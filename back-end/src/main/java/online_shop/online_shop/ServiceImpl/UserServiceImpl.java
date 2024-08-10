package online_shop.online_shop.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import online_shop.online_shop.domain.Role;
import online_shop.online_shop.domain.User;
import online_shop.online_shop.dto.UserRegisterRequest;
import online_shop.online_shop.repository.UserRepository;
import online_shop.online_shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    @Override
    public User registerNewUser(UserRegisterRequest userRegisterRequest) {

        User user = new User();
        user.setName(userRegisterRequest.name());
        user.setEmail(userRegisterRequest.email());
        String code = bcryptEncoder.encode(userRegisterRequest.password()).toString();
        user.setPassword(code);
        user.setRole(Role.CUSTOMER);
        return userRepository.save(user);
    }

    @Override
    public User registerNewAdmin(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setName(userRegisterRequest.name());
        user.setEmail(userRegisterRequest.email());
        user.setPassword(userRegisterRequest.password());
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }

}