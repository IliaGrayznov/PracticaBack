package inreco.vlgu.practic.service;


import inreco.vlgu.practic.dto.request.InputRequestID;
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
public class RequestServiceImpl implements  RequestService {
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    ServiceListRepository serviceListRepository;

    @Autowired
    RequestStatusRepository requestStatusRepository;

    @Transactional
    public boolean createRequest(User client, long id_car, Date date, Long idS1, Long idS2, Long idS3)  {
        List<Long> services = new ArrayList<>();
        if(idS1!=null && idS1!=0)
            services.add(idS1);
        if(idS2!=null && idS2!=0)
            services.add(idS2);
        if(idS3!=null && idS3!=0)
            services.add(idS3);
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
    public String acceptRequest(User master, InputRequestID inputRequestID)  {
        Request r = requestRepository.getById(inputRequestID.getId());
        if(r.getStatus().getId()!=2)
            return "You can't accept this request";
        r.setMaster(master); r.setStatus(requestStatusRepository.getById(3));
        try{
            requestRepository.save(r);
        }
        catch (Exception e){
            return "BAD";
        }
        return "OK";
    }

    @Transactional
    public String rejectRequest(InputRequestID inputRequestID, User master)  {
        Request r = requestRepository.getById(inputRequestID.getId());
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
    public List<ServiceList> service(InputRequestID inputRequestID)  {
        Request r = requestRepository.getById(inputRequestID.getId());
        r.setStatus(requestStatusRepository.getById(4));
        requestRepository.save(r);
        return r.getServices();
    }

    @Transactional
    public String finishService(InputRequestID inputRequestID)  {
        Request r = requestRepository.getById(inputRequestID.getId());
        if(r.getStatus().getId()!=4)
            return "You need to start servicing before finish it";
        r.setStatus(requestStatusRepository.getById(5));
        try{
            requestRepository.save(r);
        }
        catch (Exception e){
            return "BAD";
        }
        return "OK";
    }

    @Transactional
    public boolean payService(InputRequestID inputRequestID)  {
        Request r = requestRepository.getById(inputRequestID.getId());
        r.setStatus(requestStatusRepository.getById(6));
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

    public List<Request> getMastersRequests(Long id)  {
        return requestRepository.getMastersRequests(id);
    }

    public Request getOneRequest(long id)  {
        return requestRepository.getById(id);
    }


    public List<Request> getRequestsByStatus(Integer id_status)  {
        return requestRepository.getRequestsByStatus(id_status);
    }
}