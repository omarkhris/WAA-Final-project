package com.miu.pmtbackendapi.resource.user;

import com.miu.pmtbackendapi.domain.user.request.ForgotPassword;
import com.miu.pmtbackendapi.domain.user.request.UserDTO;
import com.miu.pmtbackendapi.domain.user.response.UserResponse;
import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;
import com.miu.pmtbackendapi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<?> getAllUsers(@RequestParam(value = "role", required = false) String role) {
        return ResponseEntity.ok(userService.getAllUsers(role));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) throws ItemNotFoundException {

        Optional<UserResponse> oUser = userService.getUser(id);

        if (oUser.isPresent()) {
            return ResponseEntity.ok().body(oUser.get());
        } else {
            throw new ItemNotFoundException("User not found.");
        }
    }

    @PostMapping(path = "/registration", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PutMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) throws ItemNotFoundException {
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }

    @DeleteMapping(path = "/{uId}")
    public ResponseEntity<?> deleteUser(@PathVariable("uId") Long userId) throws ItemNotFoundException {
        Boolean deleted = userService.deleteUser(userId);

        if (deleted) {
            return ResponseEntity.ok(new ResponseEntity<>("User deleted successfully.", HttpStatus.OK));
        } else {
            throw new ItemNotFoundException("Unable to delete the user. User with " + userId + " is unavailable.");
        }
    }


    @PutMapping(path = "/forgotpassword", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassword fgDTO) throws ItemNotFoundException {
        return ResponseEntity.ok(userService.forgotPassword(fgDTO));
    }

    @PutMapping(path = "/forgotpassword/admin", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> forgotPasswordAdmin(@RequestBody ForgotPassword fgDTO) throws ItemNotFoundException {
        return ResponseEntity.ok(userService.forgotPasswordAdmin(fgDTO));
    }

}
