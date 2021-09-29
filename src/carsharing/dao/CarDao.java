package carsharing.dao;

import carsharing.entitie.Car;

import java.util.List;

public interface CarDao {
    List<Car> getAllCars();
    void createCar(String car);
}
