package online_shop.online_shop.controller;

import jakarta.validation.Valid;
import online_shop.online_shop.dto.UserAuthRequest;
import online_shop.online_shop.dto.UserResponseDto;
import online_shop.online_shop.dto.request.UserRegisterRequest;
import online_shop.online_shop.dto.response.UserAuthResponse;
import online_shop.online_shop.service.UserService;
import online_shop.online_shop.util.JWTMgmtUtilityService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

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
    public ResponseEntity<UserAuthResponse> authenticateUser(@Valid @RequestBody UserAuthRequest userAuthRequest)
            throws Exception {
        UserAuthResponse userAuthResponse = null;
        try {
            var email = userAuthRequest.email();
            var password = userAuthRequest.password();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,
                            password));
            var jwtToken = jwtMgmtUtilityService.generateToken(email);
            var user = userService.getUserByEmail(email);
            if (user != null) {
                userAuthResponse = new UserAuthResponse(jwtToken,
                        new UserResponseDto(user.getId(), user.getName(), user.getEmail()));
            }
        } catch (Exception ex) {
            System.out.println("UserAuthException is: " + ex);
            throw ex;
        }
        return ResponseEntity.ok(userAuthResponse);
    }

    @PostMapping(value = { "/register" })
    public ResponseEntity<UserAuthResponse> register(@Valid @RequestBody UserRegisterRequest userAuthRequest)
            throws Exception {
        UserAuthResponse userAuthResponse = null;
        try {

            var email = userAuthRequest.email();
            var password = userAuthRequest.password();
            var user = userService.registerNewUser(userAuthRequest);

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,
                            password));
            var jwtToken = jwtMgmtUtilityService.generateToken(email);
            if (user != null) {
                userAuthResponse = new UserAuthResponse(jwtToken,
                        new UserResponseDto(user.getId(), user.getName(), user.getEmail()));
            }
        } catch (Exception ex) {
            System.out.println("UserAuthException is: " + ex);
            throw ex;
        }
        return ResponseEntity.ok(userAuthResponse);
    }

}
