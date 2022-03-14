package pl.sda.weather.service.impl;

import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;
import pl.sda.weather.repository.ILocationRepository;
import pl.sda.weather.repository.IWeatherRepository;
import pl.sda.weather.repository.impl.LocationRepositoryDbImpl;
import pl.sda.weather.repository.impl.WeatherRepositoryImpl;
import pl.sda.weather.service.ILocationService;

import java.util.List;

public class LocationServiceDbImpl implements ILocationService {

    ILocationRepository locationRepository = new LocationRepositoryDbImpl();
    IWeatherRepository weatherRepository = new WeatherRepositoryImpl();


    @Override
    public boolean isLocationExiest(LocationModelEntity model) {
        List<LocationModelEntity> list = locationRepository.getAllLocationModelData();
        for (LocationModelEntity location : list) {
            if (location.getLatitudeAndLongitude().equals(model.getLatitudeAndLongitude()) || location.getCityName().equals(model.getCityName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void cleanRecords() {
        List<LocationModelEntity> list = locationRepository.getAllLocationModelData();
        for (LocationModelEntity location : list) {
            this.locationRepository.delateRecord(location);
        }
    }

    @Override
    public void delateLocationOnList(String pattern) {

        LocationModelEntity location = getLocationByIdAndName(pattern);
        WeatherModelEntity weather = weatherRepository.getWeatherModelDataByLocationId(location);
        this.weatherRepository.deleteRecord(weather);
        this.locationRepository.delateRecord(location);


    }

    @Override
    public void saveLocationModel(LocationModelEntity locationModelEntity) {
        this.locationRepository.saveLocation(locationModelEntity);

    }

    @Override
    public List<LocationModelEntity> getAllLocation() {

        return this.locationRepository.getAllLocationModelData();
    }

    @Override
    public LocationModelEntity getLocationByIdAndName(String patternToSearch) {

        return this.locationRepository.getAllLocationModelDataByCityNameOrId(patternToSearch);

    }

    @Override
    public void editLocation(String whatEdit, String pattern, String editData) {

        LocationModelEntity location = getLocationByIdAndName(pattern);

        if(location != null) {
            if (whatEdit.equals("cityName")) {
                location.setCityName(editData);

            }
            if (whatEdit.equals("countryName")) {
                location.setCountryName(editData);

            }
            if (whatEdit.equals("regionName")) {

                location.setRegion(editData);
            }
            if (whatEdit.equals("coordinates")) {
                location.setLatitudeAndLongitude(editData);
            }

            locationRepository.editLocation(location);
        }
        System.out.println("Wrong location");
    }
}