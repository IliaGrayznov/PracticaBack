package inreco.vlgu.practic.service;


import inreco.vlgu.practic.dto.service.ServiceCreateRequest;
import inreco.vlgu.practic.dto.service.ServiceRequestID;
import inreco.vlgu.practic.dto.service.ServiceUpdateRequest;
import inreco.vlgu.practic.model.ServiceList;

import inreco.vlgu.practic.model.ServiceType;
import inreco.vlgu.practic.repository.ServiceListRepository;
import inreco.vlgu.practic.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ServiceListServiceImpl implements  ServiceListService {
    @Autowired
    ServiceListRepository serviceListRepository;

    @Autowired
    ServiceTypeRepository serviceTypeRepository;

   @Transactional
    public boolean createService(ServiceCreateRequest serviceCreateRequest)  {
        ServiceList s = new ServiceList();
        s.setName(serviceCreateRequest.getName()); s.setDescription(serviceCreateRequest.getDescription()); s.setPrice(serviceCreateRequest.getPrice());
                                                                            s.setType(serviceTypeRepository.getById(serviceCreateRequest.getType_id()));
        try{
            serviceListRepository.save(s);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public boolean updateService(ServiceUpdateRequest serviceUpdateRequest)  {
        ServiceList s = serviceListRepository.getById(serviceUpdateRequest.getId());
        s.setPrice(serviceUpdateRequest.getPrice());
        try{
            serviceListRepository.save(s);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public boolean deleteService(ServiceRequestID serviceRequestID)  {
        ServiceList s = serviceListRepository.getById(serviceRequestID.getId());
        s.setDeleted(true);
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

    public List<ServiceType> getServicesType(){
        return serviceTypeRepository.findAll();
    }

    public List<ServiceList> getAvailableServices(){
        return serviceListRepository.getAvailableServices();
    }


}