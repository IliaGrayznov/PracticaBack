package inreco.vlgu.practic.dto.request;

import inreco.vlgu.practic.model.Car;
import inreco.vlgu.practic.model.Request;

import java.util.List;

public class RequestListResponse {
    private List<Request> requests;

    public RequestListResponse(List<Request> requests) {
        this.requests = requests;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}