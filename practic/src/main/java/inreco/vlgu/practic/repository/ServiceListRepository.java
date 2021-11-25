package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.ServiceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceListRepository extends JpaRepository<ServiceList, Long> {
    @Query("select s from ServiceList s where s.deleted=false ")
    List<ServiceList> getAvailableServices();
}
