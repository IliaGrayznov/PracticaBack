package inreco.vlgu.practic.dto;

import inreco.vlgu.practic.model.Car;
import inreco.vlgu.practic.model.Request;

import java.util.List;

public class RequestResponse {
    private List<Request> requests;

    public RequestResponse(List<Request> requests) {
        this.requests = requests;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}