package hu.student.projlab.mealride_api.repository;

import hu.student.projlab.mealride_api.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
