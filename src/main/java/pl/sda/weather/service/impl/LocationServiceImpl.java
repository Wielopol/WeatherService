package pl.sda.weather.service.impl;

import pl.sda.weather.Gui;
import pl.sda.weather.repository.ILocationRepository;
import pl.sda.weather.repository.impl.LocationRepositoryImpl;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.service.ILocationService;

import java.util.ArrayList;
import java.util.List;


public class LocationServiceImpl implements ILocationService {

    ILocationRepository locationRepository = new LocationRepositoryImpl();

    Gui gui = new Gui();

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
    public LocationModel getLocationModelFromDbAfterIdOrName(String patternToSearch) {
        List<LocationModel> locationModelList = getLocationModelFromBD();
        LocationModel result = null;

        for (LocationModel locationModel : locationModelList) {
            if (locationModel.getCityName().equals(patternToSearch) || locationModel.getId().equals(patternToSearch)) {
                result = locationModel;
            }
        }
        return result;
    }

    List<LocationModel> newList = getLocationModelFromBD();

    @Override
    public void editLocationModel(String whatEdit, String pattern, String editData) {

        if (whatEdit.equals("cityName")) {
            editLocationModelCityName(pattern, editData);

        }
        if (whatEdit.equals("countryName")) {
            editLocationModelCountryName(pattern, editData);

        }
        if (whatEdit.equals("regionName")) {

            editLocationModelRegionName(pattern, editData);
        }
        if (whatEdit.equals("coordinates")){
            editLocationModelCoordinates(pattern);

        }
    }


    public void editLocationModelCityName(String pattern,String cityName) {

        cleanDBWithLocalModel();

        for (LocationModel locationModel : newList) {
            if (locationModel.getCityName().equals(pattern)) {
                locationModel.setCityName(cityName);
            }
        }
        for (LocationModel locationModelToBd : newList) {
            addLocationModelToDB(locationModelToBd);
        }

    }

    public void editLocationModelCountryName(String pattern, String countryName) {
        cleanDBWithLocalModel();

        for (LocationModel locationModel : newList) {
            if (locationModel.getCityName().equals(pattern)) {
                locationModel.setCountryName(countryName);
            }
        }
        for (LocationModel locationModelToBd : newList) {
            addLocationModelToDB(locationModelToBd);
        }

    }

    public void editLocationModelRegionName(String pattern, String regionName) {
        cleanDBWithLocalModel();

        for (LocationModel locationModel : newList) {
            if (locationModel.getCityName().equals(pattern)) {
                locationModel.setRegion(regionName);
            }
        }
        for (LocationModel locationModelToBd : newList) {
            addLocationModelToDB(locationModelToBd);
        }

    }

    public void editLocationModelCoordinates(String pattern) {
        cleanDBWithLocalModel();

        for (LocationModel locationModel : newList) {
            if (locationModel.getCityName().equals(pattern)) {
                locationModel.setLongitudeAndLatitude(gui.getLongitudeAndLatitudeFromUser());
            }
        }
        for (LocationModel locationModelToBd : newList) {
            addLocationModelToDB(locationModelToBd);
        }

    }

}
