package pl.sda.weather.model;

public class Weather {
    private final float temperature;
    private final float pressure;
    private final int humidity;
    private final float windDir;
    private final float windSpeed;

    public Weather(float temperature, float pressure, int humidity, float windDir, float windSpeed) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windDir = windDir;
        this.windSpeed = windSpeed;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public float getWindDir() {
        return windDir;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windDir=" + windDir +
                ", windSpeed=" + windSpeed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weather weather = (Weather) o;

        if (Float.compare(weather.temperature, temperature) != 0) return false;
        if (Float.compare(weather.pressure, pressure) != 0) return false;
        if (humidity != weather.humidity) return false;
        if (Float.compare(weather.windDir, windDir) != 0) return false;
        return Float.compare(weather.windSpeed, windSpeed) == 0;
    }

    @Override
    public int hashCode() {
        int result = (temperature != +0.0f ? Float.floatToIntBits(temperature) : 0);
        result = 31 * result + (pressure != +0.0f ? Float.floatToIntBits(pressure) : 0);
        result = 31 * result + humidity;
        result = 31 * result + (windDir != +0.0f ? Float.floatToIntBits(windDir) : 0);
        result = 31 * result + (windSpeed != +0.0f ? Float.floatToIntBits(windSpeed) : 0);
        return result;
    }
}
