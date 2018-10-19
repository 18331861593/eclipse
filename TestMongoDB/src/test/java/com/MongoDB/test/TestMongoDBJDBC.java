package com.MongoDB.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongoDB.utils.DateUtils;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

public class TestMongoDBJDBC {

	MongoClient client = null;

	MongoDatabase db = null;

	MongoCollection<Document> collection = null;

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Before
	public void start() {
		client = new MongoClient("localhost", 27017);
		db = client.getDatabase("guns");
		collection = db.getCollection("notice");
	}

	/**
	 * 测试 insert 方法
	 */
	@Test
	public void testInsert() {
		Document doc = new Document("title", "hello world").append("type", "").append("content", "<p>hello world </p>")
				.append("createtime", DateUtils.getDate("yyyy-MM-dd HH:mm:ss")).append("create", 1);
		collection.insertOne(doc);
	}

	@Test
	public void testInsertMany() {
		Document doc = new Document("title", "hello world").append("type", "").append("content", "<p>hello world </p>")
				.append("createtime", DateUtils.getDate("yyyy-MM-dd HH:mm:ss")).append("create", 1);
		
		Document doc1 = new Document("title", "你好").append("type", "10").append("content", "<p>欢迎使用Guns管理系统</p>")
				.append("createtime", DateUtils.getDate("yyyy-MM-dd HH:mm:ss")).append("create", 1);

		Document doc2 = new Document("title", "世界").append("type", 11).append("content", "<p>hello world </p>")
				.append("createtime", DateUtils.getDate("yyyy-MM-dd HH:mm:ss")).append("create", 1);

		List<Document> list = new ArrayList<Document>();
		list.add(doc);
		list.add(doc1);
		list.add(doc2);
		collection.insertMany(list);
	}

	@Test
	public void testCount(){
		System.out.println(collection.count());
	}
	
	
	@Test
	public void testDelete(){
		
		DeleteResult dr  = collection.deleteOne(Filters.and(Filters.eq("_id", new ObjectId("5af5397ccb77051ef850709f"))));
		
//		DeleteResult dr = collection.deleteOne(new Document().append("_id" , "ObjectId(\"5af5397ccb77051ef850709f\")"));
		System.out.println("删除条数 ： " + dr.getDeletedCount());
	}
	
	@Test
	public void testDeleteAll (){
		DeleteResult dr = collection.deleteMany(new Document());
		System.out.println("删除条数 ： " + dr.getDeletedCount());
	}
	
	
	

	@After
	public void end() throws Exception {
		if (client != null)
			client.close();
	}

}
