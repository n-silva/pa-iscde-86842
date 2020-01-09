package pa.iscde.packagedependencyview.model;

public class Connection {
    final String id;
    final String label;
    final Node source;
    final Node destination;

    public Connection(String id, String label, Node source, Node destination) {
        this.id = id;
        this.label = label;
        this.source = source;
        this.destination = destination;
    }

    public String getLabel() {
        return label;
    }

    public Node getSource() {
        return source;
    }
    public Node getDestination() {
        return destination;
    }

}