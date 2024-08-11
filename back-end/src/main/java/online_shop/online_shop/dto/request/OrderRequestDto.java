package online_shop.online_shop.dto.request;

import java.util.List;

import online_shop.online_shop.dto.OrderItemDto;

public record OrderRequestDto(double totalAmount, Long userId, List<OrderItemDto> orderItem) {
}