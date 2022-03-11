package pl.sda.weather.service.impl;

import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;
import pl.sda.weather.repository.IWeatherRepository;
import pl.sda.weather.repository.impl.WeatherRepositoryImpl;
import pl.sda.weather.service.IWeatherService;

import java.util.List;

public class WeatherServiceImpl implements IWeatherService {

    IWeatherRepository readWeatherRepository = new WeatherRepositoryImpl();

    @Override
    public void listWeathers(List<LocationModelEntity> citiesList) {
        cleanRecords();
        for (LocationModelEntity city : citiesList) {
            readWeatherRepository.readWeather(city, 1);
        }
    }

    @Override
    public void cleanRecords() {
        List<WeatherModelEntity> listWeather = readWeatherRepository.getAllWeatherModelData();

        for (WeatherModelEntity weather : listWeather){
            this.readWeatherRepository.deleteRecord(weather);
        }
    }

    @Override
    public List<WeatherModelEntity> getAllLocation() {
        return this.readWeatherRepository.getAllWeatherModelData();
    }


}
