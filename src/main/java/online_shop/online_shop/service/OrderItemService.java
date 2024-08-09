package online_shop.online_shop.service;

import online_shop.online_shop.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> getAllOrderItems(Long orderId);
    OrderItemDto getOrderItemById(Long orderId, Long orderItemId);
    void addOrderItemToOrder(Long orderId, OrderItemDto orderItemDto);
    void deleteOrderItem(Long orderId, Long orderItemId);
}
