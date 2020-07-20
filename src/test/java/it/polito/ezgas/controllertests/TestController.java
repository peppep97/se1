package it.polito.ezgas.controllertests;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.PriceReportDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestController {

	@Test
	public void testGetUser() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet("http://localhost:8080/user/getUser/78");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UserDto userDto = mapper.readValue(jsonFromResponse, UserDto.class);

		assertEquals("testName", userDto.getUserName());
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

		User u = new User("test1", "test1", "test1@test.com", 0);

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(u);

		HttpPost request = new HttpPost("http://localhost:8080/user/saveUser");

		StringEntity entity = new StringEntity(jsonString);
		request.setEntity(entity);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());
	}

	@Test
	public void testDeleteUser() throws ClientProtocolException, IOException {

		HttpDelete request = new HttpDelete("http://localhost:8080/user/deleteUser/113");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());
	}

	@Test
	public void testIncreaseUserReputation() throws ClientProtocolException, IOException {

		HttpPost request = new HttpPost("http://localhost:8080/user/increaseUserReputation//1");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());
	}

	@Test
	public void testDecreaseUserReputation() throws ClientProtocolException, IOException {

		HttpPost request = new HttpPost("http://localhost:8080/user/decreaseUserReputation//1");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());
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

		assertEquals(200, response.getStatusLine().getStatusCode());
	}

	@Test
	public void testGetGasStation() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getGasStation/166");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto gasStationDto = mapper.readValue(jsonFromResponse, GasStationDto.class);

		assertEquals("testGas", gasStationDto.getGasStationName());
	}

	@Test
	public void testGetAllGasStations() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getAllGasStations");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gasStationList = mapper.readValue(jsonFromResponse, GasStationDto[].class);

		assertEquals(5, gasStationList.length);
	}

	@Test
	public void testSaveGasStation() throws ClientProtocolException, IOException {

		GasStation g = new GasStation("testGasPOST", "Turin Piemont Italy", true, true, true, true, true, true, "Enjoy", 45.0677551, 7.6824892, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1, null, 0);

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(g);

		HttpPost request = new HttpPost("http://localhost:8080/gasstation/saveGasStation");

		StringEntity entity = new StringEntity(jsonString);
		request.setEntity(entity);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());
	}

	@Test
	public void testDeleteGasStation() throws ClientProtocolException, IOException {
		HttpDelete request = new HttpDelete("http://localhost:8080/gasstation/deleteGasStation/231");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());
	}

	@Test
	public void testSearchGasStationByGasolineType() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/searchGasStationByGasolineType/Diesel");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gasStationList = mapper.readValue(jsonFromResponse, GasStationDto[].class);

		assertEquals(3, gasStationList.length);
	}

	@Test
	public void testSearchGasStationByProximity() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/searchGasStationByProximity/45.06/7.68/1/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gasStationList = mapper.readValue(jsonFromResponse, GasStationDto[].class);

		assertEquals(2, gasStationList.length);
	}

	@Test
	public void testGetGasStationsWithCoordinates() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getGasStationsWithCoordinates/45.06/7.68/1/Diesel/Enjoy");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gasStationList = mapper.readValue(jsonFromResponse, GasStationDto[].class);

		assertEquals(1, gasStationList.length);
	}

	@Test
	public void testSetGasStationReport() throws ClientProtocolException, IOException {

		PriceReportDto p = new PriceReportDto(70, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1);

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(p);

		HttpPost request = new HttpPost("http://localhost:8080/gasstation/setGasStationReport");

		StringEntity entity = new StringEntity(jsonString);
		request.setEntity(entity);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());
	}

}
