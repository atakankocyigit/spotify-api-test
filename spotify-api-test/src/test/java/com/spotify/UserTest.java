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

public class UserTest extends RequestSpec{
	
	ResponseSpec responseSpecInstance = new ResponseSpec();
	int limit=3;
	
	@Test
	public void getCurrentUserProfile() {
		given()
			.spec(super.getRequestSpec())
		.when()
			.get("/me")
		.then()
			.spec(responseSpecInstance.getResponseSpec(200))
			.contentType(ContentType.JSON)
			.assertThat()
			.body("type", equalTo("user"));
	}
	
	@Test(dataProvider = "addPlaylist", dataProviderClass = TestUtilities.class)
	public void followPlaylist(String playlistId) {
		given()
			.spec(super.getRequestSpec())
			.pathParam("playlistId", playlistId)
		.when()
			.put("playlists/{playlistId}/followers")
		.then()
			.spec(responseSpecInstance.getResponseSpec(200));
	}
	
	@Test(dataProvider = "checkPlaylist", dataProviderClass = TestUtilities.class)
	public void checkFollowedPlaylist(String playlistId) {
		
		Response res = given()
			.spec(super.getRequestSpec())
			.queryParam("limit", limit)
		.when()
			.get("/me/playlists")
		.then()
			.spec(responseSpecInstance.getResponseSpec(200))
			.extract()
			.response();

		List<String> playlists= res.jsonPath().getList("items.id");
		
		assertThat(playlists, hasItems(playlistId.split(",")));
		assertThat(res.path("limit"), is(equalTo(limit)));
	}
	
	@Test(dataProvider = "addArtist", dataProviderClass = TestUtilities.class)
	public void followArtist(String type, String followedIds) {

		given()
			.spec(super.getRequestSpec())
			.queryParams("type", type)
			.queryParam("ids", followedIds)
		.when()
			.put("/me/following")
		.then()
			.spec(responseSpecInstance.getResponseSpec(204));
	}
	
	@Test(dataProvider = "checkArtist", dataProviderClass = TestUtilities.class)
	public void checkFollowedArtist(String type, String ids, Boolean[] expectedFollowed) {
		
		Response res = given()
			.spec(super.getRequestSpec())
			.queryParam("type", type)
			.queryParam("ids", ids)
		.when()
			.get("/me/following/contains")
		.then()
			.spec(responseSpecInstance.getResponseSpec(200))
			.extract()
			.response();
		
		List<Boolean> actualFollowed = res.path("");
		//	List<Boolean> actualFollowed = res.body().as(List.class);
		//	(ArrayList<Object>) res.jsonPath().getList("");
		
		assertThat(actualFollowed, hasSize(expectedFollowed.length));
		assertThat(actualFollowed, contains(expectedFollowed));
	}
	
	@Test
	public void getUsersInformation() {
		
		String userId = "serhatyuna_";
		Response res = given()
							.spec(super.getRequestSpec())
							.pathParam("userId", userId)
						.when()
							.get("/users/{userId}")
						.then()
							.spec(responseSpecInstance.getResponseSpec(200))
							.extract()
							.response();
							
		assertThat(res.path("id"), is(equalTo(userId)));
		assertThat(res.path("type"), is(equalTo("user")));
		assertThat(res.path("display_name"), is(equalTo("serhatyuna")));
	}
}
