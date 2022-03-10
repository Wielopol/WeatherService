package pl.sda.weather.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LocationValidatorTest {

    LocationValidator locationValidator = new LocationValidator();


    @Test
    void whenEnterTheNameWithNumber(){
        //give
        String wrong = "oodd3dd";
        //when
        boolean result = locationValidator.validationNames(wrong);
        //then
        assertThat(result).isFalse();

    }
    @Test
    void whenEnterCorrectName(){
        //give
        String wrong = "Krakow";
        //when
        boolean result = locationValidator.validationNames(wrong);
        //then
        assertThat(result).isTrue();

    }
    @Test
    void whenEnterNameWithComma(){
        //give
        String wrong = "Kr,akow";
        //when
        boolean result = locationValidator.validationNames(wrong);
        //then
        assertThat(result).isFalse();

    }
    @Test
    void whenEnterWrongCoordinates(){
        //give
        String wrong = "00";
        //when
        boolean result = locationValidator.validationLongitude(wrong);
        //then
        assertThat(result).isFalse();
    }

    @Test
    void whenEnterWrongCoordinatesWithComma(){
        //give
        String wrong = "00,3";
        //when
        boolean result = locationValidator.validationLatitude(wrong);
        //then
        assertThat(result).isFalse();
    }

    @Test
    void whenEnterGoodCoordinates(){
        //give
        String wrong = "11.3";
        //when
        boolean result = locationValidator.validationLatitude(wrong);
        //then
        assertThat(result).isTrue();
    }

    @Test
    void whenEnterToLoweCoordination(){
        //give
        double value = -190.3;
        //when
        boolean result = locationValidator
                .authenticationCoordinateInterval(-180,180, value);
        //then
        assertThat(result).isFalse();
    }

    @Test
    void whenEnterToHighCoordination(){
        //give
        double value = 101.3;
        //when
        boolean result = locationValidator
                .authenticationCoordinateInterval(-90,90,value);
        //then
        assertThat(result).isFalse();
    }
    @Test
    void whenEnterCoordinationInRang(){
        //give
        double value = 11.3;
        //when
        boolean result = locationValidator.authenticationCoordinateInterval(-180,180,value);
        //then
        assertThat(result).isTrue();
    }
    @Test
    void whenEnterCoordinationInRangButOnEdge(){
        //give
        double value = 90.00;
        //when
        boolean result = locationValidator.authenticationCoordinateInterval(-90,90,value);
        //then
        assertThat(result).isTrue();
    }





}