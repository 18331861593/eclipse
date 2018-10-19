package com.mongoDB;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBJDBC {
	public static void main(String[] args) {
		try {
			MongoClient client = new MongoClient("localhost", 27017);
			MongoDatabase db = client.getDatabase("guns");
//			db.createCollection("test");
			System.out.println(db.getName());
			System.out.println();

			MongoCollection<Document> collection = db.getCollection("test");
			Document document = new Document("title", "MongoDB").append("description", "database").append("likes", 100)
					.append("by", "Fly");
			
			collection.insertOne(document);
			
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
}
