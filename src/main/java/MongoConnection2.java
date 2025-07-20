package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoConnection2{
    private static final String connection_String = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "library_db";
    private static final String COLLECTION_NAME = "Book";
    private static MongoClient mongoClient = null;

    //Connection setup...
    public static MongoCollection<Document> getBookCollection(){
        if (mongoClient == null){
            mongoClient = MongoClients.create(connection_String);
        }
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        return database.getCollection(COLLECTION_NAME);
    }

    //Connection Close...
    public static void close_connection(){
        if (mongoClient !=null){
            mongoClient.close();
        }
    }
}