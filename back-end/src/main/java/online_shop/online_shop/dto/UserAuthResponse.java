package online_shop.online_shop.dto;

public record UserAuthResponse(
                String jwtToken,
                UserResponseDto user) {
}
