package pl.sda.weather.repository;


import pl.sda.weather.model.LocationModel;

import java.util.List;

public interface ILocationRepository {

    void addLocationModelJsonToDB(LocationModel locationModel);

    List<LocationModel> getLocationModelFromFileJson();

    void cleanFile();
}
