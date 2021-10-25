package inreco.vlgu.practic.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "service_in_request",schema = "public")
public class ServiceINrequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JsonManagedReference
    private Request request;

    @ManyToOne
    @JsonManagedReference
    private ServiceList service;

    @Column(name = "count")
    private Integer count;


    public ServiceINrequest() {
    }

    public ServiceINrequest(long id, Request request, ServiceList service, Integer count) {
        this.id = id;
        this.request = request;
        this.service = service;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public ServiceList getService() {
        return service;
    }

    public void setService(ServiceList service) {
        this.service = service;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

