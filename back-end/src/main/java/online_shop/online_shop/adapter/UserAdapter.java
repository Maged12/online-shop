package online_shop.online_shop.adapter;

import online_shop.online_shop.domain.User;
import online_shop.online_shop.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter {
    public static User getUserFromUserDto(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }

    public static UserDto getUserDtoFromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public static List<User> getUserListFromUserDtoList(List<UserDto> userDtoList) {
        List<User> userList = new ArrayList<>();
        for (UserDto userDto : userDtoList) {
            userList.add(getUserFromUserDto(userDto));
        }
        return userList;
    }

    public static List<UserDto> getUserDtoListFromUserList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(getUserDtoFromUser(user));
        }
        return userDtoList;
    }
}
