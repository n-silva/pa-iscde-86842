package pt.iscte.pidesco.controller;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.zest.core.viewers.EntityConnectionData;

import pt.iscte.pidesco.model.Connection;
import pt.iscte.pidesco.model.Node;

public class ZestLabelProvider extends LabelProvider {
    @Override
    public String getText(Object element) {
        if (element instanceof Node) {
            Node myNode = (Node) element;
            return myNode.getName();
        }
        // Not called with the IGraphEntityContentProvider
        if (element instanceof Connection) {
            Connection myConnection = (Connection) element;
            return myConnection.getLabel();
        }

        if (element instanceof EntityConnectionData) {
            EntityConnectionData test = (EntityConnectionData) element;
            return "Teste";
        }
        throw new RuntimeException("Wrong type: "
                + element.getClass().toString());
    }
}