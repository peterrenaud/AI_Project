//package Connect;
//
//import com.mongodb.MongoClientURI;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//
//import org.bson.Document;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import com.mongodb.MongoClient;
//
//
//import static com.mongodb.client.model.Filters.*;
//import static com.mongodb.client.model.Projections.*;
//
//
//public class test_DB{
//    public static void connectDB() {
//        try{
//        MongoClientURI uri = new MongoClientURI(
//            "mongodb+srv://Project_Access:Xlzs5fso8ghVivoS@cluster0.xwsnf.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
//        MongoClient mongoClient = new MongoClient(uri);
//        MongoDatabase database = mongoClient.getDatabase("Ontario_Roads");
//
//        MongoCollection<Document> collection = database.getCollection("Junctions");
//
//        Document myDoc = collection.find(eq("junction_ID", "1500112667")).projection(fields(excludeId())).first();
//        System.out.println("\n\n" + myDoc.toJson() + "\n");
//        ObjectMapper mapper = new ObjectMapper();
//
//        //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        JunctionEntry junction = mapper.readValue(myDoc.toJson(), JunctionEntry.class);
//        System.out.println("\n\n" + junction.getjunction_ID() + "\n");
//        mongoClient.close();
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//}
