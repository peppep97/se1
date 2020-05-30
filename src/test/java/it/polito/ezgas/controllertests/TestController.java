package it.polito.ezgas.controllertests;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polito.ezgas.dto.UserDto;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@DataJpaTest

public class TestController {
	
	@Autowired
	UserRepository userRepo;

	User a = new User("Alice", "Alice", "alice@ezgas.com", 0);
	User b = new User("Bob", "Bob", "bob@ezgas.com", 0);
	User c = new User("Charlie", "Charlie", "charlie@ezgas.com", 0);

	Integer aId, bId, cId;

	/*@Before
	public void setUp() throws Exception{
		aId = userRepo.save(a).getUserId();
		bId = userRepo.save(b).getUserId();
		cId = userRepo.save(c).getUserId();
	}

	@After
	public void tearDown() throws Exception{
		userRepo.delete(a);
		userRepo.delete(b);
		userRepo.delete(c);
	}*/

	@Test
	public void testGetUser() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/getUser/78");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UserDto userDto = mapper.readValue(jsonFromResponse, UserDto.class);

		assertEquals("test", userDto.getUserName());
	}
	
	@Test
	public void testGetAllUsers() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/getAllUsers");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UserDto[] userList = mapper.readValue(jsonFromResponse, UserDto[].class);

		assertEquals(2, userList.length);
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
