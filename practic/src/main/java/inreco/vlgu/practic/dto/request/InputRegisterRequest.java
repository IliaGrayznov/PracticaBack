package inreco.vlgu.practic.dto.request;


import java.util.Date;
import java.util.List;

public class InputRegisterRequest {
    private long car_id;

    private Date date;

    private List<Long> services;

    public InputRegisterRequest(long car_id, Date date, List<Long> services) {
        this.car_id = car_id;
        this.date = date;
        this.services=services;
    }

    public long getCar_id() {
        return car_id;
    }

    public void setCar_id(long car_id) {
        this.car_id = car_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Long> getServices() {
        return services;
    }

    public void setServices(List<Long> services) {
        this.services = services;
    }
}