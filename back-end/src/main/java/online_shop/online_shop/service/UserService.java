package online_shop.online_shop.service;

import online_shop.online_shop.domain.User;
import online_shop.online_shop.dto.request.UserRegisterRequest;

public interface UserService {
    User getUserByEmail(String email);

    User registerNewUser(UserRegisterRequest userRegisterRequest) throws Exception;

    User registerNewAdmin(UserRegisterRequest userRegisterRequest) throws Exception;
}
