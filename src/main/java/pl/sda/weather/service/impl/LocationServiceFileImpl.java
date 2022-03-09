package pl.sda.weather.service.impl;

import pl.sda.weather.Gui;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.model.LocationModel;

import java.util.List;


public class LocationServiceFileImpl {


//    Gui gui = new Gui();
//
//
//    public void cleanRecords(LocationModelEntity locationModelEntity) {
//        this.locationRepository.delateAllRecords(locationModelEntity);
//    }
//
//
//    public void saveLocationModel(LocationModel locationModel) {
//        this.locationRepository.saveLocation(locationModel);
//    }
//
//
//    public List<LocationModel> getAllLocation() {
//        return this.locationRepository.getAllLocationModelData();
//
//
//    }
//
//    public LocationModel getLocationByIdAndName(String patternToSearch) {
//        List<LocationModel> locationModelList = getAllLocation();
//        LocationModel result = null;
//
//        for (LocationModel locationModel : locationModelList) {
//            if (locationModel.getCityName().equals(patternToSearch) || locationModel.getId().equals(patternToSearch)) {
//                result = locationModel;
//            }
//        }
//        return result;
//    }
//
//    List<LocationModel> newList = getAllLocation();
//
//    public void editLocation(String whatEdit, String pattern, String editData) {
//
//        if (whatEdit.equals("cityName")) {
//            editLocationModelCityName(pattern, editData);
//
//        }
//        if (whatEdit.equals("countryName")) {
//            editLocationModelCountryName(pattern, editData);
//
//        }
//        if (whatEdit.equals("regionName")) {
//
//            editLocationModelRegionName(pattern, editData);
//        }
//        if (whatEdit.equals("coordinates")){
//            editLocationModelCoordinates(pattern);
//
//        }
//    }
//
//
//    public void editLocationModelCityName(String pattern,String cityName) {
//
//        cleanRecords();
//
//        for (LocationModel locationModel : newList) {
//            if (locationModel.getCityName().equals(pattern)) {
//                locationModel.setCityName(cityName);
//            }
//        }
//        for (LocationModel locationModelToBd : newList) {
//            saveLocationModel(locationModelToBd);
//        }
//
//    }
//
//    public void editLocationModelCountryName(String pattern, String countryName) {
//        cleanRecords();
//
//        for (LocationModel locationModel : newList) {
//            if (locationModel.getCityName().equals(pattern)) {
//                locationModel.setCountryName(countryName);
//            }
//        }
//        for (LocationModel locationModelToBd : newList) {
//            saveLocationModel(locationModelToBd);
//        }
//
//    }
//
//    public void editLocationModelRegionName(String pattern, String regionName) {
//        cleanRecords();
//
//        for (LocationModel locationModel : newList) {
//            if (locationModel.getCityName().equals(pattern)) {
//                locationModel.setRegion(regionName);
//            }
//        }
//        for (LocationModel locationModelToBd : newList) {
//            saveLocationModel(locationModelToBd);
//        }
//
//    }
//
//    public void editLocationModelCoordinates(String pattern) {
//        cleanRecords();
//
//        for (LocationModel locationModel : newList) {
//            if (locationModel.getCityName().equals(pattern)) {
//                locationModel.setLongitudeAndLatitude(gui.getLongitudeAndLatitudeFromUser());
//            }
//        }
//        for (LocationModel locationModelToBd : newList) {
//            saveLocationModel(locationModelToBd);
//        }
//
//    }

}
