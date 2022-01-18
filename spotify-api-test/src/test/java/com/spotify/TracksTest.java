package com.spotify;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.annotations.Test;

import com.spotify.data.TestUtilities;
import com.spotify.spec.RequestSpec;
import com.spotify.spec.ResponseSpec;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TracksTest extends RequestSpec{
	
	ResponseSpec responseSpecInstance = new ResponseSpec();
	
	@Test(dataProvider = "addTrackList", dataProviderClass = TestUtilities.class, priority = 1)
	public void saveTracks(String trackIds) {
		
		given()
			.spec(super.getRequestSpec())
			.queryParam("ids", trackIds)
		.when()
			.put("/me/tracks")
		.then()
			.spec(responseSpecInstance.getResponseSpec(200));
	}	
	
	@Test(dataProvider = "deleteTrackList", dataProviderClass = TestUtilities.class, priority = 2)
	public void deleteUsersTracks(String trackIds) {
		given()
			.spec(super.getRequestSpec())
			.queryParam("ids", trackIds)
		.when()
			.delete("/me/tracks")
		.then()
			.spec(responseSpecInstance.getResponseSpec(200));
	}
	
	@Test(dataProvider = "checkTrackList", dataProviderClass = TestUtilities.class, priority = 3)
	public void checkUsersSavedTracks(String trackIds) {
		
		String[] expectedTracks =	trackIds.split(",");
		
		Response res = given()
							.spec(super.getRequestSpec())
							.queryParam("ids", trackIds)
						.when()
							.get("/me/tracks")
						.then()
							.spec(responseSpecInstance.getResponseSpec(200))
							.assertThat()
							.contentType(ContentType.JSON)
							.extract()
							.response();
		
		List<Object> actualTracks = res.path("items.track.id");
		
		assertThat(actualTracks, containsInAnyOrder(expectedTracks));
	}
}
