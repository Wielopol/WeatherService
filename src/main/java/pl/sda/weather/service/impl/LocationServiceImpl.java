package pl.sda.weather.service.impl;

import pl.sda.weather.repository.ILocationRepository;
import pl.sda.weather.repository.impl.LocationRepositoryImpl;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.service.ILocationService;

import java.util.List;


public class LocationServiceImpl implements ILocationService {


    ILocationRepository locationDAO = new LocationRepositoryImpl();




    @Override
    public void addLocationModelToDB(LocationModel locationModel) {

        this.locationDAO.addLocationModelToDB(locationModel);

    }

    @Override
    public List<LocationModel> getLocationModelFromFile() {
        return this.locationDAO.getLocationModelFromFile();
    }

}
