package inreco.vlgu.practic.service;

import inreco.vlgu.practic.dto.request.InputRequestID;
import inreco.vlgu.practic.model.Request;
import inreco.vlgu.practic.model.ServiceList;
import inreco.vlgu.practic.model.User;

import java.util.Date;
import java.util.List;

public interface RequestService {
    public boolean createRequest(User client, long id_car, Date date, Long idS1, Long idS2, Long idS3);
    public String acceptRequest(User master, InputRequestID inputRequestID);
    public String rejectRequest(InputRequestID inputRequestID, User master);
    public List<ServiceList> service(InputRequestID inputRequestID);
    public String finishService(InputRequestID inputRequestID);
    public boolean payService(InputRequestID inputRequestID);
    public List<Request> getRequests();
    public List<Request> getMastersRequests(Long id);
    public Request getOneRequest(long id);
    public List<Request> getRequestsByStatus(Integer id_status);
}
