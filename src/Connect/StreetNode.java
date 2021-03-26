package src.Connect;

import java.util.LinkedList;

public class StreetNode {
    private LinkedList<StreetNode> parents;
    private String junctionFrom;
    private String junctionTo;
    private String streetname;
    private String agency;
    private String streetId;
    private String latitude;
    private String longitude;
    private int depth;

    public StreetNode(){
        parents = null;
        junctionFrom = "";
        junctionTo = "";
        streetname = "";
        streetId = "";
        latitude  = "";
        longitude = "";
        depth = 0;
    }

    public StreetNode(LinkedList<StreetNode> pars, String junFrom, String junTo, String name, String id, String lat, String lon, int dep){
        parents = pars;
        junctionFrom = junFrom;
        junctionTo = junTo;
        streetname = name;
        streetId = id;
        latitude = lat;
        longitude = lon;
        depth = dep;
    }

    public LinkedList<StreetNode> getParents(){
        return parents;
    }

    public String getJunctionFrom(){
        return junctionFrom;
    }

    public String getJunctionTo(){
        return junctionTo;
    }

    public String getStreetname(){
        return streetname;
    }

    public String getagency(){
        return agency;
    }

    public String getStreetId(){
        return streetId;
    }

    public String getLatitude(){
        return latitude;
    }

    public String getLongitude(){
        return longitude;
    }

    public int getDepth(){
        return depth;
    }

    public void setParents(StreetNode parent){
        parents = parent.getParents();
        parents.add(parent);
    }

    public void setJunctionFrom(String junction){
        junctionFrom = junction;
    }

    public void setJuncitonTo(String junction){
        junctionTo = junction;
    }

    public void setStreetname(String name){
        streetname = name;
    }

    public void setagency(String ag){
        agency = ag;
    }

    public void setStreetId(String id){
        streetId = id;
    }

    public void setLatitude(String lat){
        latitude = lat;
    }

    public void setLongitude(String lon){
        longitude = lon;
    }

    public void setDepth(int dep){
        depth = dep;
    }

    public String toString(){
        return "junction_FROM " + junctionFrom + "\n" +
               "junction_TO " + junctionTo + "\n" +
               "streetname " + streetname + "\n" +
               "agency " + agency + "\n" +
               "streetId " + streetId + "\n" +
               "latitude " + latitude + "\n" +
               "longitude " + longitude + "\n" +
               "depth " + depth;
    }

}