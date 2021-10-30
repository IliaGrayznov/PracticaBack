package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.Car;
import inreco.vlgu.practic.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("select r from Request r where r.status.id=:id")
    List<Request> getRequestsByStatus(@Param("id") Integer status_id);
}
