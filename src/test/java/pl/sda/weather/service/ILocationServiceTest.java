package pl.sda.weather.service;

import org.junit.jupiter.api.Test;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.service.impl.LocationServiceDbImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ILocationServiceTest {


    ILocationService locationService = new LocationServiceDbImpl();

    @Test
    void getLocationModelFromBD() {
    }

    @Test
    void getLocationModelFromDbAfterIdOrName() {
    }

    @Test
    void saveLocationModel(){
        //give

        //when

        //then

    }
    @Test
    void saveLocation() {

        LocationModelEntity location1 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"59.436,24.753","Tallinn","Telliskivi","Estonia");
        LocationModelEntity location2 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"48.398,9.991","Ulm","Magirus","Germany");
        LocationModelEntity location3 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"48.208,16.372","Wieden","Taborstraße","Austria");
        LocationModelEntity location4 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"48.853,2.348","Paris","Cedex","France");
        LocationModelEntity location5 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"50.454,30.523","Kijow","Okhtyrskyi","Ukraina");

        locationService.saveLocationModel(location1);
        locationService.saveLocationModel(location2);
        locationService.saveLocationModel(location3);
        locationService.saveLocationModel(location4);
        locationService.saveLocationModel(location5);

    }

}