package pl.sda.weather.service.impl;

import pl.sda.weather.repository.ILocationRepository;
import pl.sda.weather.repository.impl.LocationRepositoryImpl;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.service.ILocationService;

import java.util.ArrayList;
import java.util.List;


public class LocationServiceImpl implements ILocationService {

    ILocationRepository locationRepository = new LocationRepositoryImpl();

    @Override
    public void cleanDBWithLocalModel() {
        this.locationRepository.cleanFile();
    }


    @Override
    public void addLocationModelToDB(LocationModel locationModel) {
        this.locationRepository.addLocationModelToDB(locationModel);
    }

    @Override
    public List<LocationModel> getLocationModelFromBD() {
        return this.locationRepository.getLocationModelDataFromDB();


    }

    @Override
    public List<LocationModel> getLocationModelFromDbAfterIdOrName(String patternToSearch) {
        List<LocationModel> locationModelList = getLocationModelFromBD();
        List<LocationModel> result = new ArrayList<>();

        for (LocationModel locationModel : locationModelList) {
            if (locationModel.getCityName().equals(patternToSearch) || locationModel.getId().equals(patternToSearch)) {
                result.add(locationModel);
            }
        }
        return result;
    }

    @Override
    public void editLocationModelCityName(String pattern, String newName){
        List<LocationModel> newList = getLocationModelFromBD();
        cleanDBWithLocalModel();

        for (LocationModel locationModel : newList) {
            if (locationModel.getCityName().equals(pattern)) {
                locationModel.setCityName(newName);
            }
        }
        for (LocationModel locationModelToBd : newList){
            addLocationModelToDB(locationModelToBd);
        }

    }

}
