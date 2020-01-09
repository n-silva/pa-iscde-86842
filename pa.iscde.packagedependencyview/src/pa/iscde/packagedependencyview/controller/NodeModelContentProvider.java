package pa.iscde.packagedependencyview.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.ResourcesPlugin;

import pa.iscde.packagedependencyview.model.Connection;
import pa.iscde.packagedependencyview.model.Node;

public class NodeModelContentProvider {
    private List<Connection> connections;
    private List<Node> nodes;
    private Map<String, ArrayList<String> > packagesDep;
    
    public NodeModelContentProvider() {
        
    	nodes = new ArrayList<Node>();
        connections = new ArrayList<Connection>();
  
        //TODO:  open directory with widget 
        String path = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString()+"/src";
        //visitor implementation to load package nodes dependency
        PackageDependency dep = new PackageDependency(path);
        
        try {
        	//Get nodeDependency
        	packagesDep = dep.ExtractPackage();
        	
        	HashMap<Node, ArrayList<String> > nodeDependency = new HashMap<>();
        	        	
        	packagesDep.forEach((key,imports) -> {
        		String nodename =  key.toString();
        		int dotIndex = nodename.lastIndexOf(".");
        		String nodeid = nodename.substring(dotIndex).replace(".","");
        		Node nod = new Node(nodename, nodeid);
                nodes.add(nod);
                nodeDependency.put(nod, imports);
        	});
        	
        	
        	nodeDependency.forEach((dest,sources) -> {
        		getNodes().forEach(source->{
        			if (sources.contains(source.getId())) {
        				connections.add(new Connection("4", "3", source, dest));
        			}
        			
                });
        		
        	});
        	
		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
        for (Connection connection : connections) {
            connection.getSource().getConnectedTo().add(connection.getDestination());
        }
    }

    //Return created Nodes
    public List<Node> getNodes() {
        return nodes;
    }
    
    
}