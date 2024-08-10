package online_shop.online_shop.service;

import java.util.List;

import online_shop.online_shop.dto.OrderRequestDto;
import online_shop.online_shop.dto.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderDto);

    OrderResponseDto getOrderById(Long id);

    List<OrderResponseDto> getAllOrders();

    // void updateOrderItem(Long orderId, Long orderItemId, OrderItemDto
    // orderItemDto);

    void deleteOrder(Long id);

    OrderResponseDto updateOrderStatus(Long id, String newStatus);
}
