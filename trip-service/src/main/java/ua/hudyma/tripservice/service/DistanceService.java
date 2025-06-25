package ua.hudyma.tripservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ua.hudyma.tripservice.domain.City;

public class DistanceService {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String API_KEY = "d6be4184-80a7-4db4-8c22-a3540a16670c";

    public static double getDistance(City departure, City destination) {
        var coorDep = departure.getLatitude()+","+departure.getLongitude();
        var coorDest = destination.getLatitude()+","+ destination.getLongitude();
        String url = UriComponentsBuilder.fromHttpUrl("https://graphhopper.com/api/1/route")
                .queryParam("point", coorDep)
                .queryParam("point", coorDest)
                .queryParam("vehicle", "car")
                .queryParam("locale", "uk")
                .queryParam("calc_points", "false") // повертати масив GPS-точок, які формують шлях
                .queryParam("key", API_KEY)
                .toUriString();

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        double distanceInMeters = response.getBody()
                .get("paths")
                .get(0)
                .get("distance")
                .asDouble();

        return distanceInMeters / 1000; // в кілометрах
    }
}

