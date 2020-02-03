package pa.iscde.packagedependencyview.ext;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.zest.core.viewers.GraphViewer;

public interface Item {
	void AddItem(Menu menu, GraphViewer viewer);
}
