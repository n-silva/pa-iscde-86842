package pt.iscte.pidesco.model;


import java.util.ArrayList;
import java.util.List;

public class NodeModelContentProvider {
    private List<Connection> connections;
    private List<Node> nodes;

    public NodeModelContentProvider() {
        //TODO : visitor implementation to load package nodes dependency
    	nodes = new ArrayList<Node>();
        Node node = new Node("1", "Package 1");
        nodes.add(node);
        node = new Node("2", "Package 2");
        nodes.add(node);
        node = new Node("3", "Package 3");
        nodes.add(node);
        node = new Node("4", "Package 4");
        nodes.add(node);
        node = new Node("5", "Package 5");
        nodes.add(node);
        node = new Node("6", "Package Isolado");
        nodes.add(node);

        connections = new ArrayList<Connection>();
        Connection connect = new Connection("1", "1", nodes.get(0), nodes.get(1));
        connections.add(connect);
        connect = new Connection("2", "2", nodes.get(0), nodes.get(4));
        connections.add(connect);
        connect = new Connection("3", "3", nodes.get(2), nodes.get(1));
        connections.add(connect);
        connect = new Connection("4", "3", nodes.get(1), nodes.get(3));
        connections.add(connect);


        for (Connection connection : connections) {
            connection.getSource().getConnectedTo().add(connection.getDestination());
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }
}