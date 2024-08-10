package online_shop.online_shop.dto;

import java.util.List;
import java.util.Optional;

public record OrderRequestDto(double totalAmount, Long userId, Optional<List<OrderItemDto>> orderItem) {
}