package hu.student.projlab.mealride_api.web;

import hu.student.projlab.mealride_api.domain.user.CustomerUser;
import hu.student.projlab.mealride_api.service.UserService;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = EndpointConstants.USER_ENDPOINT)
class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<CustomerUser> getPersonalData() {
        return ResponseEntity.ok(userService.getUserData());
    }

    @PostMapping("/profile")
    public ResponseEntity<CustomerUser> modifyPersonalData(@RequestBody CustomerUser customerUser) {
        CustomerUser modifiedUser = userService.modifyUserData(customerUser);
        return ResponseEntity.ok(modifiedUser);
    }


}
