package hu.student.projlab.mealride_api.deliveryaddress;


import hu.student.projlab.mealride_api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressService {

    private DeliveryAddressRepository deliveryAddressRepository;

    private UserService userService;

    @Autowired
    public DeliveryAddressService(DeliveryAddressRepository deliveryAddressRepository, UserService userService) {
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.userService = userService;
    }

    List<DeliveryAddress> findAll() {
        return deliveryAddressRepository.findAll();
    }

    void addAddress(DeliveryAddress address) {
        deliveryAddressRepository.save(address);
    }

}
