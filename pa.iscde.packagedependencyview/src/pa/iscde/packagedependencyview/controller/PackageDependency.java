package pa.iscde.packagedependencyview.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.directorywalker.FileVisitor;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaModule;
import com.thoughtworks.qdox.model.JavaPackage;
import com.thoughtworks.qdox.model.JavaSource;

public class PackageDependency {

	private File basedir;
	private String path;
	private ArrayList<String> packages = new ArrayList<>();
	private JavaProjectBuilder builder = new JavaProjectBuilder();    
	
	public PackageDependency(String path) {
		basedir = new File(path);
		this.path = path;
		builder.addSourceTree(new File(this.path)); //Load basedir into package dependency library
		
		assert basedir.exists() && basedir.isDirectory();
	}
	
	public void accept(PackageVisitor v) throws ClassNotFoundException, IOException {
		PackageStack stack = new PackageStack();
		visit(basedir, v, stack,this.packages);
	}
	
	private static void visit(File dir, PackageVisitor v, PackageStack stack, ArrayList<String> packages) throws ClassNotFoundException, IOException {
		boolean rem = false;
		for (File child : dir.listFiles()) {
			if (child.isFile()) {
				//TODO: Maybe can be done something here
				rem = true;
			}else {
				rem = false;
				stack.push(child.getName());
				
				visit(child,v,stack,packages);
			}
		}
		
		//build package list
		if (rem) {
			packages.add(stack.packageName());
			stack.pop();
			rem = false;
		}
	}
	
	//PackageStack push and pop
	private class PackageStack {
		private ArrayList<String> stack = new ArrayList<>();
		
		public void push(String e) {
			stack.add(e);
		}
		
		public String pop() {
			return stack.remove(stack.size()-1);
		}
		
		public String packageName() {
			return String.join(".", stack);
		}
		
	}

	
	//Implement Visitor
	static class IPackageVisitor implements PackageVisitor {
		public boolean visitPackage(String packageName) {
			return true;
		}
	}
	
	/**
	 * From list of packages inside project:
	 * load package into library
	 * get classes from package
	 * extract import from the classes
	 */
	public HashMap<String, ArrayList<String> >  ExtractPackage() throws IOException, ClassNotFoundException  {
		this.accept(new IPackageVisitor());
		ArrayList<String> pckgs = this.packages;
		HashMap<String, ArrayList<String> > packagedependencies = new HashMap<>();		
		for (String path : pckgs) {
			JavaPackage pac = builder.getPackageByName(path); //load package 
			Collection<JavaClass> classes  = pac.getClasses(); // load classes
			ArrayList<String> imports = new ArrayList<>();			
			for (JavaClass cls : classes) {
				//extract imports that match work directory
				for (String imp : cls.getParentSource().getImports()) {
					String pname = imp.substring(0, imp.lastIndexOf("."));
					if (!imports.contains(imp) && pckgs.contains(pname)) {
						//avoid duplicate item
						if (!imports.contains(pname)) {
							imports.add(pname);
						}
					}
				}			
			}
			packagedependencies.put(path, imports);
		}
		return packagedependencies;
	}
	
}
