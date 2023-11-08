/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package bcs.csc.lab14_mongodbdemo;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Brian
 */
public class Lab14_MongoDBDemo {

    public static void main(String[] args) {
        // Disable MongoDB Logs
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        createTable();
        addRecords();
        deleteRecords();
//        searchRecords();
//        updateRecords();
        listRecords();
    }

    // Empty Table does not show in MongoDB Compass
    public static void createTable() {
        System.out.println("Create Table =====");
        // Try Catch is not required
        // Create a Mongo Client
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        // Connect to the database
        MongoDatabase database = mongoClient.getDatabase("CoffeeDB");
        // Create or get a collection
        MongoCollection<Document> collection = database.getCollection("coffee");
        System.out.println("Coffee table created or connected");

    }

    public static void addRecords() {
        System.out.println("Add Records =====");
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("CoffeeDB");
        MongoCollection<Document> collection = database.getCollection("coffee");
        // Append is better than put
        // Add a record with append or put method
        Document document = new Document();
        document.append("description", "Honduran Dark");
        document.append("prodNum", "22-001");
        document.append("price", 8.65);
        collection.insertOne(document);
        // Add a record with user-generated ID
        ObjectId generatedId2 = ObjectId.get();
        Document document2 = new Document("_id", generatedId2);
        document2.put("description", "Kona Dark");
        document2.put("prodNum", "18-002");
        document2.put("price", 18.45);
        collection.insertOne(document2);

        System.out.println("Records added");
    }

    public static void deleteRecords() {
        System.out.println("Delete Records =====");
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("CoffeeDB");
        MongoCollection<Document> collection = database.getCollection("coffee");
        //Delete document
        Document document = new Document();
        document.put("description", "Honduran Dark");
        //Delete one document
        collection.deleteOne(document);
        //Delete multiple documents
        collection.deleteMany(document);

        Document document2 = new Document("description", "Kona Dark");
        collection.deleteMany(document2);
    }

    public static void searchRecords() {
        System.out.println("Search Records =====");
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("CoffeeDB");
        MongoCollection<Document> collection = database.getCollection("coffee");
        // Create a search document
        Document searchDocument = new Document("description",
                "Java Medium");
        // Search for the first document that matches the condition
        Document foundDocument = collection.find().first();
        // Display the found document in Json
        System.out.println("Document found: " + foundDocument.toJson());
    }

    public static void updateRecords() {
        System.out.println("Update Records =====");
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("CoffeeDB");
        MongoCollection<Document> collection = database.getCollection("coffee");
        // Update documents that match the condition
        Document oldDocument = new Document(
                "description", "Kona Dark");
        Document newDocument = new Document(
                "description", "Columbia Dark");
        Document updateDocument = new Document("$set", newDocument);
        // Update one document
        collection.updateOne(oldDocument, updateDocument);
        // Update multiple documents
        collection.updateMany(oldDocument, updateDocument);
    }

    public static void listRecords() {
        System.out.println("List Records =====");
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("CoffeeDB");
        MongoCollection<Document> collection = database.getCollection("coffee");
        // List all the documents in a collection using lambda expression
        FindIterable<Document> iterable = collection.find();
        iterable.forEach((Block<Document>) (Document document) -> {
            System.out.println(document.toJson());
        });
    }

}
