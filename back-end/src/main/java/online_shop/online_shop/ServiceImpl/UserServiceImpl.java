package online_shop.online_shop.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import online_shop.online_shop.domain.Role;
import online_shop.online_shop.domain.User;
import online_shop.online_shop.dto.request.UserRegisterRequest;
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
    public User registerNewUser(UserRegisterRequest userRegisterRequest) throws Exception {
        try {
            User user = new User();
            user.setName(userRegisterRequest.name());
            user.setEmail(userRegisterRequest.email());
            String code = bcryptEncoder.encode(userRegisterRequest.password()).toString();
            user.setPassword(code);
            user.setRole(Role.CUSTOMER);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new Exception("Email already exists");
        }
    }

    @Override
    public User registerNewAdmin(UserRegisterRequest userRegisterRequest) throws Exception {
        try {
            User user = new User();
            user.setName(userRegisterRequest.name());
            user.setEmail(userRegisterRequest.email());
            String code = bcryptEncoder.encode(userRegisterRequest.password()).toString();
            user.setPassword(code);
            user.setRole(Role.ADMIN);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new Exception("Email already exists");
        }
    }

}
