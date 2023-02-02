package atms;

import atms.json.ATM;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/atms-list")
@Produces(MediaType.APPLICATION_JSON)
public class ATMsListResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public ATMsListResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    /*
        GeoLocation geoLocation = new GeoLocation("1", "2");
        Address address = new Address("1", "2", "3", "4", geoLocation);
        return new ATM(address, 0, "ING");
    */

    @GET
    @Timed
    @Path("/fullsearch")
    public List<ATM> getATMListFullSearch(@QueryParam("fullTextSearch") Optional<String> fullTextSearch) throws JsonProcessingException {

        final String fullSearch = (fullTextSearch.orElse("")).trim();

        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI());

        String jsonString = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
        if(jsonString==null) return new ArrayList<>();

        List<ATM> atmListOrigin = new ObjectMapper().readValue(jsonString, new TypeReference<List<ATM>>(){});
        if(atmListOrigin==null) return new ArrayList<>();

        List<ATM> atmList = atmListOrigin.stream()
                .filter(atm -> (
                   (atm.getType()!=null && atm.getType().contains(fullSearch))
                           || (atm.getDistance()!=null && atm.getDistance().toString().contains(fullSearch)) ||
                       (atm.getAddress()!=null &&
                           (atm.getAddress().getStreet()!=null && atm.getAddress().getStreet().contains(fullSearch)) ||
                           (atm.getAddress().getHousenumber()!=null && atm.getAddress().getHousenumber().contains(fullSearch)) ||
                           (atm.getAddress().getPostalcode()!=null && atm.getAddress().getPostalcode().contains(fullSearch)) ||
                           (atm.getAddress().getCity()!=null && atm.getAddress().getCity().contains(fullSearch)) ||
                           (atm.getAddress().getGeoLocation()!=null && (
                               (atm.getAddress().getGeoLocation().getLat()!=null && atm.getAddress().getGeoLocation().getLat().contains(fullSearch)) ||
                               (atm.getAddress().getGeoLocation().getLng()!=null && atm.getAddress().getGeoLocation().getLng().contains(fullSearch))
                           )
                        ))
                ))
                .collect(Collectors.toList());

        return atmList!=null ? atmList : new ArrayList<>();
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("https://www.dropbox.com/s/6fg0k2wxwrheyqk/ATMs?dl=1").build();
    }

    @GET
    @Timed
    @Path("/searchbyfield")
    public List<ATM> getATMListSearchbyField(
            @QueryParam("type") Optional<String> type,
            @QueryParam("distance") Optional<Integer> distance,
            @QueryParam("street") Optional<String> street,
            @QueryParam("housenumber") Optional<String> housenumber,
            @QueryParam("postalcode") Optional<String> postalcode,
            @QueryParam("city") Optional<String> city,
            @QueryParam("lat") Optional<String> lat,
            @QueryParam("lng") Optional<String> lng
    ) throws JsonProcessingException {

        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(getBaseURI());

        String jsonString = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
        if(jsonString==null) return new ArrayList<>();

        List<ATM> atmListOrigin = new ObjectMapper().readValue(jsonString, new TypeReference<List<ATM>>(){});
        if(atmListOrigin==null) return new ArrayList<>();

        List<ATM> atmList = atmListOrigin.stream()
                .filter(atm -> (type.isPresent() && atm.getType()!=null) ? atm.getType().contains(type.get().trim()) : true)
                .filter(atm -> (distance.isPresent() && atm.getDistance()!=null) ? atm.getDistance()==distance.get() : true)
                .filter(atm -> (street.isPresent() && atm.getAddress()!=null && atm.getAddress().getStreet()!=null) ? atm.getAddress().getStreet().contains(street.get()) : true)
                .filter(atm -> (housenumber.isPresent() && atm.getAddress()!=null && atm.getAddress().getHousenumber()!=null) ? atm.getAddress().getHousenumber().equals(housenumber.get()) : true)
                .filter(atm -> (postalcode.isPresent() && atm.getAddress()!=null && atm.getAddress().getPostalcode()!=null) ? atm.getAddress().getPostalcode().contains(postalcode.get()) : true)
                .filter(atm -> (city.isPresent() && atm.getAddress()!=null && atm.getAddress().getCity()!=null) ? atm.getAddress().getCity().contains(city.get()) : true)
                .filter(atm -> (lat.isPresent() && atm.getAddress()!=null && atm.getAddress().getGeoLocation()!=null) && atm.getAddress().getGeoLocation().getLat()!=null ? atm.getAddress().getGeoLocation().getLat().equals(lat.get()) : true)
                .filter(atm -> (lng.isPresent() && atm.getAddress()!=null && atm.getAddress().getGeoLocation()!=null) && atm.getAddress().getGeoLocation().getLng()!=null ? atm.getAddress().getGeoLocation().getLat().equals(lng.get()) : true)
                .collect(Collectors.toList());

        return atmList!=null ? atmList : new ArrayList<>();
    }

}