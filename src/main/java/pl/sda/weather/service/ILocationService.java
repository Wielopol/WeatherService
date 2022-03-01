package pl.sda.weather.service;


import pl.sda.weather.model.LocationModel;

import java.util.List;

public interface ILocationService {


    void addLocationModelToTxtDB(LocationModel locationModel);

    List<LocationModel> getLocationModelFromFileTxt();

    void addLocationModelJsonToDB(LocationModel locationModel);

    List<LocationModel> getLocationModelFromFileJson();
}
