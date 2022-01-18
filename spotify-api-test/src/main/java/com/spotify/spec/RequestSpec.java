package com.spotify.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {
	
	private static String baseUri="https://api.spotify.com";
	private static String basePath="/v1";
	private static String accessToken="BQDalIWFphO0sYFABDHYsLBSS5Jhel21y0rVs0oTqjOoVD1VEvFvIR3WULDOTYdxB5QaQr4Rr0JbGcdriq0it1QkCO-0UlfDgGlEh9e2LDq-cPTO4XXjLC3v6pmHMp1D2HKZ-9HVCGeI3veJvsxM2yORoWhyMtXhc8Rpr-2Jg3JnoGaxJL6MKsJvKjSATiVFrNmF7Gth-2n79JeYhPfg3jr6MBGjxEvAcTdN1y7nrmBsOxVDlw37CRZbG0RTdAjweI53jR-C_9_wyC7r";

	public RequestSpecification getRequestSpec() {
		return new RequestSpecBuilder()
						.setBaseUri(baseUri)
						.setBasePath(basePath)
						.addHeader("Authorization", "Bearer " + accessToken)
						.setContentType(ContentType.JSON)
						.log(LogDetail.ALL)
						.build();
	}
}
