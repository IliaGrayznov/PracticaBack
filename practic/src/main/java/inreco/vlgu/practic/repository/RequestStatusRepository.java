package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.Request;
import inreco.vlgu.practic.model.RequestStatus;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestStatusRepository extends JpaRepository<RequestStatus, Integer> {

}
