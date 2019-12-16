package pt.iscte.pidesco.controller;

public interface PackageVisitor{

	default boolean visitPackage(String packageName) {
		return true;
	}
	
	/**
	 *	Executed when a class is visited. 
	 */
	default void visit(Class<?> clazz) {

	}
}