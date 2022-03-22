package pl.sda.weather.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.sda.weather.connection.HibernateUtil;
import pl.sda.weather.model.LocationModelDTO;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.entity.WeatherModelEntity;
import pl.sda.weather.repository.ILocationRepository;
import pl.sda.weather.repository.IWeatherRepository;
import pl.sda.weather.repository.impl.LocationRepositoryDbImpl;
import pl.sda.weather.repository.impl.WeatherRepositoryImpl;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.transform.LocationTransform;
import pl.sda.weather.service.IWeatherService;

import javax.persistence.NoResultException;
import java.util.List;

public class LocationServiceDbImpl implements ILocationService {

    ILocationRepository locationRepository = new LocationRepositoryDbImpl();
    IWeatherRepository weatherRepository = new WeatherRepositoryImpl();
    IWeatherService weatherService = new WeatherServiceImpl();
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

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
        if (location.getLocation_id() == null) {
            return;
        }
        WeatherModelEntity weather = weatherService.getWeatherByLocation(location);
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

        LocationModelEntity locationModel = new LocationModelEntity();
        try {
            locationModel = locationRepository.getAllLocationModelDataByCityNameOrId(patternToSearch);
        } catch (NoResultException e) {
            logger.error(e.getMessage(), e);
        }

        return locationModel;
    }

    @Override
    public void editLocation(String whatEdit, String pattern, String editData) {

        LocationModelEntity location = getLocationByIdAndName(pattern);

        if (location != null) {
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
