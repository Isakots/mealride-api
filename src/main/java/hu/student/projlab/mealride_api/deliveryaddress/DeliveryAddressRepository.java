package hu.student.projlab.mealride_api.deliveryaddress;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

    Optional<List<DeliveryAddress>> findAllByUserId(Long userId);

    Optional<DeliveryAddress> findDeliveryAddressById(Long id);

}
