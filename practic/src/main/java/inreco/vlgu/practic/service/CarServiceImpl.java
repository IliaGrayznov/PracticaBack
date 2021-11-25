package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.car.RegisterCarRequest;
import inreco.vlgu.practic.model.Car;
import inreco.vlgu.practic.model.User;
import inreco.vlgu.practic.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository carRepository;

    @Transactional
    public boolean createCar(RegisterCarRequest registerCarRequest, User user)  {
        Car c = new Car();
        c.setNumber(registerCarRequest.getNumber()); c.setMark(registerCarRequest.getMark()); c.setModel(registerCarRequest.getModel()); c .setOwner(user);
        try{
            carRepository.save(c);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public List<Car> getCars()  {
        return carRepository.findAll();
    }

}