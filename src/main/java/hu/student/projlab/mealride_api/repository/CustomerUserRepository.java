package hu.student.projlab.mealride_api.repository;

import hu.student.projlab.mealride_api.domain.user.CustomerUser;
import org.springframework.data.repository.CrudRepository;

public interface CustomerUserRepository extends CrudRepository<CustomerUser, Long> {
}
