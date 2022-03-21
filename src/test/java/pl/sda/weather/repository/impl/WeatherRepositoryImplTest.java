package pl.sda.weather.repository.impl;

import org.junit.jupiter.api.Test;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;
import pl.sda.weather.repository.ILocationRepository;
import pl.sda.weather.repository.IWeatherRepository;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.IWeatherService;
import pl.sda.weather.service.impl.LocationServiceDbImpl;
import pl.sda.weather.service.impl.WeatherServiceImpl;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class WeatherRepositoryImplTest {

    IWeatherRepository weatherRepository = new WeatherRepositoryImpl();
    ILocationRepository locationRepository = new LocationRepositoryDbImpl();
    ILocationService locationService = new LocationServiceDbImpl();
    IWeatherService weatherService = new WeatherServiceImpl();

    @Test
    void saveWeatherTest() {

        String location_id = String.valueOf(UUID.randomUUID());
        //give
        LocationModelEntity location1 = new LocationModelEntity(location_id,"48.398,9.991","Ulm","Magirus","Germany");

        locationRepository.saveLocation(location1);
        WeatherModelEntity expected1 = weatherService.readWeather(location1,2);
        weatherRepository.saveWeather(expected1);

        //when
        WeatherModelEntity result1 = weatherRepository.getWeatherModelDataByLocation(location1);

        //then

        assertEquals(result1, expected1);
        assertNotNull(result1);

        //after
        locationService.delateLocationOnList("Ulm");
    }

    @Test
    void getWeatherModelDataByLocationTest() {

        String location_id = String.valueOf(UUID.randomUUID());
        //give
        LocationModelEntity location1 = new LocationModelEntity(location_id,"48.398,9.991","Ulm","Magirus","Germany");

        locationRepository.saveLocation(location1);
        WeatherModelEntity expected1 = weatherService.readWeather(location1,1);
        weatherRepository.saveWeather(expected1);

        //when
        WeatherModelEntity result1 = weatherRepository.getWeatherModelDataByLocation(location1);

        //then

        assertEquals(result1, expected1);

        //after
        locationService.delateLocationOnList("Ulm");
    }

    @Test
    void deleteWeatherTest() {

        String location_id = String.valueOf(UUID.randomUUID());
        //give
        LocationModelEntity location1 = new LocationModelEntity(location_id,"48.398,9.991","Ulm","Magirus","Germany");

        locationRepository.saveLocation(location1);
        WeatherModelEntity expected1 = weatherService.readWeather(location1,2);
        weatherRepository.saveWeather(expected1);

        //when
        WeatherModelEntity result1 = weatherRepository.getWeatherModelDataByLocation(location1);

        weatherRepository.deleteRecord(result1);
        boolean result1Exist = weatherService.doesWeatherExistForLocation(location1);

        //then

        assertFalse(result1Exist);

        //after
        locationService.delateLocationOnList("Ulm");
    }
}