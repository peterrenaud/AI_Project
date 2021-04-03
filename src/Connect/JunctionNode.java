package src.Connect;

import java.util.ArrayList;

// Used to store Junctions for the Dijkstra algorithm
public class JunctionNode {
    private JunctionNode parent;
    private ArrayList<JunctionNode> adjacentJunctions;
    private long junction_ID;
    private double distance;
    private double latitude;
    private double longitude;

    public JunctionNode(){
        parent = null;
        adjacentJunctions = new ArrayList<JunctionNode>();
        junction_ID = -1;
        distance = -1;
        latitude = -1;
        longitude = -1;
    }

    public JunctionNode(JunctionEntry junction){
        parent = null;
        adjacentJunctions = new ArrayList<JunctionNode>();
        junction_ID = Long.parseLong(junction.getjunction_ID());
        latitude = Double.parseDouble(junction.getlatitude());
        longitude = Double.parseDouble(junction.getlongitude());
        distance = 0;
    }

    public void addAdjecentJunction(JunctionNode adj){
        adjacentJunctions.add(adj);
    }

    public void setParent(JunctionNode par){
        parent = par;
    }

    public void setadjacentJunctions(ArrayList<JunctionNode> adjNodes){
        adjacentJunctions = adjNodes;
    }

    public void setJunction_ID(long Id){
        junction_ID = Id;
    }

    public void setLatitude(double lat){
        latitude = lat;
    }

    public void setLongitude(double lon){
        longitude = lon;
    }

    public void setDistance(double dist){
        distance = dist;
    }

    public JunctionNode getParent(){
        return parent;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<JunctionNode> getadjacentJunctions(){
        return (ArrayList<JunctionNode>)adjacentJunctions.clone();
    }

    public long getJunction_ID(){
        return junction_ID;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public double getdistance(){
        return distance;
    }

    public String toString(){
        String output = "";
        output += "JunctionID: " + junction_ID + "\n";
        for(int i = 0; i < adjacentJunctions.size(); i++){
            output += "Adjacent Junction" + i + ": "+ adjacentJunctions.get(i).getJunction_ID() + "\n";
        }
        output += "Distance: " + distance;
        return output;

    }
}
