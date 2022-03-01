package pl.sda.weather.service.impl;

import org.junit.jupiter.api.Test;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.model.Weather;
import pl.sda.weather.model.WeatherLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ReadWeathersImplTest {

    ReadWeathersImpl readWeathers = new ReadWeathersImpl();

    @Test
    void readWeather() {

        List<WeatherLine> expected = new ArrayList<>();

        List<WeatherLine> result = readWeathers.readWeather();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getWeatherMap() {

        Map<String, Weather> expected = new HashMap<>();

        Map<String, Weather> result = readWeathers.getWeatherMap();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void listWeathersNotNull() {

        LocationModel location1 = new LocationModel("1", "latitude: 14.22; longitude: 61.23",
                "Wieliczka", "malopolskie", "Poland");
        LocationModel location2 = new LocationModel("2", "latitude: 24.22; longitude: 41.23",
                "Warsaw", "mazowieckie", "Poland");

        List<LocationModel> citiesList = new ArrayList<>();
        citiesList.add(location1);
        citiesList.add(location2);

        Weather weather1 = new Weather((float) 264.31, (float) 1010.5, 50, 167, (float) 4.5, "Wieliczka");
        Weather weather2 = new Weather((float) 244.31, (float) 1012.5, 58, 157, (float) 11.5, "Krakow");
        Weather weather3 = new Weather((float) 244.31, (float) 1012.5, 58, 157, (float) 11.5, "Warsaw");

        Map<String,Weather> weatherMap = new HashMap<>();
        weatherMap.put("Wieliczka",weather1);
        weatherMap.put("Krakow",weather2);
        weatherMap.put("Warsaw",weather3);

        List<Weather> expectedNotNull = new ArrayList<>();
        expectedNotNull.add(weather1);
        expectedNotNull.add(weather3);

        List<Weather> result1 = readWeathers.listWeathers(citiesList,weatherMap);

        assertThat(result1).isEqualTo(expectedNotNull);
    }

    @Test
    void listWeatherNull() {
        Weather weather1 = new Weather((float) 264.31, (float) 1010.5, 50, 167, (float) 4.5, "Wieliczka");
        Weather weather2 = new Weather((float) 244.31, (float) 1012.5, 58, 157, (float) 11.5, "Krakow");
        Weather weather3 = new Weather((float) 244.31, (float) 1012.5, 58, 157, (float) 11.5, "Warsaw");

        Map<String,Weather> weatherMap = new HashMap<>();
        weatherMap.put("Wieliczka",weather1);
        weatherMap.put("Krakow",weather2);
        weatherMap.put("Warsaw",weather3);

        LocationModel location3 = new LocationModel("1", "latitude: 14.22; longitude: 61.23",
                "Wielicka", "malopolskie", "Poland");
        LocationModel location4 = new LocationModel("2", "latitude: 24.22; longitude: 41.23",
                "Warszawa", "mazowieckie", "Poland");

        List<LocationModel> wrongCitiesList = new ArrayList<>();
        wrongCitiesList.add(location3);
        wrongCitiesList.add(location4);

        List<Weather> expectedNull = new ArrayList<>();
        expectedNull.add(null);
        expectedNull.add(null);

        List<Weather> result2 = readWeathers.listWeathers(wrongCitiesList,weatherMap);

        assertThat(result2).isEqualTo(expectedNull);
    }
}