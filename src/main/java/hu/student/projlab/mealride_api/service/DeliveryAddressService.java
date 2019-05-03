package hu.student.projlab.mealride_api.service;

import hu.student.projlab.mealride_api.config.security.SecurityUtils;
import hu.student.projlab.mealride_api.domain.DeliveryAddress;
import hu.student.projlab.mealride_api.exception.UserIsNotAuthenticatedException;
import hu.student.projlab.mealride_api.repository.DeliveryAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
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

    public List<DeliveryAddress> findAll() throws UserIsNotAuthenticatedException {
        return deliveryAddressRepository.findAllByCustomerId(
                userService.getCurrentUser(SecurityUtils.getCurrentUserLogin())
                        .getCustomerUser().getId())
                .orElse(Collections.emptyList());
    }

    /**
     * Saves address.
     *
     * @param address The address which needs to be persisted
     * @return The new address with generated ID
     */
    public DeliveryAddress addAddress(DeliveryAddress address) throws UserIsNotAuthenticatedException {
        address.setCustomer(
                userService.getCurrentUser(SecurityUtils.getCurrentUserLogin())
                        .getCustomerUser());

        address.setCreationDate(LocalDateTime.now());
        return deliveryAddressRepository.save(address);
    }

    /**
     * Updates address.
     *
     * @param address Address which needs to be updated.
     * @return The address with updated values
     */
    public DeliveryAddress updateAddress(DeliveryAddress address) throws UserIsNotAuthenticatedException {
        checkIfUserHasSpecifiedAddress(address);
        return deliveryAddressRepository.save(address);
    }

    /**
     * Deletes the relation to CustomerUser.
     *
     * @param id ID of the address
     */
    public void deleteAddress(Long id) throws UserIsNotAuthenticatedException {
        DeliveryAddress address = deliveryAddressRepository.findById(id).get();
        checkIfUserHasSpecifiedAddress(address);
        address.setCustomer(null);
        address.setDeletionDate(LocalDateTime.now());
        deliveryAddressRepository.save(address);
    }

    private void checkIfUserHasSpecifiedAddress(DeliveryAddress address) throws UserIsNotAuthenticatedException {
        List<DeliveryAddress> userAddresses =
                deliveryAddressRepository.findAllByCustomerId(
                        userService.getCurrentUser(SecurityUtils.getCurrentUserLogin())
                                .getCustomerUser().getId())
                        .orElse(Collections.emptyList());

        if (!userAddresses.contains(address))
            throw new AccessDeniedException("Address not found.");
    }


}
