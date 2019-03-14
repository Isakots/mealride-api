package hu.student.projlab.mealride_api.config.security;


import hu.student.projlab.mealride_api.domain.User;
import hu.student.projlab.mealride_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByEmail(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

       return new org.springframework.security.core.userdetails.User(user.get().getEmail(),user.get().getPassword(),user.get().getRoles());
    }


}
