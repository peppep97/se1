package it.polito.ezgas.controllertests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
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

import static com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer.Vanilla.std;
import static org.junit.Assert.assertEquals;

public class TestController {

	@Test
	public void testGetUser() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/getUser/78");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UserDto userDto = mapper.readValue(jsonFromResponse, UserDto.class);

		assert(userDto.getUserName().equals("test"));
	}
	
	@Test
	public void testGetAllUsers() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/getAllUsers");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UserDto[] userList = mapper.readValue(jsonFromResponse, UserDto[].class);

		assert(userList.length == 2);
	}
	
	@Test
	public void testSaveUser() throws ClientProtocolException, IOException {

		User u = new User("test1", "test1", "test1@test.com", 0);

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(u);

		HttpPost request = new HttpPost("http://localhost:8080/user/saveUser");

		StringEntity entity = new StringEntity(jsonString);
		request.setEntity(entity);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assert(response.getStatusLine().getStatusCode() == 200);
	}
	
	@Test
	public void testDeleteUser() throws ClientProtocolException, IOException {

		HttpDelete request = new HttpDelete("http://localhost:8080/user/deleteUser/113");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assert(response.getStatusLine().getStatusCode() == 200);
	}
	
	@Test
	public void testIncreaseUserReputation() throws ClientProtocolException, IOException {

		HttpPost request = new HttpPost("http://localhost:8080/user/increaseUserReputation//1");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assert(response.getStatusLine().getStatusCode() == 200);
	}
	
	@Test
	public void testDecreaseUserReputation() throws ClientProtocolException, IOException {

		HttpPost request = new HttpPost("http://localhost:8080/user/decreaseUserReputation//1");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assert(response.getStatusLine().getStatusCode() == 200);
	}

	@Test
	public void testLogin() throws ClientProtocolException, IOException {

		IdPw credentials = new IdPw("test@test.com", "test");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(credentials);

		HttpPost request = new HttpPost("http://localhost:8080/user/login");

		StringEntity entity = new StringEntity(jsonString);
		request.setEntity(entity);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assert(response.getStatusLine().getStatusCode() == 200);
	}

	@Test
	public void testGetGasStation() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getGasStation/166");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto gasStationDto = mapper.readValue(jsonFromResponse, GasStationDto.class);

		assert(gasStationDto.getGasStationName().equals("testGas"));
	}

	@Test
	public void testGetAllGasStations() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getAllGasStations");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gasStationList = mapper.readValue(jsonFromResponse, GasStationDto[].class);

		assert(gasStationList.length == 5);
	}

	@Test
	public void testSaveGasStation() throws ClientProtocolException, IOException {

		GasStation g = new GasStation("testGasPOST", "Turin Piemont Italy", true, true, true, true, true, "Enjoy", 45.0677551, 7.6824892, -1, -1, -1, -1, -1, -1, null, 0);

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(g);

		HttpPost request = new HttpPost("http://localhost:8080/gasstation/saveGasStation");

		StringEntity entity = new StringEntity(jsonString);
		request.setEntity(entity);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assert(response.getStatusLine().getStatusCode() == 200);
	}

	@Test
	public void testDeleteGasStation() throws ClientProtocolException, IOException {
		HttpDelete request = new HttpDelete("http://localhost:8080/gasstation/deleteGasStation/231");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assert(response.getStatusLine().getStatusCode() == 200);
	}

	@Test
	public void testSearchGasStationByGasolineType() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/searchGasStationByGasolineType/Diesel");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gasStationList = mapper.readValue(jsonFromResponse, GasStationDto[].class);

		assert(gasStationList.length == 3);
	}

	@Test
	public void testSearchGasStationByProximity() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/searchGasStationByProximity/45.06/7.68/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gasStationList = mapper.readValue(jsonFromResponse, GasStationDto[].class);

		assert(gasStationList.length == 2);
	}

	@Test
	public void testGetGasStationsWithCoordinates() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getGasStationsWithCoordinates/45.06/7.68/Diesel/Enjoy");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gasStationList = mapper.readValue(jsonFromResponse, GasStationDto[].class);

		assert(gasStationList.length == 1);
	}

	@Test
	public void testSetGasStationReport() throws ClientProtocolException, IOException {

		HttpPost request = new HttpPost("http://localhost:8080/gasstation/setGasStationReport/198/1/1/1/1/1/1/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assert(response.getStatusLine().getStatusCode() == 200);
	}

}
