package pa.iscde.packagedependencyview.controller;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphItem;

public class ContextMenu  {
	private GraphViewer viewer;
    private static Point point;

    public ContextMenu(Composite parent, int style) {
    	
    	viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				// TODO Auto-generated method stub
			}
		});
       
        viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
        
        viewer.addPostSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
        
        viewer.getGraphControl().addListener(3,new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if (event.button == 3) {
					//ISelection entity = viewer.getSelection();
					System.out.println(event.button );
					System.out.println(event.data);
					System.out.println(event.text);
					
					//if (!entity.isEmpty()) {
			            //Node node = (Node) entity;
						//List node = (List)entity;
			         //   System.out.println(event.item.getData());
						//System.out.println(node.getData()+ " : " +event.button);
			        //}
					
				}
				
			}
		});
      
        final Graph graph = viewer.getGraphControl();
        
        graph.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                System.out.println(e.item);
                System.out.println(e.detail);
                System.out.println(e.text);
            }

        });
        
    
    }

}