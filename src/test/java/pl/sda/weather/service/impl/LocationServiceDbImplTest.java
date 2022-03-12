package pl.sda.weather.service.impl;

import org.junit.jupiter.api.Test;
import pl.sda.weather.model.entity.LocationModelEntity;
import pl.sda.weather.service.ILocationService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LocationServiceDbImplTest {

    public final ILocationService locationService = new LocationServiceDbImpl();

    @Test
    void whenLocationExiest() {
        //give
        LocationModelEntity location = new LocationModelEntity
                (String.valueOf(UUID.randomUUID()),"48.398,9.991","Ulm","Magirus","Germany");
        locationService.saveLocationModel(location);

        //when

        boolean result = locationService.isLocationExiest(location);

        //then
        assertTrue(result);
        //after
        locationService.delateLocationOnList(location.getLocation_id());
    }

    @Test
    void whenTheLocationIsNotListed() {
        //give
        LocationModelEntity location = new LocationModelEntity
                (String.valueOf(UUID.randomUUID()),"48.398,9.991","Ulm","Magirus","Germany");

        //when

        boolean result = locationService.isLocationExiest(location);

        //then
        assertFalse(result);

    }

    @Test
    void cleanRecords() {
        //give
        int listSizeOrginal = locationService.getAllLocation().size();
        LocationModelEntity location = new LocationModelEntity
                (String.valueOf(UUID.randomUUID()),"48.398,9.991","Ulm","Magirus","Germany");
        locationService.saveLocationModel(location);

        LocationModelEntity location2 = new LocationModelEntity
                (String.valueOf(UUID.randomUUID()),"48.398,9.991","Berlin","Magirus","Germany");
        locationService.saveLocationModel(location2);
        //when

        int listSize = locationService.getAllLocation().size();

        int expectedSize = listSizeOrginal + 2;

        //then
        assertEquals(listSize, expectedSize);
        //after
        locationService.delateLocationOnList(location.getLocation_id());
        locationService.delateLocationOnList(location2.getLocation_id());

    }

    @Test
    void cleanRecords2() {
        //give
        int listSizeOrginal = locationService.getAllLocation().size();
        LocationModelEntity location = new LocationModelEntity
                (String.valueOf(UUID.randomUUID()),"48.398,9.991","Ulm","Magirus","Germany");
        locationService.saveLocationModel(location);

        LocationModelEntity location2 = new LocationModelEntity
                (String.valueOf(UUID.randomUUID()),"48.398,9.991","Berlin","Magirus","Germany");
        locationService.saveLocationModel(location2);
        //when

        int listSize = locationService.getAllLocation().size();

        int expectedSize = listSizeOrginal + 3;

        //then
        assertNotEquals(listSize, expectedSize);
        //after
        locationService.delateLocationOnList(location.getLocation_id());
        locationService.delateLocationOnList(location2.getLocation_id());

    }

    @Test
    void delateLocationOnList() {
        //give
        int listSizeOrginal = locationService.getAllLocation().size();
        LocationModelEntity location = new LocationModelEntity
                (String.valueOf(UUID.randomUUID()),"48.398,9.991","Ulm","Magirus","Germany");
        locationService.saveLocationModel(location);

        LocationModelEntity location2 = new LocationModelEntity
                (String.valueOf(UUID.randomUUID()),"48.398,9.991","Berlin","Magirus","Germany");
        locationService.saveLocationModel(location2);
        //when

        locationService.delateLocationOnList(location.getLocation_id());
        locationService.delateLocationOnList(location2.getCityName());


        boolean expect1 = locationService.isLocationExiest(location);
        boolean expect2 = locationService.isLocationExiest(location2);

        //then
        assertFalse(expect1);
        assertFalse(expect2);

    }

    @Test
    void saveLocationModel() {
        //give
        int listSizeOrginal = locationService.getAllLocation().size();
        LocationModelEntity location = new LocationModelEntity
                (String.valueOf(UUID.randomUUID()),"48.398,9.991","Ulm","Magirus","Germany");

        LocationModelEntity location2 = new LocationModelEntity
                (String.valueOf(UUID.randomUUID()),"48.398,9.991","Berlin","Magirus","Germany");

        //when
        locationService.saveLocationModel(location);
        locationService.saveLocationModel(location2);
        //then
        assertTrue(locationService.isLocationExiest(location));
        assertEquals(location2.getLocation_id(), locationService.getLocationByIdAndName("Berlin").getLocation_id());
        //after
        locationService.delateLocationOnList(location.getCityName());
        locationService.delateLocationOnList(location2.getLocation_id());
    }

    @Test
    void getAllLocation() {
        //give
        int listSizeOrginal = locationService.getAllLocation().size();
        LocationModelEntity location = new LocationModelEntity
                (String.valueOf(UUID.randomUUID()),"48.398,9.991","Ulm","Magirus","Germany");
        locationService.saveLocationModel(location);

        LocationModelEntity location2 = new LocationModelEntity
                (String.valueOf(UUID.randomUUID()),"48.398,9.991","Berlin","Magirus","Germany");
        locationService.saveLocationModel(location2);
        //when

        int listSize = locationService.getAllLocation().size();

        int expectedSize = listSizeOrginal + 2;

        //then

        assertEquals(listSize, expectedSize);
        //after
        locationService.delateLocationOnList(location.getLocation_id());
        locationService.delateLocationOnList(location2.getLocation_id());
    }

    @Test
    void getLocationByIdAndName() {
        //give
        int listSizeOrginal = locationService.getAllLocation().size();
        LocationModelEntity location = new LocationModelEntity(
                "696a9717-e065-4856-a7d8-8052f8fb60ae","48.398,9.991","Ulm","Magirus","Germany");

        LocationModelEntity location2 = new LocationModelEntity
                (String.valueOf(UUID.randomUUID()),"48.398,9.991","Berlin","Magirus","Germany");

        //when
        locationService.saveLocationModel(location);
        locationService.saveLocationModel(location2);
        //then
        assertEquals(location.getCityName(), locationService.getLocationByIdAndName("696a9717-e065-4856-a7d8-8052f8fb60ae").getCityName());
        assertEquals(location2.getLocation_id(), locationService.getLocationByIdAndName("Berlin").getLocation_id());
        //after
        locationService.delateLocationOnList(location.getCityName());
        locationService.delateLocationOnList(location2.getLocation_id());

    }

}