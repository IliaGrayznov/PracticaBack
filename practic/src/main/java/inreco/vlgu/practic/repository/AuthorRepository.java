package inreco.vlgu.practic.repository;

import inreco.vlgu.practic.model.Author;
import inreco.vlgu.practic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Author findAuthorByUser(User user);
}
