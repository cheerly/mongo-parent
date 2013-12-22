package org.moon.mogon.query;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class QueryTest {
	
	private MongoClient mongo;
	private DB moon;
	private DBCollection users;

	@Before
	public void before() {
		try {
			mongo = new MongoClient("localhost");
			moon = mongo.getDB("moon");
			users = moon.getCollection("users");
			users.drop();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test(){
		init();
		doQuery();
	}
	
	public void doQuery() {
		DBCursor cur = users.find(new BasicDBObject("id",1));
		while(cur.hasNext()){
			System.out.println(((Date)cur.next().get("createdTime")));
		}
	}

	public void init() {
		List<DBObject> userList = new ArrayList<DBObject>();
		userList.add(new BasicDBObject("id",1).append("name", "mary").append("email", "mary@abc.com").append("createdTime", new Date()));
		userList.add(new BasicDBObject("id",2).append("name", "david").append("email", "david@abc.com").append("createdTime", new Date()));
		userList.add(new BasicDBObject("id",3).append("name", "joy").append("email", "joy@abc.com").append("createdTime", new Date()));
		userList.add(new BasicDBObject("id",4).append("name", "marck").append("email", "marck@abc.com").append("createdTime", new Date()));
		userList.add(new BasicDBObject("id",5).append("name", "lucy").append("email", "lucy@abc.com").append("createdTime", new Date()));
		userList.add(new BasicDBObject("id",6).append("name", "jone").append("email", "jone@abc.com").append("createdTime", new Date()));
		userList.add(new BasicDBObject("id",7).append("name", "tom").append("email", "tom@abc.com").append("createdTime", new Date()));
		userList.add(new BasicDBObject("id",8).append("name", "aris").append("email", "aris@abc.com").append("createdTime", new Date()));
		userList.add(new BasicDBObject("id",9).append("name", "bras").append("email", "bras@abc.com").append("createdTime", new Date()));
		userList.add(new BasicDBObject("id",10).append("name", "cricy").append("email", "cricy@abc.com").append("createdTime", new Date()));
		users.insert(userList);
	}

	@After
	public void after() {
		users.drop();
		moon.dropDatabase();
		if (mongo != null)
			mongo.close();
		mongo = null;
		moon = null;
		users = null;
	}
	
}
