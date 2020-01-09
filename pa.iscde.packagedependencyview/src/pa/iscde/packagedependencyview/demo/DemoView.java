package pa.iscde.packagedependencyview.demo;

import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import pa.iscde.packagedependencyview.graph.View;
import pt.iscte.pidesco.extensibility.PidescoView;

public class DemoView implements PidescoView {
	
	@Override
	public void createContents(Composite viewArea, Map<String, Image> imageMap) {
		//show the graph
		View viewer = new View();
		viewer.createPartControl(viewArea);
	}
	
}
