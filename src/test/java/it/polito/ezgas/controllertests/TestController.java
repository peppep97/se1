package it.polito.ezgas.controllertests;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestController {
	
	@Autowired
	UserRepository userRepo;
	
	@Before
	public void setUp() {
		
		User a = new User("Alice", "Alice", "alice@ezgas.com", 0);
		User b = new User("Bob", "Bob", "bob@ezgas.com", 0);
		User c = new User("Charlie", "Charlie", "charlie@ezgas.com", 0);
		a.setUserId(2);
		b.setUserId(3);
		c.setUserId(4);
		userRepo.save(a);
		userRepo.save(b);
		userRepo.save(b);
	}
	
	@Test
	public void testGetUser() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/getUser/2");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		assert(jsonFromResponse.contains("Alice"));
	}
	
	@Test
	public void testGetAllUser() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/getAllUser");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		assert(jsonFromResponse.contains("Alice"));
		assert(jsonFromResponse.contains("Bob"));
		assert(jsonFromResponse.contains("Charlie"));
	}
	
	@Test
	public void testSaveUser() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/saveUser");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assert(response.getStatusLine().getStatusCode() == 200);
	}
	
	@Test
	public void testDeleteUser() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/deleteUser");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assert(response.getStatusLine().getStatusCode() == 200);
	}
	
	@Test
	public void testIncreaseUserReputation() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/increaseUserReputation");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assert(response.getStatusLine().getStatusCode() == 200);
	}
	
	@Test
	public void testDecreaseUserReputation() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/decreaseUserReputation");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assert(response.getStatusLine().getStatusCode() == 200);
	}

}
