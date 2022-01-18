package com.spotify.data;

import org.testng.annotations.DataProvider;

public class TestUtilities{
	@DataProvider(name="addPlaylist")
	protected static Object[][] addPlaylist(){
		return new Object[][] 
			{{"37i9dQZF1DX9tzt7g58Xlh"}, 
			{"37i9dQZF1DX5KpP2LN299J"},
			{"37i9dQZF1DWWylYLMvjuRG"}};
	}
	
	@DataProvider(name="checkPlaylist")
	protected static Object[][] checkplaylist(){
		return new Object[][] 
			{{"37i9dQZF1DX9tzt7g58Xlh,37i9dQZF1DX5KpP2LN299J"}, 
			{"37i9dQZF1DWWylYLMvjuRG"}};
	}
	
	@DataProvider(name="addArtist")
	protected static Object[][] addArtist(){
		return new Object[][] 
			{{"artist", "06HL4z0CvFAxyc27GXpf02,53XhwfbYqKCa1cC15pYq2q"},
			{"artist", "1Xyo4u8uXC1ZmMpatF05PJ"}};
	}
	
	@DataProvider(name="checkArtist")
		protected static Object[][] checkArtist(){
			return new Object[][] {
				{"artist", "06HL4z0CvFAxyc27GXpf02", new Boolean []{true}},
				{"artist", "53XhwfbYqKCa1cC15pYq2q,7dGJo4pcD2V6oG8kP0tJRR", new Boolean[] {true,false}},
				{"artist", "0C8ZW7ezQVs4URX5aX7Kqx,6VuMaDnrHyPL1p4EHjYLi7", new Boolean[] {false,false}},
				{"artist", "1Xyo4u8uXC1ZmMpatF05PJ", new Boolean[] {true}}
				};
	}
	
	@DataProvider(name="addTrackList")
		protected static Object[][] addTrackList(){
			return new Object [][] {
				{"7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B"},
				{"6qYkmqFsXbj8CQjAdbYz07,5xTtaWoae3wi06K5WfVUUH"},
				{"4IWAyPf1KMq7JCyGeCjTeH"}
			};
	}
	
	@DataProvider(name="deleteTrackList")
	protected static Object[][] deleteTrackList(){
		return new Object [][] {
			{"6qYkmqFsXbj8CQjAdbYz07,5xTtaWoae3wi06K5WfVUUH"}
		};
	}
	
	@DataProvider(name="checkTrackList")
	protected static Object[][] checkTrackList(){
		return new Object [][] {
			{"7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B,4IWAyPf1KMq7JCyGeCjTeH"},
		};
	}
}
