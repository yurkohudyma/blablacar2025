package ua.hudyma.tripservice.util;

import ua.hudyma.tripservice.domain.City;

public class DistanceCalculator {
    private static final double EARTH_RADIUS_KM = 6371;

    public static double haversine(City departure, City destination) {
        var lat1 = departure.getLatitude();
        var lat2 = destination.getLatitude();
        var lon1 =  departure.getLongitude();
        var lon2 = destination.getLongitude();
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.pow(Math.sin(dLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}

