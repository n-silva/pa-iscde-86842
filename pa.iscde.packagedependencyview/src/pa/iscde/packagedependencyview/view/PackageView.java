package pa.iscde.packagedependencyview.view;

import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import pa.iscde.packagedependencyview.graph.View;
import pa.iscde.packagedependencyview.ext.PackageViewExport;

import pt.iscte.pidesco.extensibility.PidescoView;

public class PackageView implements PidescoView {
	public static final String VIEW_ID = "pa.iscde.packagedependencyview.view";	
	private static final String EXT_POINT_EXPORT = "pa.iscde.packagedependencyview.export";
	
	private static PackageView instance;

	private Image packageIcon;
	private Image classIcon;

	private Set<String> activeExports;
	
	
	public PackageView() {
		instance = this;
		
	}
	
	public static PackageView getInstance() {
		return instance;
	}
    
	private void AddExport() {
		IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
		
		IExtensionPoint extensionPoint = extRegistry.getExtensionPoint(EXT_POINT_EXPORT);
		IExtension[] extensions = extensionPoint.getExtensions();
		for(IExtension e : extensions) {
			IConfigurationElement[] confElements = e.getConfigurationElements();
			for(IConfigurationElement c : confElements) {
				
				try {
					Object o = c.createExecutableExtension(EXT_POINT_EXPORT);
				} catch (CoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}

	
	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		//show the graph
		View viewer = new View();
		viewer.createPartControl(viewArea);
	}
	
	
	
}
