package pl.sda.weather.model;

public class WeatherApi {
    private MainWeather main;
    private Wind wind;
    private long dt;
    private String name;

    public MainWeather getMainWeather() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public long getDt() {
        return dt;
    }

    public String getName() {
        return name;
    }

    public class MainWeather {
        private float temp;
        private float feels_like;
        private float temp_min;
        private float temp_max;
        private float pressure;
        private int humidity;
        private float sea_level;
        private float grnd_level;

        public float getTemp() {
            return temp;
        }

        public float getPressure() {
            return pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public float getTemp_min() {
            return temp_min;
        }

        public float getTemp_max() {
            return temp_max;
        }

        public float getFeels_like() {
            return feels_like;
        }

        public float getSea_level() {
            return sea_level;
        }

        public float getGrnd_level() {
            return grnd_level;
        }

        @Override
        public String toString() {
            return "MainWeather{" +
                    "temp=" + temp +
                    ", temp_min=" + temp_min +
                    ", temp_max=" + temp_max +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    '}';
        }
    }

    public class Wind {
        private float speed;
        private float deg;
        private float gust;

        public float getSpeed() {
            return speed;
        }

        public float getDeg() {
            return deg;
        }

        public float getGust() {
            return gust;
        }

        @Override
        public String toString() {
            return "Wind{" +
                    "windSpeed=" + speed +
                    ", windDeg=" + deg +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherApi{" +
                "main=" + main +
                ", wind=" + wind +
                ", name='" + name + '\'' +
                ", dt=" + dt +
                '}';
    }
}
