package online_shop.online_shop.service;

import online_shop.online_shop.domain.User;
import online_shop.online_shop.dto.UserRegisterRequest;

public interface UserService {
    User getUserByEmail(String email);

    User registerNewUser(UserRegisterRequest userRegisterRequest);

    User registerNewAdmin(UserRegisterRequest userRegisterRequest);
}
