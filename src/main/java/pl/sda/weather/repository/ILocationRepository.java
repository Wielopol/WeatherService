package pl.sda.weather.repository;


import pl.sda.weather.model.LocationModel;

import java.util.List;

public interface ILocationRepository {


    void addLocationModelToTxtDB(LocationModel locationModel);

    void addLocationModelJsonToDB(LocationModel locationModel);

    List<LocationModel> getLocationModelFromFileTxt();

    List<LocationModel> getLocationModelFromFileJson();
}
