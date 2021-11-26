package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.ERole;
import inreco.vlgu.practic.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
