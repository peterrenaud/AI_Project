package src.Connect;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import com.mongodb.MongoClient;

public class test_DB{
    public static void connectDB() {
        MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://Project_Access:Ju$tW0rK?-!@cluster0.xwsnf.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("Ontario_Roads");

        MongoCollection<Document> collection = database.getCollection("Junctions");
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());
        mongoClient.close();
    }
    
}