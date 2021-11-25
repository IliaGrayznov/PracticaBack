package inreco.vlgu.practic.dto.car;

import inreco.vlgu.practic.model.Car;

import java.util.List;

public class CarResponse {
    private List<Car> cars;

    public CarResponse(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}