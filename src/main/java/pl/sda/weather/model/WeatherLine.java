package pl.sda.weather.model;

import java.util.Arrays;

public class WeatherLine {
    private City city;
    private long time;
    private MainWeather main;
    private Wind wind;
    private Clouds clouds;
    private Desc[] weather;

    public City getCity() {
        return city;
    }

    public long getTime() {
        return time;
    }

    public MainWeather getMainWeather() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Desc[] getWeather() {
        return weather;
    }

    public class City {
        private long id;
        private String name;
        private String findname;
        private String country;
        private Coord coord;
        private int zoom;

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getFindname() {
            return findname;
        }

        public String getCountry() {
            return country;
        }

        public Coord getCoord() {
            return coord;
        }

        public int getZoom() {
            return zoom;
        }

        private class Coord {
            private double lon;
            private double lat;

            public double getLon() {
                return lon;
            }

            public double getLat() {
                return lat;
            }

            @Override
            public String toString() {
                return "Coord{" +
                        "lon=" + lon +
                        ", lat=" + lat +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "City{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", findname='" + findname + '\'' +
                    ", country='" + country + '\'' +
                    ", coord=" + coord +
                    ", zoom=" + zoom +
                    '}';
        }
    }

    public class MainWeather {
        private float temp;
        private float pressure;
        private int humidity;
        private float temp_min;
        private float temp_max;

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

        @Override
        public String toString() {
            return "MainWeather{" +
                    "temp=" + temp +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", tempMin=" + temp_min +
                    ", tempMax=" + temp_max +
                    '}';
        }
    }

    public class Wind {
        private float speed;
        private float deg;

        public float getSpeed() {
            return speed;
        }

        public float getDeg() {
            return deg;
        }

        @Override
        public String toString() {
            return "Wind{" +
                    "windSpeed=" + speed +
                    ", windDeg=" + deg +
                    '}';
        }
    }

    public class Clouds {
        private int all;

        public int getAll() {
            return all;
        }

        @Override
        public String toString() {
            return "Clouds{" +
                    "all=" + all +
                    '}';
        }
    }

    public class Desc {
        private int id;
        private String main;
        private String description;
        private String icon;

        public int getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }

        @Override
        public String toString() {
            return "Desc{" +
                    "id=" + id +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherLine{" +
                "city=" + city +
                ", time=" + time +
                ", main=" + main +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", weather=" + Arrays.toString(weather) +
                '}';
    }
}
