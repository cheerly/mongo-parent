package org.moon.mogon;

import static org.junit.Assert.assertEquals;

import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class DBConnectTest {

	private MongoClient mongo;
	private DB moon;
	private DBCollection users;

	@Before
	public void before() {
		try {
			mongo = new MongoClient("localhost");
			moon = mongo.getDB("moon");
			users = moon.getCollection("users");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		users.drop();
		BasicDBObject user = new BasicDBObject();
		user.put("id", 1);
		user.put("name", "test");
		users.save(user);
		assertEquals(1, users.getCount());
	}

	@After
	public void after() {
		if (mongo != null)
			mongo.close();
		mongo = null;
		moon = null;
	}
}
