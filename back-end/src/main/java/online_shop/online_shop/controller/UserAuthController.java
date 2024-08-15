package online_shop.online_shop.controller;

import java.util.Map;

import online_shop.online_shop.domain.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import online_shop.online_shop.dto.UserAuthRequest;
import online_shop.online_shop.dto.UserResponseDto;
import online_shop.online_shop.dto.request.UserRegisterRequest;
import online_shop.online_shop.dto.response.UserAuthResponse;
import online_shop.online_shop.service.UserService;
import online_shop.online_shop.util.JWTMgmtUtilityService;

@RestController
@RequestMapping(value = { "/api/auth" })
public class UserAuthController {

    private JWTMgmtUtilityService jwtMgmtUtilityService;
    private AuthenticationManager authenticationManager;
    private UserService userService;

    public UserAuthController(JWTMgmtUtilityService jwtMgmtUtilityService,
            AuthenticationManager authenticationManager,
            UserService userService) {
        this.jwtMgmtUtilityService = jwtMgmtUtilityService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping(value = { "/login" })
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserAuthRequest userAuthRequest)
            throws Exception {
        try {
            var email = userAuthRequest.email();
            var password = userAuthRequest.password();

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,
                            password));

            var jwtToken = jwtMgmtUtilityService.generateToken(email);
            var user = userService.getUserByEmail(email);
            if (user != null) {
                var userAuthResponse = new UserAuthResponse(jwtToken,
                        new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getAddress()));
                return ResponseEntity.ok(userAuthResponse);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (AuthenticationException ex) {
            String errorMessage = "Invalid email or password";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", errorMessage));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


    @PostMapping(value = { "/admin/login" })
    public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody UserAuthRequest userAuthRequest)
            throws Exception {
        try {
            var email = userAuthRequest.email();
            var password = userAuthRequest.password();

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,
                            password));

            var jwtToken = jwtMgmtUtilityService.generateToken(email);
            var user = userService.getUserByEmail(email);
            if (user != null && user.getRole() == Role.ADMIN) {
                var userAuthResponse = new UserAuthResponse(jwtToken,
                        new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getAddress()));
                return ResponseEntity.ok(userAuthResponse);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (AuthenticationException ex) {
            String errorMessage = "Invalid email or password";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", errorMessage));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


    @PostMapping(value = { "/register" })
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest userAuthRequest) {
        UserAuthResponse userAuthResponse = null;
        try {

            var email = userAuthRequest.email();

            var user = userService.registerNewUser(userAuthRequest);

            var jwtToken = jwtMgmtUtilityService.generateToken(email);

            if (user != null) {
                userAuthResponse = new UserAuthResponse(jwtToken,
                        new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getAddress()));
                return ResponseEntity.ok(userAuthResponse);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", ex.getMessage()));
        }
    }

    @PostMapping(value = { "/admin/register" })
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody UserRegisterRequest userAuthRequest) {
        UserAuthResponse userAuthResponse = null;
        try {

            var email = userAuthRequest.email();

            var user = userService.registerNewAdmin(userAuthRequest);

            var jwtToken = jwtMgmtUtilityService.generateToken(email);

            if (user != null) {
                userAuthResponse = new UserAuthResponse(jwtToken,
                        new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getAddress()));
                return ResponseEntity.ok(userAuthResponse);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", ex.getMessage()));
        }
    }

}
