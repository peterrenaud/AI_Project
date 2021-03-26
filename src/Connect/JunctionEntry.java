package src.Connect;

public class JunctionEntry {
    private String junction_ID;
    private String latitude;
    private String longitude;
    private String junction_TYPE;
    private String national_UUID;
    private String effective_DATETIME;
    private String system_DATETIME;
    private String objectid;

    public void setjunction_ID(String Id){
        junction_ID = Id;
    }

    public void setlatitude(String Id){
        latitude = Id;
    }

    public void setlongitude(String Id){
        longitude = Id;
    }

    public void setjunction_TYPE(String Id){
        junction_TYPE = Id;
    }

    public void setnational_UUID(String Id){
        national_UUID = Id;
    }

    public void seteffective_DATETIME(String Id){
        effective_DATETIME = Id;
    }

    public void setsystem_DATETIME(String Id){
        system_DATETIME = Id;
    }

    public void setobjectid(String Id){
        objectid = Id;
    }

    public String getjunction_ID(){
        return junction_ID;
    }

    public String getlatitude(){
        return latitude;
    }

    public String getlongitude(){
        return longitude;
    }

    public String getjunction_TYPE(){
        return junction_TYPE;
    }

    public String getnational_UUID(){
        return national_UUID;
    }

    public String geteffective_DATETIME(){
        return effective_DATETIME;
    }

    public String getsystem_DATETIME(){
        return system_DATETIME;
    }

    public String getobjectid(){
        return objectid;
    }


}
