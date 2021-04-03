package src.Connect;

// Stores queries from Road_Net_Elements collection
public class ElementEntry {
    private String streetid;
    private String junction_FROM;
    private String junction_TO;
    private String accuracy;
    private String national_ID;
    private String length;
    private String direction;
    private String type;
    private String toll;
    private String exit;
    private String acqtech;
    private String credate;
    private String revdate;
    private String geo_UPD_DT;
    private String effective_DATETIME;

    public void setjunction_FROM(String id){
        junction_FROM = id;
    }

    public void setjunction_TO(String id){
        junction_TO = id;
    }

    public void setaccuracy(String measure){
        accuracy = measure;
    }

    public void setnational_ID(String measure){
        national_ID = measure;
    }

    public void setstreetid(String id){
        streetid = id;
    }

    public void setlength(String len){
        length = len;
    }

    public void setdirection(String reg){
        direction = reg;
    }

    public void seteffective_DATETIME(String UUID){
        effective_DATETIME = UUID;
    }

    public void settype(String len){
        type = len;
    }

    public void settoll(String t){
        toll = t;
    }

    public void setexit(String e){
        exit = e;
    }

    public void setacqtech(String acq){
        acqtech = acq;
    }

    public void setcredate(String date){
        credate = date;
    }

    public void setrevdate(String date){
        revdate = date;
    }

    public void setgeo_UPD_DT(String date){
        geo_UPD_DT = date;
    }

    public String getjunction_FROM(){
        return junction_FROM;
    }

    public String getjunction_TO(){
        return junction_TO;
    }

    public String getaccuracy(){
        return accuracy;
    }

    public String getnational_ID(){
        return national_ID;
    }

    public String getstreetid(){
        return streetid;
    }

    public String getlength(){
        return length;
    }

    public String getdirection(){
        return direction;
    }

    public String geteffective_DATETIME(){
        return effective_DATETIME;
    }

    public String gettype(){
        return type;
    }

    public String gettoll(){
        return toll;
    }

    public String getexit(){
        return exit;
    }

    public String getacqtech(){
        return acqtech;
    }

    public String getcredate(){
        return credate;
    }

    public String getrevdate(){
        return revdate;
    }

    public String getgeo_UPD_DT(){
        return geo_UPD_DT;
    }
}
