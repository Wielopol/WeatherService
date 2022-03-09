package pl.sda.weather.repository;


import pl.sda.weather.model.LocationModel;
import pl.sda.weather.model.entity.LocationModelEntity;

import java.util.List;

public interface ILocationRepository {

    void saveLocation(LocationModelEntity locationModelEntity);

    List<LocationModelEntity> getAllLocationModelData();


    void delateAllRecords(LocationModelEntity locationModelEntity);
}
