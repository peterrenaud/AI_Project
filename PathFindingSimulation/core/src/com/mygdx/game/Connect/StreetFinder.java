//package com.pathfinding.sim.Connect;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.logging.Logger;
//import java.util.logging.Level;
//
//import com.mongodb.MongoClientURI;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.MongoClient;
//import static com.mongodb.client.model.Filters.*;
//import static com.mongodb.client.model.Projections.*;
//
//import org.bson.Document;
//import org.bson.conversions.Bson;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//// Class for connecting to and querying the MongoDB cluster
//public class StreetFinder {
//    private MongoClientURI connection;
//    private MongoClient client;
//    private MongoDatabase database;
//
//    /**
//     * Default constructor for Streetfinder
//     * Connects to the Ontario_Roads Database
//     */
//    public StreetFinder(){
//        connection = new MongoClientURI(
//            "mongodb+srv://Project_Access:Xlzs5fso8ghVivoS@cluster0.xwsnf.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
//        client = new MongoClient(connection);
//        database = client.getDatabase("Ontario_Roads");
//        Logger mongoLogger = Logger.getLogger("org.mongodb");
//        mongoLogger.setLevel(Level.SEVERE);
//    }
//
//    /**
//     * Returns the Street Node of a given city and street
//     * @param city The city that the street is found in
//     * @param streetname The street name to find StreetEntries for
//     * @return the StreetNode of the given city and street
//     */
//    public StreetNode findStreet(String city, String streetname){
//        try{
//            // Create variables to be used
//            MongoCursor<Document> myDocs = null;
//            Document myDoc = null;
//            ArrayList<String> junctionIds = new ArrayList<String>();
//            ArrayList<Long> streetIds = new ArrayList<Long>();
//            StreetEntry street = new StreetEntry();
//            LinkedList<JunctionEntry> junctions = new LinkedList<JunctionEntry>();
//            Boolean skipFrom = false, skipTo = false;
//
//            // Create the node that will be returned
//            StreetNode node = new StreetNode();
//            node.setStreetname(streetname.toUpperCase());
//
//            // Gets the StreetEntry from the input
//            ObjectMapper mapper = new ObjectMapper();
//
//            // Finds all street segments with the given name
//            MongoCollection<Document> collection = database.getCollection("Street_Names");
//            Bson filter = and(regex("streetname",streetname.toUpperCase()), regex("agency", city.toUpperCase()));
//
//            myDocs = collection.find(filter).projection(fields(excludeId())).iterator();
//
//            // Search through the street segments
//            while(myDocs.hasNext()){
//                myDoc = myDocs.next();
//                street = mapper.readValue(myDoc.toJson(), StreetEntry.class);
//                streetIds.add(Long.parseLong(street.getstreetid()));
//            }
//
//            // Find all junctions connected to the given Street segment
//            collection = database.getCollection("Road_Net_Elements");
//
//            for(int i = 0; i < streetIds.size(); i++){
//                filter = and(eq("streetid", streetIds.get(i).toString()));
//                // There is only one entry for each streetid
//                myDoc = collection.find(filter).projection(fields(excludeId())).first();
//                ElementEntry element = mapper.readValue(myDoc.toJson(), ElementEntry.class);
//                for(int j = 0; j < junctionIds.size(); j++){
//                    if(skipFrom && skipTo){
//                        break;
//                    }
//                    if(element.getjunction_FROM().compareTo(junctionIds.get(j)) == 0){
//                        skipFrom = true;
//                        continue;
//                    }
//                    if(element.getjunction_TO().compareTo(junctionIds.get(j)) == 0){
//                        skipTo = true;
//                        continue;
//                    }
//                }
//                if(skipFrom && skipTo)
//                    continue;
//                if(skipFrom){
//                    junctions.push(mapper.readValue(database.getCollection("Junctions").find(eq("junction_ID", element.getjunction_TO())).projection(fields(excludeId())).first().toJson(), JunctionEntry.class));
//                    junctionIds.add(element.getjunction_TO());
//                    continue;
//                }
//                else if(skipTo){
//                    junctions.push(mapper.readValue(database.getCollection("Junctions").find(eq("junction_ID", element.getjunction_FROM())).projection(fields(excludeId())).first().toJson(), JunctionEntry.class));
//                    junctionIds.add(element.getjunction_FROM());
//                    continue;
//                }
//                else{
//                    junctions.push(mapper.readValue(database.getCollection("Junctions").find(eq("junction_ID", element.getjunction_FROM())).projection(fields(excludeId())).first().toJson(), JunctionEntry.class));
//                    junctions.push(mapper.readValue(database.getCollection("Junctions").find(eq("junction_ID", element.getjunction_TO())).projection(fields(excludeId())).first().toJson(), JunctionEntry.class));
//                    junctionIds.add(element.getjunction_FROM());
//                    junctionIds.add(element.getjunction_TO());
//                }
//                skipFrom = false;
//                skipTo = false;
//            }
//
//            node.setJunctions(junctions);
//            node.setStreetId(streetIds);
//            node.setagency(street.getagency());
//            node.setDepth(0);
//
//            return node;
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * Finds the closest JunctionNode to a given StreetNode.
//     * @param street The street node to find its closest junction to the given location
//     * @param location The location to calculate distance to
//     * @param destination The destination location to calculate the total distance of the junction from the destination
//     * @return The junction node that is the closest to the starting location
//     */
//    public JunctionNode findClosestJunction(StreetNode street, double[] location, double[] destination){
//        LinkedList<JunctionEntry> possibleJunctions = street.getJunctions();
//
//        JunctionNode output = new JunctionNode();
//
//        JunctionEntry closestJunction = new JunctionEntry();
//        JunctionEntry current = new JunctionEntry();
//        double closest = Double.MAX_VALUE;
//        double distance = 0;
//
//        while(!possibleJunctions.isEmpty()){
//            current = possibleJunctions.pop();
//            distance = Math.sqrt(Math.pow(Double.parseDouble(current.getlatitude()) - location[0],2) + Math.pow(Double.parseDouble(current.getlongitude()) - location[1], 2));
//            if(distance < closest){
//                closestJunction = current;
//                closest = distance;
//            }
//        }
//
//        output.setJunction_ID(Long.parseLong(closestJunction.getjunction_ID()));
//        output.setLatitude(Double.parseDouble(closestJunction.getlatitude()));
//        output.setLongitude(Double.parseDouble(closestJunction.getlongitude()));
//        distance = Math.sqrt(Math.pow(destination[0] - output.getLatitude(), 2) + Math.pow(destination[1] - output.getLongitude(), 2));
//        output.setDistanceFromDestination(distance);
//
//        findAdjacentJunctions(output, destination);
//
//        return output;
//    }
//
//    /**
//     * Finds all the Junctions adjacent to a given JunctionNode
//     * @param node
//     * @param destination
//     */
//    public void findAdjacentJunctions(JunctionNode node, double[] destination){
//        try{
//            ArrayList<JunctionPath> junctionAdj = new ArrayList<JunctionPath>();
//            JunctionNode tempNode =null;
//            double cost = 0;
//
//            MongoCursor<Document> docs  = database.getCollection("Road_Net_Elements").find(or(eq("junction_FROM", Long.toString(node.getJunction_ID())),eq("junction_TO", Long.toString(node.getJunction_ID())))).projection(excludeId()).iterator();
//            Document doc = null;
//            ElementEntry tempElement = null;
//            long tempID = 0;
//            boolean skip = false;
//            ObjectMapper mapper = new ObjectMapper();
//
//            while(docs.hasNext()){
//                doc = docs.next();
//                tempElement = mapper.readValue(doc.toJson(), ElementEntry.class);
//
//                // One of the junctions is the starting junction, so we want to add the other junction instead
//                if(tempElement.getjunction_FROM().compareTo(Long.toString(node.getJunction_ID())) == 0){
//                    tempID = Long.parseLong(tempElement.getjunction_TO());
//                }
//                else{
//                    tempID = Long.parseLong(tempElement.getjunction_FROM());
//                }
//                // Check that we are not adding a duplicate junction
//                for(int i = 0; i < junctionAdj.size(); i++){
//                    if(tempID == junctionAdj.get(i).getDestination().getJunction_ID()){
//                        skip = true;
//                        break;
//                    }
//                }
//                // Creates a Junction Node and adds it to the adjacent junctions
//                if(!skip){
//                    doc = database.getCollection("Junctions").find(eq("junction_ID",Long.toString(tempID))).projection(excludeId()).first();
//                    tempNode = new JunctionNode(mapper.readValue(doc.toJson(), JunctionEntry.class));
//                    tempNode.setDistanceFromDestination(Math.sqrt((Math.pow(destination[0] - tempNode.getLatitude(), 2) + Math.pow(destination[1] - tempNode.getLongitude(), 2))));
//                    cost = Math.sqrt((Math.pow(tempNode.getLatitude() - node.getLatitude(), 2) + Math.pow(tempNode.getLongitude() - node.getLongitude(), 2)));
//                    junctionAdj.add(new JunctionPath(tempNode, cost));
//                }
//            }
//            node.setPath(junctionAdj);
//        }
//        catch(JsonMappingException jme){
//            jme.printStackTrace();
//        }
//        catch(JsonProcessingException jpe){
//            jpe.printStackTrace();
//        }
//    }
//
//        /**
//     * Finds all the Junctions adjacent to a given JunctionNode
//     * @param node
//     * @param destination
//     */
//    // public void findAdjacentJunctions(JunctionNode node){
//    //     try{
//    //         ArrayList<JunctionPath> junctionAdj = new ArrayList<JunctionPath>();
//    //         JunctionNode tempNode =null;
//    //         double cost = 0;
//
//    //         MongoCursor<Document> docs  = database.getCollection("Road_Net_Elements").find(or(eq("junction_FROM", Long.toString(node.getJunction_ID())),eq("junction_TO", Long.toString(node.getJunction_ID())))).projection(excludeId()).iterator();
//    //         Document doc = null;
//    //         ElementEntry tempElement = null;
//    //         long tempID = 0;
//    //         boolean skip = false;
//    //         ObjectMapper mapper = new ObjectMapper();
//
//    //         while(docs.hasNext()){
//    //             doc = docs.next();
//    //             tempElement = mapper.readValue(doc.toJson(), ElementEntry.class);
//
//    //             // One of the junctions is the starting junction, so we want to add the other junction instead
//    //             if(tempElement.getjunction_FROM().compareTo(Long.toString(node.getJunction_ID())) == 0){
//    //                 tempID = Long.parseLong(tempElement.getjunction_TO());
//    //             }
//    //             else{
//    //                 tempID = Long.parseLong(tempElement.getjunction_FROM());
//    //             }
//    //             // Check that we are not adding a duplicate junction
//    //             for(int i = 0; i < junctionAdj.size(); i++){
//    //                 if(tempID == junctionAdj.get(i).getDestination().getJunction_ID()){
//    //                     skip = true;
//    //                     break;
//    //                 }
//    //             }
//    //             // Creates a Junction Node and adds it to the adjacent junctions
//    //             if(!skip){
//    //                 doc = database.getCollection("Junctions").find(eq("junction_ID",Long.toString(tempID))).projection(excludeId()).first();
//    //                 tempNode = new JunctionNode(mapper.readValue(doc.toJson(), JunctionEntry.class));
//    //                 cost = Math.sqrt((Math.pow(tempNode.getLatitude() - node.getLatitude(), 2) + Math.pow(tempNode.getLongitude() - node.getLongitude(), 2)));
//    //                 junctionAdj.add(new JunctionPath(tempNode, cost));
//    //             }
//    //         }
//    //         node.setPath(junctionAdj);
//    //     }
//    //     catch(JsonMappingException jme){
//    //         jme.printStackTrace();
//    //     }
//    //     catch(JsonProcessingException jpe){
//    //         jpe.printStackTrace();
//    //     }
//    // }
//
//    // Unsure if this is needed, but may be worked on in the future
//    /*
//    public LinkedList<StreetNode> findConnectedStreets(StreetNode start){
//        try{
//        // Get the list of connected junction to the StreetNode
//        LinkedList<JunctionEntry> junctions = start.getJunctions();
//
//        // Create a new Linked List to store the Connected StreetNodes
//        LinkedList<StreetNode> connections = new LinkedList<StreetNode>();
//
//        // Connect to the MongoDB database
//        MongoClient mongoClient = new MongoClient(connection);
//        MongoDatabase database = mongoClient.getDatabase("Ontario_Roads");
//
//        // Create the neccessary varaibles that will be used to temporary stores Nodes and Entries
//        StreetNode node = new StreetNode();
//        StreetEntry street = null;
//        LinkedList<ElementEntry> elements = new LinkedList<ElementEntry>();
//        LinkedList<JunctionEntry> junctionConnected = new LinkedList<JunctionEntry>();
//
//        MongoCollection<Document> collection = database.getCollection("Road_Net_Elements");
//        // Create the object mapper for parsing Jsons.
//        ObjectMapper mapper = new ObjectMapper();
//
//        Document myDoc = new Document();
//        MongoCursor<Document> myDocs = null;
//        MongoCursor<Document> junctionDocs = null;
//        ElementEntry elem = null;
//        boolean skip = false;
//
//        // Run through each Junction to find the roads that connect to it
//        while(!junctions.isEmpty()){
//            JunctionEntry jun = junctions.pop();
//            // Get all road elements that use the junction as either a FROM or TO
//            myDocs = collection.find(or(eq("junction_FROM", jun.getjunction_ID()),eq("junction_TO", jun.getjunction_ID()))).projection(fields(excludeId())).iterator();
//            // Run through all the road elements that were found
//            while(myDocs.hasNext()){
//                myDoc = myDocs.next();
//                elem = mapper.readValue(myDoc.toJson(), ElementEntry.class);
//                // Make sure we are not included the starting road
//                if(elem.getstreetid() == start.getStreetId()){
//                    continue;
//                }
//
//                for(int i = 0; i < junctionIDs.size(); i++){
//
//
//                    // Make sure that
//
//                    if(elem.getjunction_FROM().compareTo(junctionIDs.get(i)) == 0 && elem.getjunction_TO().compareTo(junctionIDs.get(i)) == 0){
//                        skip = true;
//                        break;
//                    }
//
//                }
//
//                if(skip == true){
//                    skip = false;
//                    continue;
//                }
//
//
//                // Now we have all the elements connected to the street
//                myDoc = database.getCollection("Street_Names").find(eq("ornelement_ID", elem.getstreetid())).projection(fields(excludeId())).first();
//                street = mapper.readValue(myDoc.toJson(), StreetEntry.class);
//
//                // Find all road elements that contain the current connected street
//                junctionDocs = collection.find(eq("streetid", street.getstreetid())).projection(fields(excludeId())).iterator();
//                while(junctionDocs.hasNext()){
//                    myDoc = junctionDocs.next();
//                    elem = mapper.readValue(myDoc.toJson(), ElementEntry.class);
//
//                    // Check that the junction is not already included.
//                    junctionConnected.push(mapper.readValue(database.getCollection("Junctions").find(eq("junction_ID", elem.getjunction_FROM())).projection(fields(excludeId())).first().toJson(), JunctionEntry.class));
//                    junctionConnected.push(mapper.readValue(database.getCollection("Junctions").find(eq("junction_ID", elem.getjunction_TO())).projection(fields(excludeId())).first().toJson(), JunctionEntry.class));
//                }
//
//                connections.push(new StreetNode(start,junctionConnected, street.getstreetname(), street.getstreetid(), start.getDepth() + 1));
//            }
//        }
//        System.out.println(connections.toString());
//        mongoClient.close();
//
//        return connections;
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//    */
//
//    /**
//     * Closes the connection to MongoDB cluster
//     */
//    public void close(){
//        client.close();
//    }
//}
