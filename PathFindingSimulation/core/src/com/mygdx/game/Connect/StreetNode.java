package com.mygdx.game.Connect;

import java.util.ArrayList;
import java.util.LinkedList;

// Used to store Junctions for the Dijkstra algorithm
public class StreetNode {
    private LinkedList<StreetNode> parents;
    private LinkedList<JunctionEntry> junctions;
    private String streetname;
    private String agency;
    private ArrayList<Long> streetId;
    private int depth;

    public StreetNode(){
        parents = null;
        streetname = "";
        streetId = new ArrayList<Long>();
        depth = 0;
    }

    public StreetNode(StreetNode pars, LinkedList<JunctionEntry> junctions, String name, ArrayList<Long> id, int dep){
        setParents(pars);
        streetname = name;
        streetId = id;
        depth = dep;
    }

    @SuppressWarnings("unchecked")
    public LinkedList<StreetNode> getParents(){
        return (LinkedList<StreetNode>) parents.clone();
    }

    @SuppressWarnings("unchecked")
    public LinkedList<JunctionEntry> getJunctions(){
        return (LinkedList<JunctionEntry>)junctions.clone();
    }

    public String getStreetname(){
        return streetname;
    }

    public String getagency(){
        return agency;
    }

    public ArrayList<Long> getStreetId(){
        return streetId;
    }

    public int getDepth(){
        return depth;
    }

    public void setParents(StreetNode parent){
        parents = parent.getParents();
        parents.add(parent);
    }

    public void setJunctions(LinkedList<JunctionEntry> js){
        junctions = js;
    }

    public void addJunction(JunctionEntry js){
        junctions.push(js);
    }

    public void setStreetname(String name){
        streetname = name;
    }

    public void setagency(String ag){
        agency = ag;
    }

    public void setStreetId(ArrayList<Long> id){
        streetId = id;
    }

    public void setDepth(int dep){
        depth = dep;
    }

    public String toString(){
        String output = "";
        for(int i = 0; i < junctions.size(); i++){
            output += "Junction"+i+": "+junctions.get(i).toString() + "\n";
        }
        return output += "streetname " + streetname + "\n" +
               "agency " + agency + "\n" +
               "streetId " + streetId + "\n" +
               "depth " + depth;
    }

}