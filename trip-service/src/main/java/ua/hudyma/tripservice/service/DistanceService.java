package ua.hudyma.tripservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ua.hudyma.tripservice.domain.City;

@Component
@ConfigurationProperties(prefix = "graphhopper.api")
public class DistanceService {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static String staticKey;
    @Value("${graphhopper.api.key}")
    private String key;

    public void setKey(String key) {
        this.key = key;
        DistanceService.staticKey = key; // ← передаємо в static
    }

    public static String getStaticKey() {
        return staticKey;
    }

    public static double getDistance(City departure, City destination) {
        String key = getStaticKey(); // ← буде ініціалізовано після старту

        String url = UriComponentsBuilder.fromHttpUrl("https://graphhopper.com/api/1/route")
                .queryParam("point", departure.getLatitude() + "," + departure.getLongitude())
                .queryParam("point", destination.getLatitude() + "," + destination.getLongitude())
                .queryParam("vehicle", "car")
                .queryParam("locale", "uk")
                .queryParam("calc_points", "false")
                .queryParam("key", key)
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

