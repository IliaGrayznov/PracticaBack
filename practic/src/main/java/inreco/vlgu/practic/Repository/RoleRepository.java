package inreco.vlgu.practic.Repository;

import inreco.vlgu.practic.Model.ERole;
import inreco.vlgu.practic.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
