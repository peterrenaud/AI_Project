package src.Connect;

import java.util.LinkedList;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StreetFinder {
    private MongoClientURI connection;
    //private MongoCollection table;

    public StreetFinder(){
        connection = new MongoClientURI(
            "mongodb+srv://Project_Access:Xlzs5fso8ghVivoS@cluster0.xwsnf.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
    }

    public StreetNode findStreet(String city, String streetname){
        try{
            MongoClient mongoClient = new MongoClient(connection);
            MongoDatabase database = mongoClient.getDatabase("Ontario_Roads");
            
            StreetNode node = new StreetNode();
            node.setStreetname(streetname.toUpperCase());
            MongoCollection<Document> collection = database.getCollection("Street_Names");

            Bson filter = and(regex("streetname",streetname.toUpperCase()), regex("agency", city.toUpperCase()));
            System.out.println("\n\n" + filter.toString() + "\n");

            Document myDoc = collection.find(filter).projection(fields(excludeId())).first();

            if(myDoc != null)
                System.out.println("\n\n" + myDoc.toJson() + "\n");
            else
                System.out.println("\n\nmyDoc is null\n");

            ObjectMapper mapper = new ObjectMapper();
            // mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            StreetEntry street = mapper.readValue(myDoc.toJson(), StreetEntry.class);
            System.out.println("\n\n" + street.getornelement_ID() + "\n");

            // double[] lat_long = test_Geocode.Geocode("Canada", street.get, street.getstreetname());

            collection = database.getCollection("Road_Net_Elements");
            filter = and(eq("objectid", street.getornelement_ID()));
            myDoc = collection.find(filter).projection(fields(excludeId())).first();
            ElementEntry element = mapper.readValue(myDoc.toJson(), ElementEntry.class);
            System.out.println("\n\n" + element.getobjectid() + "\n");

            collection = database.getCollection("Junctions");
            filter = eq("junction_ID", element.getjunction_FROM());
            myDoc = collection.find(filter).projection(fields(excludeId())).first();
            JunctionEntry junction = mapper.readValue(myDoc.toJson(), JunctionEntry.class);

            
            node.setJuncitonTo(element.getjunction_TO());
            node.setJunctionFrom(element.getjunction_FROM());
            node.setLatitude(junction.getlatitude());
            node.setLongitude(junction.getlongitude());
            node.setStreetId(street.getornelement_ID());
            node.setagency(street.getagency());

            System.out.println("\n" + node.toString());

            mongoClient.close();
            return new StreetNode();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public LinkedList<StreetNode> findStreets(LinkedList<JunctionEntry> junctions){
        try{
        LinkedList<StreetNode> connections = new LinkedList<StreetNode>();

        MongoClient mongoClient = new MongoClient(connection);
        MongoDatabase database = mongoClient.getDatabase("Ontario_Roads");
        
        StreetNode node = new StreetNode();

        MongoCollection<Document> collection = database.getCollection("Junctions");

        Document myDoc = collection.find(eq("junction_ID", "1500112667")).projection(fields(excludeId())).first();
        System.out.println("\n\n" + myDoc.toJson() + "\n");
        ObjectMapper mapper = new ObjectMapper();

        //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JunctionEntry junction = mapper.readValue(myDoc.toJson(), JunctionEntry.class);
        System.out.println("\n\n" + junction.getjunction_ID() + "\n");
        mongoClient.close();

        return connections;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public LinkedList<StreetNode> findJunctions(String city, String streetname){
        try{
        LinkedList<StreetNode> connections = new LinkedList<StreetNode>();

        MongoClient mongoClient = new MongoClient(connection);
        MongoDatabase database = mongoClient.getDatabase("Ontario_Roads");
        
        StreetNode node = new StreetNode();
        node.setStreetname(streetname);
        MongoCollection<Document> collection = database.getCollection("Junctions");

        Document myDoc = collection.find(eq("junction_ID", "1500112667")).projection(fields(excludeId())).first();
        System.out.println("\n\n" + myDoc.toJson() + "\n");
        ObjectMapper mapper = new ObjectMapper();

        //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JunctionEntry junction = mapper.readValue(myDoc.toJson(), JunctionEntry.class);
        System.out.println("\n\n" + junction.getjunction_ID() + "\n");
        mongoClient.close();

        return connections;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
