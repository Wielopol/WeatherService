package pl.sda.weather.service.impl;

import pl.sda.weather.dao.ILocationDAO;
import pl.sda.weather.dao.impl.LocationDAOImpl;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.service.ILocationService;

import java.util.List;


public class LocationServiceImpl implements ILocationService {


    ILocationDAO locationDAO = new LocationDAOImpl();




    @Override
    public void addLocationModelToDB(LocationModel locationModel) {

        this.locationDAO.addLocationModelToDB(locationModel);

    }

    @Override
    public List<LocationModel> getLocationModelFromFile() {
        return this.locationDAO.getLocationModelFromFile();
    }

}
