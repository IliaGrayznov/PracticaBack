package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.CurrentNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentNewsRepository extends JpaRepository<CurrentNews,Integer> {
}
