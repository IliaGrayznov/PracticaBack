package inreco.vlgu.practic.dto.request;


import inreco.vlgu.practic.model.ServiceList;

import java.util.List;

public class ServiceListResponse {
    private List<ServiceList> services;


    public ServiceListResponse(List<ServiceList> services) {
        this.services = services;
    }

    public List<ServiceList> getServices() {
        return services;
    }

    public void setServices(List<ServiceList> services) {
        this.services = services;
    }
}