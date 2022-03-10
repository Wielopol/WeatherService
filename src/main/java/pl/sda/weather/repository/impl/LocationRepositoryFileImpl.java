package pl.sda.weather.repository.impl;


import com.google.gson.Gson;
import pl.sda.weather.model.LocationModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class LocationRepositoryFileImpl {

    private static final String FILE_LOCATION_JSON = "src/main/resources/location/locations.json";
    Gson gson = new Gson();


    public void saveLocation(LocationModel locationModel) {

        try {
            Files.write(Paths.get(FILE_LOCATION_JSON),
                    (gson.toJson(locationModel) + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);

        } catch (IOException e) {
            System.err.println("Unable to write the file.");
        }
    }



    public List<LocationModel> getAllLocationModelData() {

        try (Stream<String> stream = Files.lines(Paths.get(FILE_LOCATION_JSON))) {
            return stream.map(s -> gson.fromJson(s, LocationModel.class)).toList();
        } catch (IOException e) {
            System.err.println("Unable to clear the file");
        }
        return Collections.emptyList();

    }


    public void delateAllRecordsInFile() {
        try {
            Files.write(Paths.get(FILE_LOCATION_JSON), "".getBytes());
        } catch (IOException e) {
            System.err.println("Unable to clear the file.");
        }
    }


}
