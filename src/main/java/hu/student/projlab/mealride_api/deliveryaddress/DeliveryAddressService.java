package hu.student.projlab.mealride_api.deliveryaddress;


import hu.student.projlab.mealride_api.config.security.SecurityUtils;
import hu.student.projlab.mealride_api.user.User;
import hu.student.projlab.mealride_api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
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

    Optional<List<DeliveryAddress>> findAll() {
        try {
            return deliveryAddressRepository.findAllByUserId(userService.getCurrentUser(SecurityUtils.getCurrentUserLogin()).getId());
        }
        catch(NoSuchElementException e) {
            return new Optional<>(Collections.emptyList());
        }
    }

    void addAddress(DeliveryAddress address) throws NoSuchElementException{
        try {
            User user = userService.getCurrentUser(SecurityUtils.getCurrentUserLogin());
            address.setUser(user);
            address.setCreationDate(LocalDateTime.now());
            deliveryAddressRepository.save(address);
        }
        catch(NoSuchElementException e) {
            throw new NoSuchElementException();
        }
    }

    void updateAddress(DeliveryAddress address) {
        try {
            if (address.getId() != null) {
                deliveryAddressRepository.save(address);
            }
        }
        catch(Exception e) {

        }
    }

    void deleteAddress(DeliveryAddress address) {
        address.setUser(null);
        address.setDeletionDate(LocalDateTime.now());
        deliveryAddressRepository.save(address);
    }

}
