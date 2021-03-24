package src.Connect;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mongodb.MongoClient;


import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;


public class test_DB{
    public static void connectDB() {
        try{
        MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://Project_Access:Xlzs5fso8ghVivoS@cluster0.xwsnf.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("Ontario_Roads");

        MongoCollection<Document> collection = database.getCollection("Junctions");

        Document myDoc = collection.find(eq("junction_ID", "1500112667")).projection(fields(excludeId())).first();
        System.out.println("\n\n" + myDoc.toJson() + "\n");
        ObjectMapper mapper = new ObjectMapper();

        //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        TestNode_Junction junction = mapper.readValue(myDoc.toJson(), TestNode_Junction.class);
        System.out.println("\n\n" + junction.getjunction_ID() + "\n");
        mongoClient.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}

//Change db and names
class TestNode_Junction{
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