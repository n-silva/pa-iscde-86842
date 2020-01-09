package pa.iscde.packagedependencyview.graph;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.AbstractZoomableViewer;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IZoomableWorkbenchPart;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.HorizontalTreeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

import pa.iscde.packagedependencyview.controller.NodeModelContentProvider;
import pa.iscde.packagedependencyview.controller.ZestLabelProvider;
import pa.iscde.packagedependencyview.controller.ZestNodeContentProvider;
import pa.iscde.packagedependencyview.model.Figures;

public class View extends ViewPart implements IZoomableWorkbenchPart {
    public static final String ID = "pa.iscde.packagedependencyview.graph.view";
    private GraphViewer viewer;
    private static Point point;
    
    public void createPartControl(Composite parent) {
    	//Create Graphview
        viewer = new GraphViewer(parent, SWT.BORDER);
        viewer.setContentProvider(new ZestNodeContentProvider());
        viewer.setLabelProvider(new ZestLabelProvider());
        
        //Add nodes
        NodeModelContentProvider model = new NodeModelContentProvider();
        viewer.setInput(model.getNodes());
       
        //Create Layout
        LayoutAlgorithm layout = setLayout();
        viewer.setLayoutAlgorithm(layout, true);
        viewer.applyLayout();
        
                
        //====================================
        //Add context menu
        final Menu menu = new Menu(parent);
        
        MenuItem header = new MenuItem( menu, SWT.NONE);
        header.setText("-- Menu --");
        menu.setDefaultItem(header);
        header.setID(0);
        MenuItem separator = new MenuItem( menu, SWT.BOLD);
        separator.setText("--------------------");
        
        MenuItem item = new MenuItem( menu, SWT.CHECK );
        item.setText( "Check box" );
        
        item.addSelectionListener( new SelectionAdapter( )
        {
            public void widgetSelected( final SelectionEvent e )
            {
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

        //====================================
        //show menu on right click event
        final Graph graph = viewer.getGraphControl();
        viewer.getGraphControl().addMenuDetectListener(new MenuDetectListener() {
			
			@Override
			public void menuDetected(MenuDetectEvent e) {
				// TODO Add functionality
				Point point = graph.toControl(e.x, e.y);
                IFigure fig = graph.getFigureAt(point.x, point.y);
                if (fig != null)
                {
                	if (fig instanceof Figures) {
                		MenuItem defaultItem = menu.getItem(0);
                		
                		//TODO: action event goes here  
                		
                	}
                	menu.setVisible(true);  
                }
                else
                {
                    menu.setVisible(false);
                }
			}
		});
        
        
    }

    private LayoutAlgorithm setLayout() {
        LayoutAlgorithm layout;
        //layout = new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
        layout = new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
        // layout = new GridLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
        // layout = new HorizontalTreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
        // layout = new RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
        return layout;

    }

    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
    }
    
    @Override
    public AbstractZoomableViewer getZoomableViewer() {
        return viewer;
    }
}