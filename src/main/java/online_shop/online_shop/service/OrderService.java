package online_shop.online_shop.service;

import java.util.List;

import online_shop.online_shop.dto.OrderRequestDto;
import online_shop.online_shop.dto.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderDto);

    OrderRequestDto getOrderById(Long id);

    List<OrderRequestDto> getAllOrders();

    // void updateOrderItem(Long orderId, Long orderItemId, OrderItemDto
    // orderItemDto);

    void deleteOrder(Long id);

    void updateOrderStatus(Long id, String newStatus);
}
