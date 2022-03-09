package pl.sda.weather.service.impl;

import org.junit.jupiter.api.Test;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.model.Weather;
import pl.sda.weather.repository.IWeatherRepository;
import pl.sda.weather.repository.impl.WeatherRepositoryImpl;
import pl.sda.weather.service.IWeatherService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class IReadWeathersServiceImplTest {

    IWeatherRepository weatherRepository = new WeatherRepositoryImpl();
    IWeatherService weatherService = new WeatherServiceImpl();

//    @Test
//    void getWeatherMapNotNull() {
//
//        Map<String, Weather> result = weatherRepository.getWeatherMap();
//
//        Weather weather = result.get("Zatory");
//
//        Weather expected = new Weather((float) 291.78, (float) 1010.0, 61, 120, (float) 6.2, "Zatory");
//
//        assertThat(weather).isEqualTo(expected);
//    }
//
//    @Test
//    void getWeatherMapNull() {
//
//        Map<String, Weather> result = weatherRepository.getWeatherMap();
//
//        Weather weather = result.get("Zatoryy");
//
//        assertThat(weather).isNull();
//    }

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

        List<Weather> result1 = null;
        try {
            result1 = weatherService.listWeathers(citiesList);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        List<Weather> result2 = null;
        try {
            result2 = weatherService.listWeathers(wrongCitiesList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(result2).isEqualTo(expectedNull);
    }
}