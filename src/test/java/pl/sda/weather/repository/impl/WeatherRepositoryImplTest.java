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

    @Test
    void getWeatherModelDataByLocationId() {

        String location_id = String.valueOf(UUID.randomUUID());
        //give
        LocationModelEntity location1 = new LocationModelEntity(location_id,"48.398,9.991","Ulm","Magirus","Germany");

        locationRepository.saveLocation(location1);
        WeatherModelEntity expected1 = weatherRepository.readWeather(location1,1);
        weatherRepository.saveWeather(expected1);

        //when
        WeatherModelEntity result1 = weatherRepository.getWeatherModelDataByLocationId(location1);

        //then

        assertEquals(result1, expected1);

        //after
        locationService.delateLocationOnList("Ulm");
    }
}