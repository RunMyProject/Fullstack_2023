package atms.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ATM {

    private Address address;
    private Integer distance;
    private String type;

    public ATM() {

    }

    public ATM(Address address, Integer distance, String type) {
        // Jackson deserialization
        //
        this.address = address;
        this.distance = distance;
        this.type = type;
    }

    @JsonProperty
    public Address getAddress() {
        return address;
    }

    @JsonProperty
    public Integer getDistance() {
        return distance;
    }

    @JsonProperty
    public String getType() {
        return type;
    }

}