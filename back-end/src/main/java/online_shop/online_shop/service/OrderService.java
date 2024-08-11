package online_shop.online_shop.service;

import java.util.List;

import online_shop.online_shop.dto.request.OrderRequestDto;
import online_shop.online_shop.dto.response.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderDto);

    OrderResponseDto getOrderById(Long id);

    List<OrderResponseDto> getAllOrders();

    // void updateOrderItem(Long orderId, Long orderItemId, OrderItemDto
    // orderItemDto);

    void deleteOrder(Long id);

    OrderResponseDto updateOrderStatus(Long id, String newStatus);
}
