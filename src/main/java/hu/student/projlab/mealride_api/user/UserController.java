package hu.student.projlab.mealride_api.user;


import hu.student.projlab.mealride_api.config.security.SecurityConstants;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = EndpointConstants.USER_ENDPOINT)
class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(SecurityConstants.SIGN_UP_URL)
    public ResponseEntity<Object> signUp(@RequestBody @Valid UserDTO dto) {

        return ResponseEntity.ok(dto);
    }


}
