package com.spotify;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;

import com.spotify.spec.RequestSpec;
import com.spotify.spec.ResponseSpec;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ShowsTest extends RequestSpec{
	
	ResponseSpec responseSpecInstance = new ResponseSpec();
	String showId = "7mPYdMnKMESL3gAhzzXIVE";
	int limit = 5;
	int offset = 3;
	
	@Test
	public void getSeveralShow() {
		
		Response res=  given()
			.spec(super.getRequestSpec())
			.queryParam("ids", showId)
		.when()
			.get("/shows")
		.then()
			.spec(responseSpecInstance.getResponseSpec(200))
			.assertThat()
			.contentType(ContentType.JSON)
			.extract()
			.response();
		
		assertThat(res.path("shows[0].id"), is(equalTo(showId)));
	}
	
	@Test
	public void getShowEpisodes() {
		Response res = given()
			.spec(super.getRequestSpec())
			.pathParam("showId", showId)
			.queryParam("limit", limit)
			.queryParam("offset", offset)
		.when()
			.get("/shows/{showId}/episodes")
		.then()
			.spec(responseSpecInstance.getResponseSpec(200))
			.assertThat()
			.contentType(ContentType.JSON)
			.extract()
			.response();
		
		assertThat(res.path("limit"), is(equalTo(limit)));
		assertThat(res.path("offset"), is(equalTo(offset)));
	}
}
