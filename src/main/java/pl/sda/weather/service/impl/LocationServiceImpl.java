package pl.sda.weather.service.impl;

import pl.sda.weather.repository.ILocationRepository;
import pl.sda.weather.repository.impl.LocationRepositoryImpl;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.service.ILocationService;

import java.util.List;
import java.util.stream.Collectors;


public class LocationServiceImpl implements ILocationService {

    ILocationRepository locationRepository = new LocationRepositoryImpl();

    @Override
    public void cleanDBWithLocalModel() {
        this.locationRepository.cleanFile();
    }


    @Override
    public void addLocationModelToDB(LocationModel locationModel) {
        this.locationRepository.addLocationModelToDB(locationModel);
    }

    @Override
    public List<LocationModel> getLocationModelFromBD() {
        return this.locationRepository.getLocationModelDataFromDB();


    }

}
