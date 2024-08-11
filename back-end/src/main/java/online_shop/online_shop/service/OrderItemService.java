package online_shop.online_shop.service;

import online_shop.online_shop.dto.OrderItemDto;
import online_shop.online_shop.dto.response.OrderItemResponseDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemResponseDto> getAllOrderItems(Long orderId);

    OrderItemDto getOrderItemById(Long orderItemId);

    // void addOrderItemToOrder(Long orderId, OrderItemDto orderItemDto);
    void deleteOrderItem(Long orderId, Long orderItemId);
}
