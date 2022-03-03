package pl.sda.weather.repository;


import pl.sda.weather.model.LocationModel;

import java.util.List;

public interface ILocationRepository {

    void addLocationModelToDB(LocationModel locationModel);

    void cleanFile();

    List<String[]> getDataFromDB();
}
