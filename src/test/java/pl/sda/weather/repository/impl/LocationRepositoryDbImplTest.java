package pl.sda.weather.repository.impl;

import org.junit.jupiter.api.Test;
import pl.sda.weather.model.LocationModel;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.repository.ILocationRepository;
import pl.sda.weather.service.ILocationService;
import pl.sda.weather.service.impl.LocationServiceDbImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LocationRepositoryDbImplTest {


    ILocationRepository locationRepository = new LocationRepositoryDbImpl();
    ILocationService locationService = new LocationServiceDbImpl();

    @Test
    void testSaveGetByNameAndDelate() {
        //give
        LocationModelEntity location1 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"59.436,24.753","Tallinn","Telliskivi","Estonia");
        LocationModelEntity location2 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"48.398,9.991","Ulm","Magirus","Germany");

        //when
        locationRepository.saveLocation(location1);
        locationRepository.saveLocation(location2);

        LocationModelEntity result1 = locationRepository.getAllLocationModelDataByCityNameOrId("Tallinn");
        LocationModelEntity result2 = locationRepository.getAllLocationModelDataByCityNameOrId("Ulm");


        //then
        assertEquals(location1, result1);
        assertNotNull(result2);

        //after

        locationRepository.delateRecord(result1);
        locationRepository.delateRecord(result2);
    }

    @Test
    void getAllLocationModelDataByCityNameOrId() {

        //give
        LocationModelEntity location1 = new LocationModelEntity("1999","59.436,24.753","Tallinn","Telliskivi","Estonia");
        LocationModelEntity location2 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"48.398,9.991","Ulm","Magirus","Germany");

        locationRepository.saveLocation(location1);
        locationRepository.saveLocation(location2);

        LocationModelEntity result1 = locationRepository.getAllLocationModelDataByCityNameOrId("Tallinn");
        LocationModelEntity result2 = locationRepository.getAllLocationModelDataByCityNameOrId("Ulm");

        //when
        LocationModelEntity expected1 = locationRepository.getAllLocationModelDataByCityNameOrId("1999");
        LocationModelEntity expected2 = locationRepository.getAllLocationModelDataByCityNameOrId("Ulm");

        //then

        assertEquals(result1, expected1);
        assertEquals(result2, expected2);

        //after
        locationRepository.delateRecord(result1);
        locationRepository.delateRecord(result2);
    }

    @Test
    void delateRecord() {

        //give
        LocationModelEntity location1 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"59.436,24.753","Tallinn","Telliskivi","Estonia");
        LocationModelEntity location2 = new LocationModelEntity(String.valueOf(UUID.randomUUID()),"48.398,9.991","Ulm","Magirus","Germany");

        locationRepository.saveLocation(location1);
        locationRepository.saveLocation(location2);

        LocationModelEntity result1 = locationRepository.getAllLocationModelDataByCityNameOrId("Tallinn");
        LocationModelEntity result2 = locationRepository.getAllLocationModelDataByCityNameOrId("Ulm");

        //when

        assertEquals(locationRepository.getAllLocationModelDataByCityNameOrId("Tallinn"), result1);
        assertEquals(locationRepository.getAllLocationModelDataByCityNameOrId("Ulm"), result2);

        locationRepository.delateRecord(result1);
        boolean result1Expected = locationService.isLocationExiest(result1);
        //then

        assertFalse(result1Expected);
        assertNotNull(locationRepository.getAllLocationModelDataByCityNameOrId("Ulm"));
        //after

        locationRepository.delateRecord(result2);

    }
}