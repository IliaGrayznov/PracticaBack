package inreco.vlgu.practic.service;

import inreco.vlgu.practic.model.Car;
import inreco.vlgu.practic.model.Request;
import inreco.vlgu.practic.model.ServiceList;
import inreco.vlgu.practic.model.User;
import inreco.vlgu.practic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    ServiceListRepository serviceListRepository;

    @Autowired
    RequestStatusRepository requestStatusRepository;

    @Transactional
    public boolean createRequest(User client, long id_car, Date date, List<Long> services)  {
        Request r = new Request(); List<ServiceList> serviceLists = new ArrayList<>();
        r.setCar(carRepository.getById(id_car)); r.setClient(client); r.setStatus(requestStatusRepository.getById(2)); r.setDate(date); r.setMaster(null);
        for (long id:
             services) {
            serviceLists.add(serviceListRepository.getById(id));
        }
        r.setServices(serviceLists);
        try{
            requestRepository.save(r);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public boolean acceptRequest(User master, long id_request)  {
        Request r = requestRepository.getById(id_request);
        r.setMaster(master); r.setStatus(requestStatusRepository.getById(3));
        try{
            requestRepository.save(r);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public String rejectRequest(long id_request, User master)  {
        Request r = requestRepository.getById(id_request);
        if(r.getStatus().getId()!=2)
            return "You can't reject accepted request";
        r.setStatus(requestStatusRepository.getById(7));
        r.setMaster(master);
        try{
            requestRepository.save(r);
        }
        catch (Exception e){
            return "BAD";
        }
        return "OK";
    }

    @Transactional
    public List<ServiceList> service(long id_request)  {
        Request r = requestRepository.getById(id_request);
        r.setStatus(requestStatusRepository.getById(4));
        requestRepository.save(r);
        return r.getServices();
    }

    @Transactional
    public boolean finishService(long id_request)  {
        Request r = requestRepository.getById(id_request);
        r.setStatus(requestStatusRepository.getById(5));
        try{
            requestRepository.save(r);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public List<Request> getRequests()  {
        return requestRepository.findAll();
    }

    public Request getOneRequest(long id)  {
        return requestRepository.getById(id);
    }

    public List<Request> getRequestsByStatus(Integer id_status)  {
        return requestRepository.getRequestsByStatus(id_status);
    }
}