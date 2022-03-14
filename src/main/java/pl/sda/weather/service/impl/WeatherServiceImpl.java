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
    public void listOneWeather(LocationModelEntity location, int day) {
        WeatherModelEntity weather = readWeatherRepository.readWeather(location, day);
        readWeatherRepository.saveWeather(weather);
    }

    @Override
    public void listWeathers(List<LocationModelEntity> citiesList, int day) {
        cleanRecords();
        for (LocationModelEntity city : citiesList) {
            WeatherModelEntity weather = readWeatherRepository.readWeather(city, day);
            readWeatherRepository.saveWeather(weather);
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
    public List<WeatherModelEntity> getAllWeathers() {
        return this.readWeatherRepository.getAllWeatherModelData();
    }

    @Override
    public WeatherModelEntity getWeatherByLocationId(LocationModelEntity location) {
        return this.readWeatherRepository.getWeatherModelDataByLocationId(location);
    }
}
