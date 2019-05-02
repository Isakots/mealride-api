package hu.student.projlab.mealride_api.service;


import hu.student.projlab.mealride_api.domain.user.SpringUser;
import hu.student.projlab.mealride_api.repository.RoleRepository;
import hu.student.projlab.mealride_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder PasswordEncoder;

    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder PasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.PasswordEncoder = PasswordEncoder;
        this.roleRepository = roleRepository;
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
    public Optional<SpringUser> getCurrentUser(Optional<String> email) {
        return userRepository.findByUsername(email.get());
    }
}
