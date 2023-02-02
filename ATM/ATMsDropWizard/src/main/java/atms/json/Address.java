package atms.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

	private String street;
	private String housenumber;
	private String postalcode;
	private String  city;
	private GeoLocation geoLocation;

	public Address() {

	}

	public Address(String street, String housenumber, String postalcode, String city, GeoLocation geoLocation) {
		this.street = street;
		this.housenumber = housenumber;
		this.postalcode = postalcode;
		this.city = city;
		this.geoLocation = geoLocation;
	}

	@JsonProperty
	public String getStreet() {
		return street;
	}

	@JsonProperty
	public String getHousenumber() {
		return housenumber;
	}

	@JsonProperty
	public String getPostalcode() {
		return postalcode;
	}

	@JsonProperty
	public String getCity() {
		return city;
	}

	@JsonProperty
	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

}