package hu.student.projlab.mealride_api.repository;

import hu.student.projlab.mealride_api.domain.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

    Optional<List<DeliveryAddress>> findAllByCustomerUserId(Long userId);

}
