package pl.sda.weather.service;


import pl.sda.weather.model.LocationModel;

import java.util.List;

public interface ILocationService {


    void addLocationModelToDB(LocationModel locationModel);

    List<LocationModel> getLocationModelFromFile();
}
