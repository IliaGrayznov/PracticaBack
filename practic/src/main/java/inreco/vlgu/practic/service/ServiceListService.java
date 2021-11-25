package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.service.ServiceCreateRequest;
import inreco.vlgu.practic.dto.service.ServiceRequestID;
import inreco.vlgu.practic.dto.service.ServiceUpdateRequest;
import inreco.vlgu.practic.model.ServiceList;
import inreco.vlgu.practic.model.ServiceType;

import java.util.List;

public interface ServiceListService {
    public boolean createService(ServiceCreateRequest serviceCreateRequest);
    public boolean updateService(ServiceUpdateRequest serviceUpdateRequest);
    public boolean deleteService(ServiceRequestID serviceRequestID);
    public List<ServiceList> getAllServices();
    public List<ServiceType> getServicesType();
    public List<ServiceList> getAvailableServices();
}
