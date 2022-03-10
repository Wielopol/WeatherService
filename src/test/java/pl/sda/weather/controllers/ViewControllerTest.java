package pl.sda.weather.controllers;

import org.junit.jupiter.api.Test;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.impl.LocationServiceDbImpl;

import javax.persistence.Column;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ViewControllerTest {

    ViewController viewController = new ViewController();

    ILocationService locationService = new LocationServiceDbImpl();




    @Test
    void addLocationWord() {
        LocationModelEntity location1 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"50.061,19.936","Krakow","Malopolska","Polska");
        LocationModelEntity location2 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"52.229,21.011","Warszawa","Mazowieckie","Polska");
        LocationModelEntity location3 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"50.041,21.999","Rzeszow","Podkarpacie","Polska");
        LocationModelEntity location4 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"52.406,16.929","Poznan","Wielkopolska","Polska");
        LocationModelEntity location5 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"51.100,17.033","Wroclaw","Slask","Polska");


        locationService.saveLocationModel(location1);
        locationService.saveLocationModel(location2);
        locationService.saveLocationModel(location3);
        locationService.saveLocationModel(location4);
        locationService.saveLocationModel(location5);
    }

    @Test
    void getDataFromUserToCompleteLocationModel() {
    }

    @Test
    void displayLocation() {
    }

    @Test
    void displayWeathers() {
    }

    @Test
    void cleanFile() {
    }

    @Test
    void delateOneLocation() {
    }

    @Test
    void locationSearch() {
    }

    @Test
    void editLocationModelInDb() {
    }
}