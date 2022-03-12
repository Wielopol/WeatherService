package pl.sda.weather.model;

public class Coords {
    private double lat;
    private double lon;

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "Coords{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
