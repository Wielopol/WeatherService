package pl.sda.weather.service;


import pl.sda.weather.model.LocationModel;

import java.util.List;

public interface ILocationService {


    void cleanFile();

    void addLocationModelJsonToDB(LocationModel locationModel);

    List<LocationModel> getLocationModelFromFileJson();
}
