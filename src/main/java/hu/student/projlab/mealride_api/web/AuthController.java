package hu.student.projlab.mealride_api.web;

import com.google.gson.Gson;
import hu.student.projlab.mealride_api.config.security.JwtProvider;
import hu.student.projlab.mealride_api.domain.DeliveryAddress;
import hu.student.projlab.mealride_api.domain.Role;
import hu.student.projlab.mealride_api.domain.user.CustomerUser;
import hu.student.projlab.mealride_api.domain.user.SpringUser;
import hu.student.projlab.mealride_api.repository.*;
import hu.student.projlab.mealride_api.service.dto.SignUpForm;
import hu.student.projlab.mealride_api.service.dto.UserDTO;
import hu.student.projlab.mealride_api.web.exceptionhandler.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerUserRepository customerUserRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DeliveryAddressRepository addressRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserDTO loginRequest) {

        SpringUser springUser = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(loginRequest.getUsername()));

        if (!encoder.matches(loginRequest.getPassword(), springUser.getPassword()))
            throw new AccessDeniedException("Wrong password.");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword(), springUser.getRoles()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(),
                userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm form) {

        // Check if username (email) is already taken
        if (userRepository.findByUsername(form.getUsername()).isPresent()) {
            return new ResponseEntity<>(new Gson().toJson(new Message("Fail -> Username is already taken!")),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's basic account
        SpringUser user = new SpringUser();
        user.setUsername(form.getUsername());
        user.setPassword(encoder.encode(form.getPassword()));
        Role role = roleRepository.findByRole("ROLE_USER");
        user.getRoles().add(role);
        userRepository.save(user);

        // Creating user's customer account
        CustomerUser customerUser = new CustomerUser();
        customerUser.setFirstname(form.getFirstname());
        customerUser.setLastname(form.getLastname());
        customerUser.setPhone(form.getPhone());
        customerUserRepository.save(customerUser);

        user.setCustomerUser(customerUser);
        userRepository.save(user);

        // Creating user's first delivery address
        setAddressToUser(form, customerUser);

        return new ResponseEntity<>(new Gson().toJson(new Message("User registered successfully!")), HttpStatus.CREATED);
    }

    private void setAddressToUser(SignUpForm form, CustomerUser customerUser) {
        DeliveryAddress address = new DeliveryAddress();
        address.setZipcode(form.getZipcode());
        address.setCity(form.getCity());
        address.setState(form.getState());
        address.setStreet(form.getStreet());
        address.setHousenumber(form.getHousenumber());
        address.setCustomer(customerUser);
        address.setCreationDate(LocalDateTime.now());

        addressRepository.save(address);
    }
}
