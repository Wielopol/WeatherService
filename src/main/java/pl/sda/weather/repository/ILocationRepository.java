package pl.sda.weather.repository;


import pl.sda.weather.model.entity.LocationModelEntity;

import java.util.List;

public interface ILocationRepository {

    void saveLocation(LocationModelEntity locationModelEntity);

    void editLocation(LocationModelEntity locationModelEntity);

    List<LocationModelEntity> getAllLocationModelData();


    void delateRecord(LocationModelEntity locationModelEntity);
}
