package online_shop.online_shop.dto.response;

import online_shop.online_shop.dto.UserResponseDto;

public record UserAuthResponse(
        String jwtToken,
        UserResponseDto user) {
}
