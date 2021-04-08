package com.mygdx.game.Connect;

// Stores queries from Street_Names collection
public class StreetEntry{
    private String event_ID;
    private String streetid;
    private String measure_FROM;
    private String measure_TO;
    private String streetname;
    private String agency;
    private String effective_DATETIME;
    private String system_DATETIME;
    private String objectid;

    public void setevent_ID(String id){
        event_ID = id;
    }

    public void setstreetid(String id){
        streetid = id;
    }

    public void setmeasure_FROM(String measure){
        measure_FROM = measure;
    }

    public void setmeasure_TO(String measure){
        measure_TO = measure;
    }

    public void setobjectid(String id){
        objectid = id;
    }

    public void setstreetname(String name){
        streetname = name;
    }

    public void setagency(String reg){
        agency = reg;
    }

    public void seteffective_DATETIME(String UUID){
        effective_DATETIME = UUID;
    }

    public void setsystem_DATETIME(String len){
        system_DATETIME = len;
    }

    public String getevent_ID(){
        return event_ID;
    }

    public String getstreetid(){
        return streetid;
    }

    public String getmeasure_FROM(){
        return measure_FROM;
    }

    public String getmeasure_TO(){
        return measure_TO;
    }

    public String getobjectid(){
        return objectid;
    }

    public String getstreetname(){
        return streetname;
    }

    public String getagency(){
        return agency;
    }

    public String geteffective_DATETIME(){
        return effective_DATETIME;
    }

    public String getsystem_DATETIME(){
        return system_DATETIME;
    }
}