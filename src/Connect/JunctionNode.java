package src.Connect;

import java.util.ArrayList;

// Used to store Junctions for the Dijkstra algorithm
public class JunctionNode {
    private JunctionNode parent;
    private ArrayList<JunctionPath> adjacentJunctions;
    private long junction_ID;
    private double distance;
    private double distance_from_destination;
    private double cost;
    private double latitude;
    private double longitude;

    public JunctionNode(){
        parent = null;
        adjacentJunctions = new ArrayList<JunctionPath>();
        junction_ID = -1;
        distance = Double.MAX_VALUE;
        distance_from_destination = -1;
        latitude = -1;
        longitude = -1;
    }

    public JunctionNode(JunctionEntry junction){
        parent = null;
        adjacentJunctions = new ArrayList<JunctionPath>();
        junction_ID = Long.parseLong(junction.getjunction_ID());
        latitude = Double.parseDouble(junction.getlatitude());
        longitude = Double.parseDouble(junction.getlongitude());
        distance = Double.MAX_VALUE;
        distance_from_destination = -1;
    }

    public void addPath(JunctionPath adj){
        adjacentJunctions.add(adj);
    }

    public void setParent(JunctionNode par){
        parent = par;
    }

    public void setPath(ArrayList<JunctionPath> adjNodes){
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

    public void setDistanceFromDestination(double dist){
        distance_from_destination = dist;
    }

    public void setCost(double c){
        cost = c;
    }

    public JunctionNode getParent(){
        return parent;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<JunctionPath> getPaths(){
        return (ArrayList<JunctionPath>)adjacentJunctions.clone();
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

    public double getDistance(){
        return distance;
    }

    public double getDistanceFromDestination(){
        return distance_from_destination;
    }

    public double getCost(){
        return cost;
    }

    public String getData(){
        return junction_ID + " " + distance;
    }
    public ArrayList<Double> getPathPoints(){
        ArrayList<Double> points = new ArrayList<Double>();
        for(JunctionNode thisNode = this; thisNode!=null; thisNode = thisNode.getParent()){
            points.add(0, thisNode.getLongitude());
            points.add(0, thisNode.getLatitude());
        }
        return points;
    }

    public String toString(){
        String output = "";
        output += "JunctionID: " + junction_ID + "\n";
        for(int i = 0; i < adjacentJunctions.size(); i++){
            output += "Adjacent Junction" + i + ": "+ adjacentJunctions.get(i).getDestination().getJunction_ID() + "\n";
        }
        output += "Distance: " + distance_from_destination + "\n";
        return output;

    }
}
