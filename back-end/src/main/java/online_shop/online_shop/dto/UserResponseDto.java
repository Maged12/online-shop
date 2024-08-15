package online_shop.online_shop.dto;

import online_shop.online_shop.domain.Address;
import online_shop.online_shop.domain.Role;

public record UserResponseDto(Long id, String name, String email, Role role, Address address) {

}