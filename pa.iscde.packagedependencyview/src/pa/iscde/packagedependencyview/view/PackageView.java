package pa.iscde.packagedependencyview.view;

import java.util.Map;
import java.util.Set;


import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import pa.iscde.packagedependencyview.graph.View;

import pt.iscte.pidesco.extensibility.PidescoView;

public class PackageView implements PidescoView {
	public static final String VIEW_ID = "pa.iscde.packagedependencyview.view";	
	private static final String EXT_POINT_EXPORT = "pa.iscde.packagedependencyview.menuitem";
	
	private static PackageView instance;

	private Image packageIcon;
	private Image classIcon;

	
	
	public PackageView() {
		instance = this;
		
	}
	
	public static PackageView getInstance() {
		return instance;
	}

	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		//show the graph
		View viewer = new View();
		viewer.createPartControl(viewArea);
	}
	
	
	
}
