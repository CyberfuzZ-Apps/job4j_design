package ru.job4j.design.lsp.parking;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CityParkingTest {

    @Ignore
    @Test
    public void whenParkingCar() {
        Parking parking = new CityParking(10, 5); // 10 - легковых, 5 - грузовых
        Automobile auto = new Car("A001AA", 1); // "A001AA" - carNumber, 1 - size
        assertTrue(parking.park(auto));
        assertThat(parking.freeSpaces(), is(Map.of("Car", 9, "Truck", 5)));
    }

    @Ignore
    @Test
    public void whenNoParkingCar() {
        Parking parking = new CityParking(0, 0);
        Automobile auto = new Car("A001AA", 1);
        assertFalse(parking.park(auto));
    }

    @Ignore
    @Test
    public void whenNoParkingTruck() {
        Parking parking = new CityParking(0, 0);
        Automobile auto = new Truck("B002BB", 3);
        assertFalse(parking.park(auto));
    }

    @Ignore
    @Test
    public void whenParkingTruckOnTruckPlace() {
        Parking parking = new CityParking(10, 5); // 10 - легковых, 5 - грузовых
        Automobile auto = new Truck("B002BB", 3); // "B002BB" - carNumber, 3 - size
        assertTrue(parking.park(auto));
        assertThat(parking.freeSpaces(), is(Map.of("Car", 10, "Truck", 4)));
    }

    @Ignore
    @Test
    public void whenParkingTruckOnCarPlace() {
        Parking parking = new CityParking(10, 0); // 10 - легковых, 0 - грузовых
        Automobile auto = new Truck("B002BB", 3); // "B002BB" - carNumber, 3 - size
        assertTrue(parking.park(auto));
        assertThat(parking.freeSpaces(), is(Map.of("Car", 7, "Truck", 0)));
    }

    @Ignore
    @Test
    public void whenTakeCarFromParking() {
        Parking parking = new CityParking(10, 0);
        Automobile auto = new Car("A001AA", 1);
        parking.park(auto);
        assertThat(parking.freeSpaces(), is(Map.of("Car", 9, "Truck", 0)));
        assertTrue(parking.takeAuto(auto));
        assertThat(parking.freeSpaces(), is(Map.of("Car", 10, "Truck", 0)));
    }

    @Ignore
    @Test
    public void whenTakeTruckFromParking() {
        Parking parking = new CityParking(10, 5);
        Automobile auto = new Truck("B002BB", 3);
        parking.park(auto);
        assertThat(parking.freeSpaces(), is(Map.of("Car", 10, "Truck", 4)));
        assertTrue(parking.takeAuto(auto));
        assertThat(parking.freeSpaces(), is(Map.of("Car", 10, "Truck", 5)));
    }

    @Ignore
    @Test
    public void whenTakeTruckFromParkingFromCarPlace() {
        Parking parking = new CityParking(10, 0);
        Automobile auto = new Truck("B002BB", 3);
        parking.park(auto);
        assertThat(parking.freeSpaces(), is(Map.of("Car", 7, "Truck", 0)));
        assertTrue(parking.takeAuto(auto));
        assertThat(parking.freeSpaces(), is(Map.of("Car", 10, "Truck", 0)));
    }

    @Ignore
    @Test
    public void whenParkingDuplicateCar() {
        Parking parking = new CityParking(10, 0);
        Automobile auto1 = new Car("A001AA", 1);
        Automobile auto2 = new Car("A001AA", 1);
        assertTrue(parking.park(auto1));
        assertThat(parking.freeSpaces(), is(Map.of("Car", 9, "Truck", 0)));
        assertFalse(parking.park(auto2));
    }

}