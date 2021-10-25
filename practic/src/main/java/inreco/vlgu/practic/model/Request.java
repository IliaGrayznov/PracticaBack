package inreco.vlgu.practic.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "request",schema = "public")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JsonManagedReference
    private User client;

    @ManyToOne
    @JsonManagedReference
    private User master;

    @OneToOne
    private Car car;

    @ManyToOne
    @JoinColumn(name = "request_status_id")
    private RequestStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "service_in_request",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<ServiceList> services;

    public Request() {
    }

    public Request(long id, User client, User master, Car car, RequestStatus status, List<ServiceList> services) {
        this.id = id;
        this.client = client;
        this.master = master;
        this.car = car;
        this.status = status;
        this.services = services;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public List<ServiceList> getServices() {
        return services;
    }

    public void setServices(List<ServiceList> services) {
        this.services = services;
    }
}

