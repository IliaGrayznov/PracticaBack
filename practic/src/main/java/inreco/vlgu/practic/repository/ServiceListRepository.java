package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.ServiceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceListRepository extends JpaRepository<ServiceList, Long> {
}
