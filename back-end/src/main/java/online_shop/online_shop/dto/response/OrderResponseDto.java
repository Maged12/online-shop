package online_shop.online_shop.dto.response;

import java.util.Date;
import java.util.List;

import online_shop.online_shop.dto.UserResponseDto;

public record OrderResponseDto(Long id, double totalAmount, Date orderDate, String status, UserResponseDto user,
                List<OrderItemResponseDto> orderItems) {

}
