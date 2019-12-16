package pt.iscte.pidesco.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.AbstractZoomableViewer;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IZoomableWorkbenchPart;
import org.eclipse.zest.core.viewers.ZoomContributionViewItem;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

import pt.iscte.pidesco.controller.ZestLabelProvider;
import pt.iscte.pidesco.controller.ZestNodeContentProvider;
import pt.iscte.pidesco.model.NodeModelContentProvider;


public class View extends ViewPart implements IZoomableWorkbenchPart {
    public static final String ID = "de.vogella.zest.jface.view";
    private GraphViewer viewer;

    public void createPartControl(Composite parent) {
        viewer = new GraphViewer(parent, SWT.BORDER);
        viewer.setContentProvider(new ZestNodeContentProvider());
        viewer.setLabelProvider(new ZestLabelProvider());
        NodeModelContentProvider model = new NodeModelContentProvider();
        viewer.setInput(model.getNodes());
        LayoutAlgorithm layout = setLayout();
        viewer.setLayoutAlgorithm(layout, true);
        viewer.applyLayout();
    }

    private LayoutAlgorithm setLayout() {
        LayoutAlgorithm layout;
        // layout = new
        // SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
        layout = new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
        // layout = new
        // GridLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
        // layout = new
        // HorizontalTreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
        // layout = new
        // RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
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