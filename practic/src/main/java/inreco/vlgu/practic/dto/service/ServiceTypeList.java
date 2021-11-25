package inreco.vlgu.practic.dto.service;


import inreco.vlgu.practic.model.ServiceType;

import java.util.List;


public class ServiceTypeList {

   private List<ServiceType> types;

    public ServiceTypeList(List<ServiceType> types) {
        this.types = types;
    }

    public List<ServiceType> getTypes() {
        return types;
    }

    public void setTypes(List<ServiceType> types) {
        this.types = types;
    }
}