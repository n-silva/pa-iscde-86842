package pa.iscde.pack.demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.zest.core.viewers.GraphViewer;
import pa.iscde.packagedependencyview.ext.Item;


public class DemoPackExt implements Item {
	Point point = null;
	@Override
	public void AddItem(Menu menu, GraphViewer viewer) {
		 
		MenuItem item = new MenuItem( menu, SWT.CURSOR_HAND);
		item.setText("Export To PNG");
		item.addSelectionListener( new SelectionAdapter( )
        {
            public void widgetSelected( final SelectionEvent e )
            {
            	//Export graph to png
            	GC gc = new GC(viewer.getControl());
            	Rectangle bounds = viewer.getControl().getBounds();
            	Image image = new Image(viewer.getControl().getDisplay(), bounds);
            	try {
            	    gc.copyArea(image, 0, 0);
            	    ImageLoader imageLoader = new ImageLoader();
            	    imageLoader.data = new ImageData[] { image.getImageData() };
            	    imageLoader.save("zest_graph_export.png", SWT.IMAGE_PNG);
            	} finally {
            	    image.dispose();
            	    gc.dispose();
            	}
            	
                if ( point == null ) return;
                Display.getDefault( ).asyncExec( new Runnable( )
                {
                    @Override
                    public void run( )
                    {
                        menu.setLocation( point );
                        menu.setVisible( true );
                    }
                } );
            }
        } );
		
	
	}

}
