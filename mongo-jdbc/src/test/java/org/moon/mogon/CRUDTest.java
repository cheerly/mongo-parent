package org.moon.mogon;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class CRUDTest {
	
	private MongoClient mongo;
	private DB moon;
	private DBCollection users;
	private DBObject user = new BasicDBObject();

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
	public void test() {
		create();
		update();
		remove();
	}

	public void create() {
		user.put("id", 1);
		user.put("name", "admin");
		user.put("email", "870757543@qq.com");
		// users.insert(user);
		users.save(user);
		assertNotEquals(null, users.findOne(new BasicDBObject("id", 1)));
	}

	public void update() {
		user = users.findOne(new BasicDBObject("id", 1));
		user.put("name", "test");
		user.put("email", "499904442@qq.com");
		users.update(new BasicDBObject("id", 1), user);
		DBObject temp = users.findOne(new BasicDBObject("id", 1));
		assertEquals("test", temp.get("name"));
		assertEquals("499904442@qq.com", temp.get("email"));
	}

	public void remove() {
		users.findAndRemove(new BasicDBObject("id", 1));
		assertEquals(0, users.getCount());
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
		user = null;
	}

}
