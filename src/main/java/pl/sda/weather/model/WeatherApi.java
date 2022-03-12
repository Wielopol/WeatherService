package pl.sda.weather.model;

import java.util.Arrays;

public class WeatherApi {
    private double lon;
    private double lat;
    private MainWeather[] daily;

    public class MainWeather {
        private long dt;
        private Temp temp;
        private float pressure;
        private int humidity;
        private float wind_speed;
        private float wind_deg;

        public class Temp {
            private float day;

            public float getDayTemp() {
                return day;
            }

            @Override
            public String toString() {
                return "Temp{" +
                        "temp=" + day +
                        '}';
            }
        }

        public long getDt() {
            return dt;
        }

        public Temp getTemp() {
            return temp;
        }

        public float getPressure() {
            return pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public float getWind_speed() {
            return wind_speed;
        }

        public float getWind_deg() {
            return wind_deg;
        }

        @Override
        public String toString() {
            return "MainWeather{" +
                    "dt=" + dt +
                    ", temp=" + temp +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", speed=" + wind_speed +
                    ", deg=" + wind_deg +
                    '}';
        }
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public MainWeather[] getDaily() {
        return daily;
    }

    @Override
    public String toString() {
        return "WeatherApi{" +
                "lon=" + lon +
                ", lat=" + lat +
                ", daily=" + Arrays.toString(daily) +
                '}';
    }
}
