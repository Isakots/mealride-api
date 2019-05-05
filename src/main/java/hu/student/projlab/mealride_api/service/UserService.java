package hu.student.projlab.mealride_api.service;


import hu.student.projlab.mealride_api.config.security.SecurityUtils;
import hu.student.projlab.mealride_api.domain.user.CustomerUser;
import hu.student.projlab.mealride_api.domain.user.SpringUser;
import hu.student.projlab.mealride_api.exception.UserIsNotAuthenticatedException;
import hu.student.projlab.mealride_api.repository.CustomerUserRepository;
import hu.student.projlab.mealride_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private UserRepository userRepository;

    private CustomerUserRepository customerUserRepository;

    @Autowired
    public UserService(UserRepository userRepository, CustomerUserRepository customerUserRepository) {
        this.userRepository = userRepository;
        this.customerUserRepository = customerUserRepository;
    }

    public List<SpringUser> findAllWorkers(Long restaurantId) {
        return userRepository.findAllByRestaurantUserRestaurantId(restaurantId)
                .orElse(Collections.emptyList());
    }

    public Optional<SpringUser> findByEmail(String email) {
        return userRepository.findByUsername(email);
    }

    /**
     * Note: This is a bad solution. I cant store yet the authorities of a CustomerUser in SecurityContext so
     * I implement this function as a temporary solution to let me move on.
     *
     * @return the database CustomerUser of the current login
     */
    public SpringUser getCurrentUser(Optional<String> email) throws UserIsNotAuthenticatedException {
        return userRepository.findByUsername(email.get()).orElseThrow(UserIsNotAuthenticatedException::new);
    }

    public void save(SpringUser springUser) {
        userRepository.save(springUser);
    }

    public CustomerUser getUserData() {
        return this.getCurrentUser(SecurityUtils.getCurrentUserLogin()).getCustomerUser();
    }

    public CustomerUser modifyUserData(CustomerUser customerUser) {
        validateID(customerUser);
        return customerUserRepository.save(customerUser);
    }

    private void validateID(CustomerUser customerUser) {
        if (this.getCurrentUser(SecurityUtils.getCurrentUserLogin()).getCustomerUser().getId() !=
                customerUser.getId())
            throw new AccessDeniedException("Invalid operation");
    }

}
