package pa.iscde.packagedependencyview.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final String id;
    private final String name;
    private List<Node> connections;

    public Node(String id, String name) {
        this.id = id;
        this.name = name;
        this.connections = new ArrayList<Node>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Node> getConnectedTo() {
        return connections;
    }

}