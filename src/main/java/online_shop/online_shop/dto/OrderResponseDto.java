package online_shop.online_shop.dto;

import java.util.Date;
import java.util.List;

public record OrderResponseDto(Long id, double totalAmount, Date orderDate, String status, UserResponseDto user,
        List<OrderItemResponseDto> orderItems) {

}
