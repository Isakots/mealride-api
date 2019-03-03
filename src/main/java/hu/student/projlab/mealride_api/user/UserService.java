package hu.student.projlab.mealride_api.user;


import hu.student.projlab.mealride_api.config.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    /**
     * Note: This is a bad solution. I cant store yet the authorities of a User in SecurityContext so
     * I implement this function as a temporary solution to let me move on.
     *
     * @return the database User of the current login
     */
    public User getCurrentUser(String email) {
        return userRepository.findByEmail(email);
    }





}
