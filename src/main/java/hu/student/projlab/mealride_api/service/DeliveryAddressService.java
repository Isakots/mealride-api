package hu.student.projlab.mealride_api.service;

import hu.student.projlab.mealride_api.config.security.SecurityUtils;
import hu.student.projlab.mealride_api.domain.DeliveryAddress;
import hu.student.projlab.mealride_api.domain.User;
import hu.student.projlab.mealride_api.repository.DeliveryAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class DeliveryAddressService {

    private DeliveryAddressRepository deliveryAddressRepository;

    private UserService userService;

    @Autowired
    public DeliveryAddressService(DeliveryAddressRepository deliveryAddressRepository, UserService userService) {
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.userService = userService;
    }

    public List<DeliveryAddress> findAll() {
        return deliveryAddressRepository.findAllByUserId(
                userService.getCurrentUser(SecurityUtils.getCurrentUserLogin()).getId()).get();
        // TODO NoSuchElementException-Handler (for controller class)
    }

    /**
     * Saves address.
     *
     * @param address The address which should be persisted
     * @return The new address with generated ID
     */
    public DeliveryAddress addAddress(DeliveryAddress address) {
        User user = userService.getCurrentUser(SecurityUtils.getCurrentUserLogin());
        address.setUser(user);
        address.setCreationDate(LocalDateTime.now());
        return deliveryAddressRepository.save(address);
    }

    /**
     *
     * Updates address.
     *
     * @param address Address which needs to be updated.
     * @return The address with updates values
     */
    public DeliveryAddress updateAddress(DeliveryAddress address) {
        User user = userService.getCurrentUser(SecurityUtils.getCurrentUserLogin());

        List<DeliveryAddress> userAddresses = deliveryAddressRepository.findAllByUserId(user.getId()).get();
        if(!userAddresses.contains(address))
            throw new AccessDeniedException("It is not your address");

        return deliveryAddressRepository.save(address);
    }

    /**
     *
     * Deletes the relation to User.
     *
     * @param id ID of the address
     */
    public void deleteAddress(Long id) {
        DeliveryAddress address = deliveryAddressRepository.findById(id).get();
        address.setUser(null);
        address.setDeletionDate(LocalDateTime.now());
        deliveryAddressRepository.save(address);
    }

}
