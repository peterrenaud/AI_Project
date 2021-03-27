package src.Connect;

import java.util.ArrayList;

// Used to store Junctions for the Dijkstra algorithm
public class JunctionNode {
    private JunctionNode parent;
    private ArrayList<String> adjacentJunctions;
    private long junction_ID;
    private double distance;
    private double latitude;
    private double longitude;

    public JunctionNode(){
        parent = null;
        adjacentJunctions = null;
        junction_ID = -1;
        distance = -1;
        latitude = -1;
        longitude = -1;
    }

    public void setParent(JunctionNode par){
        parent = par;
    }

    public void setadjacentJunctions(ArrayList<String> adjNodes){
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
    public ArrayList<String> getadjacentJunctions(){
        return (ArrayList<String>)adjacentJunctions.clone();
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
            output += "Adjacent Junction" + i + ": "+ adjacentJunctions.get(i) + "\n";
        }
        output += "Distance: " + distance;
        return output;

    }
}
