package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select c from Car c where c.owner.id=:id")
    List<Car> getUserCars(@Param("id") long id);

}
