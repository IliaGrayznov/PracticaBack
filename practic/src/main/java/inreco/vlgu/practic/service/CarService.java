package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.car.RegisterCarRequest;
import inreco.vlgu.practic.model.Car;
import inreco.vlgu.practic.model.User;

import java.util.List;

public interface CarService {

    public boolean createCar(RegisterCarRequest registerCarRequest, User user);

    public List<Car> getCars();
}
