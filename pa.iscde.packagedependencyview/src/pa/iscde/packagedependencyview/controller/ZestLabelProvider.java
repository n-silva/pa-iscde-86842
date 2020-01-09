package pa.iscde.packagedependencyview.controller;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.zest.core.viewers.EntityConnectionData;
import org.eclipse.zest.core.viewers.IConnectionStyleProvider;
import org.eclipse.zest.core.viewers.IFigureProvider;
import org.eclipse.zest.core.viewers.ISelfStyleProvider;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;

import pa.iscde.packagedependencyview.model.Connection;
import pa.iscde.packagedependencyview.model.Figures;
import pa.iscde.packagedependencyview.model.Node;

public class ZestLabelProvider extends LabelProvider implements IFigureProvider, IConnectionStyleProvider, ISelfStyleProvider{
    @Override
    public String getText(Object element) {
    	if (element instanceof Node) {
            Node node = (Node) element;
            return node.getName();
        }
    	
        //Not called with the IGraphEntityContentProvider
        if (element instanceof Connection) {
            Connection myConnection = (Connection) element;
            return myConnection.getLabel();
        }

        if (element instanceof EntityConnectionData) {
            EntityConnectionData test = (EntityConnectionData) element;
            return " <import> "+((Node) test.source).getName();
        }
        throw new RuntimeException("Wrong type: "
                + element.getClass().toString());
    }

	@Override
	public void selfStyleConnection(Object element, GraphConnection connection) {
		PolylineConnection connectionFig = (PolylineConnection) connection.getConnectionFigure();
		PolygonDecoration decoration = new PolygonDecoration();
		decoration.setScale(20, 10);
		decoration.setLineWidth(2);
		decoration.setOpaque(true);
		decoration.setBackgroundColor(ColorConstants.white);
		connectionFig.setTargetDecoration(decoration);		
	}

	@Override
	public void selfStyleNode(Object element, GraphNode node) {
		
	}

	@Override
	public int getConnectionStyle(Object rel) {
		return ZestStyles.CONNECTIONS_DASH;
	}

	@Override
	public Color getColor(Object rel) {
		return ColorConstants.red;
	}

	@Override
	public Color getHighlightColor(Object rel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLineWidth(Object rel) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public IFigure getTooltip(Object entity) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public IFigure getFigure(Object element) {
		return new Figures(((Node) element).getName()); //Create a new box
	}
}