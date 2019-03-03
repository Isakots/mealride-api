package hu.student.projlab.mealride_api.deliveryaddress;


import hu.student.projlab.mealride_api.config.security.SecurityUtils;
import hu.student.projlab.mealride_api.user.User;
import hu.student.projlab.mealride_api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryAddressService {

    private DeliveryAddressRepository deliveryAddressRepository;

    private UserService userService;

    @Autowired
    public DeliveryAddressService(DeliveryAddressRepository deliveryAddressRepository, UserService userService) {
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.userService = userService;
    }

    Optional<DeliveryAddress> findById(Long id) {
        return deliveryAddressRepository.findDeliveryAddressById(id);
    }

    List<DeliveryAddress> findAll() {
        return deliveryAddressRepository.findAllByUserId(userService.getCurrentUser(SecurityUtils.getCurrentUserLogin().get()).getId());
    }

    void addAddress(DeliveryAddress address) {
        User user = userService.getCurrentUser(SecurityUtils.getCurrentUserLogin().get());
        address.setUser(user);
        address.setCreationDate(LocalDateTime.now());
        deliveryAddressRepository.save(address);
    }

    void updateAddress(DeliveryAddress address) {
        if(address.getId() != null) {
            deliveryAddressRepository.save(address);
        }
    }

    void deleteAddress(DeliveryAddress address) {
        address.setUser(null);
        address.setDeletionDate(LocalDateTime.now());
        deliveryAddressRepository.save(address);
    }

}
