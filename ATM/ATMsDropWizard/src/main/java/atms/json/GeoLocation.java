package atms.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoLocation {

	@JsonProperty
	private String lat;

	@JsonProperty
	private String lng;

	public GeoLocation() {

	}

	public GeoLocation(String lat, String lng)  {
		this.lat = lat;
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public String getLng() {
		return lng;
	}
}
