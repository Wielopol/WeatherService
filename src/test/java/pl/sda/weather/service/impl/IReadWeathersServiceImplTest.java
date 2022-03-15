package pl.sda.weather.service.impl;

import org.junit.jupiter.api.Test;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;
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

        LocationModelEntity location1 = new LocationModelEntity("1", "latitude: 14.22; longitude: 61.23",
                "Wieliczka", "malopolskie", "Poland");
        LocationModelEntity location2 = new LocationModelEntity("2", "latitude: 24.22; longitude: 41.23",
                "Warsaw", "mazowieckie", "Poland");

        List<LocationModelEntity> citiesList = new ArrayList<>();
        citiesList.add(location1);
        citiesList.add(location2);

        WeatherModelEntity weather1 = new WeatherModelEntity();
        weather1.setTemperature(6.4f);
        weather1.setPressure(1010.5f);
        weather1.setHumidity(50);
        weather1.setWindDir(167f);
        weather1.setWindSpeed(4.5f);
        weather1.setLocation(location1);
//        WeatherModelEntity weather2 = new WeatherModelEntity(244.31, 1012.5, 58, 157, 11.5, "Krakow");
//        WeatherModelEntity weather3 = new WeatherModelEntity(244.31, 1012.5, 58, 157, 11.5, "Warsaw");

        Map<String,WeatherModelEntity> weatherMap = new HashMap<>();
        weatherMap.put("Wieliczka",weather1);
//        weatherMap.put("Krakow",weather2);
//        weatherMap.put("Warsaw",weather3);

        List<WeatherModelEntity> expectedNotNull = new ArrayList<>();
        expectedNotNull.add(weather1);
//        expectedNotNull.add(weather3);

        List<WeatherModelEntity> result1 = null;
        try {
//            result1 = weatherService.listWeathers(citiesList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(result1).isEqualTo(expectedNotNull);
    }

    @Test
    void listWeatherNull() {
//        WeatherModelEntity weather1 = new WeatherModelEntity(264.31, 1010.5, 50, 167, 4.5, "Wieliczka");
//        WeatherModelEntity weather2 = new WeatherModelEntity(244.31, 1012.5, 58, 157, 11.5, "Krakow");
//        WeatherModelEntity weather3 = new WeatherModelEntity(244.31, 1012.5, 58, 157, 11.5, "Warsaw");

        Map<String,WeatherModelEntity> weatherMap = new HashMap<>();
//        weatherMap.put("Wieliczka",weather1);
//        weatherMap.put("Krakow",weather2);
//        weatherMap.put("Warsaw",weather3);

        LocationModelEntity location3 = new LocationModelEntity("1", "latitude: 14.22; longitude: 61.23",
                "Wielicka", "malopolskie", "Poland");
        LocationModelEntity location4 = new LocationModelEntity("2", "latitude: 24.22; longitude: 41.23",
                "Warszawa", "mazowieckie", "Poland");

        List<LocationModelEntity> wrongCitiesList = new ArrayList<>();
        wrongCitiesList.add(location3);
        wrongCitiesList.add(location4);

        List<WeatherModelEntity> expectedNull = new ArrayList<>();
        expectedNull.add(null);
        expectedNull.add(null);

        List<WeatherModelEntity> result2 = null;
        try {
//            result2 = weatherService.listWeathers(wrongCitiesList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(result2).isEqualTo(expectedNull);
    }
}