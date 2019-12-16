package pt.iscte.pidesco.controller;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

import pt.iscte.pidesco.model.Node;

public class ZestNodeContentProvider extends ArrayContentProvider  implements IGraphEntityContentProvider {

    @Override
    public Object[] getConnectedTo(Object entity) {
        if (entity instanceof Node) {
            Node node = (Node) entity;
            return node.getConnectedTo().toArray();
        }
        throw new RuntimeException("Type not supported");
    }
}