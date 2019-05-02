package hu.student.projlab.mealride_api.repository;

import hu.student.projlab.mealride_api.domain.user.SpringUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<SpringUser, Long> {

    Optional<SpringUser> findByUsername(String email);

}
