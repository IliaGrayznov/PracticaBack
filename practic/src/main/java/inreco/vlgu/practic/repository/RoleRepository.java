package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.ERole;
import inreco.vlgu.practic.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
