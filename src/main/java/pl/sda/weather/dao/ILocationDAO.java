package pl.sda.weather.dao;


import pl.sda.weather.model.LocationModel;

import java.util.List;

public interface ILocationDAO {


    void addLocationModelToDB(LocationModel locationModel);

    List<LocationModel> getLocationModelFromFile();

}
