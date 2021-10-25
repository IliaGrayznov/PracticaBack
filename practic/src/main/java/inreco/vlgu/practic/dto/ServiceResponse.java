package inreco.vlgu.practic.dto;

import inreco.vlgu.practic.model.Car;
import inreco.vlgu.practic.model.ServiceList;

import java.util.List;

public class ServiceResponse {
    private List<ServiceList> services;


    public ServiceResponse(List<ServiceList> services) {
        this.services = services;
    }

    public List<ServiceList> getServices() {
        return services;
    }

    public void setServices(List<ServiceList> services) {
        this.services = services;
    }
}