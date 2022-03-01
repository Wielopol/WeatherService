package pl.sda.weather.service.impl;

import pl.sda.weather.repository.ILocationRepository;
import pl.sda.weather.repository.impl.LocationRepositoryImpl;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.service.ILocationService;

import java.util.List;


public class LocationServiceImpl implements ILocationService {


    ILocationRepository locationDAO = new LocationRepositoryImpl();




    @Override
    public void addLocationModelToTxtDB(LocationModel locationModel) {

        this.locationDAO.addLocationModelToTxtDB(locationModel);

    }

    @Override
    public List<LocationModel> getLocationModelFromFileTxt() {
        return this.locationDAO.getLocationModelFromFileTxt();
    }

    @Override
    public void addLocationModelJsonToDB(LocationModel locationModel){
        this.locationDAO.addLocationModelJsonToDB(locationModel);
    }
    @Override
    public List<LocationModel> getLocationModelFromFileJson() {
        return this.locationDAO.getLocationModelFromFileJson();
    }


}
