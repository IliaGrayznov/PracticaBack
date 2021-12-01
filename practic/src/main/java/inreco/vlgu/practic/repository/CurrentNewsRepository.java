package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.CurrentNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrentNewsRepository extends JpaRepository<CurrentNews,Integer> {
   List<CurrentNews> getAllByHotTrue();
   List<CurrentNews> getAllByTopTrue();
}
