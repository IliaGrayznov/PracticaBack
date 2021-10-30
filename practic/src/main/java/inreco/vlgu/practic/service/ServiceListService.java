package inreco.vlgu.practic.service;


import inreco.vlgu.practic.model.ServiceList;

import inreco.vlgu.practic.repository.ServiceListRepository;
import inreco.vlgu.practic.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ServiceListService {
    @Autowired
    ServiceListRepository serviceListRepository;

    @Autowired
    ServiceTypeRepository serviceTypeRepository;

   @Transactional
    public boolean createService(String name, String description, Double price, Integer type_id)  {
        ServiceList s = new ServiceList();
        s.setName(name); s.setDescription(description); s.setPrice(price); s.setType(serviceTypeRepository.getById(type_id));
        try{
            serviceListRepository.save(s);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public boolean updateService(Long id, Double price)  {
        ServiceList s = serviceListRepository.getById(id);
        s.setPrice(price);
        try{
            serviceListRepository.save(s);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }


    public List<ServiceList> getAllServices(){
        return serviceListRepository.findAll();
    }


}