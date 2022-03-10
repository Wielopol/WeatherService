package pl.sda.weather.service;


import pl.sda.weather.model.entity.LocationModelEntity;

import java.util.List;

public interface ILocationService {

    boolean isLocationExiest(LocationModelEntity model);

    void cleanRecords();

    void delateLocationOnList(String pattern);

    void saveLocationModel(LocationModelEntity locationModelEntity);

    List<LocationModelEntity> getAllLocation();

    LocationModelEntity getLocationByIdAndName(String patternToSearch);


    void editLocation(String whatEdit, String pattern, String editData);
}
