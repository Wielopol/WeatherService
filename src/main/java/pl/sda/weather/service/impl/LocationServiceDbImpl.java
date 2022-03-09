package pl.sda.weather.service.impl;

import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.repository.ILocationRepository;
import pl.sda.weather.repository.impl.LocationRepositoryDbImpl;
import pl.sda.weather.service.ILocationService;

import java.util.List;

public class LocationServiceDbImpl implements ILocationService {

    ILocationRepository locationRepository = new LocationRepositoryDbImpl();

    @Override
    public void cleanRecords() {
        List<LocationModelEntity> list = locationRepository.getAllLocationModelData();

        for (LocationModelEntity location : list){
            this.locationRepository.delateAllRecords(location);
        }



    }

    @Override
    public void saveLocationModel(LocationModelEntity locationModelEntity) {
        this.locationRepository.saveLocation(locationModelEntity);

    }

    @Override
    public List<LocationModelEntity> getAllLocation() {

        return this.locationRepository.getAllLocationModelData();
    }

    @Override
    public LocationModelEntity getLocationByIdAndName(String patternToSearch) {

        List<LocationModelEntity> locationModelList = getAllLocation();

        LocationModelEntity result = null;

        for (LocationModelEntity locationModel : locationModelList) {
            if (locationModel.getCityName().equals(patternToSearch) || locationModel.getId().equals(patternToSearch)) {
                result = locationModel;
            }
        }
        return result;

    }

    @Override
    public void editLocation(String whatEdit, String pattern, String editData) {

    }
}
