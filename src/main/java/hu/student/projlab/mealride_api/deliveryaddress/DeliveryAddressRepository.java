package hu.student.projlab.mealride_api.deliveryaddress;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

    List<DeliveryAddress> findAllByUserId(Long userId);

    DeliveryAddress getDeliveryAddressById(Long id);

}
