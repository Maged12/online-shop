package online_shop.online_shop.service;
import online_shop.online_shop.dto.OrderDto;
import online_shop.online_shop.dto.OrderItemDto;

import java.util.List;

public interface OrderService {
    void createOrder(OrderDto orderDTO);
    OrderDto getOrderById(Long id);
    List<OrderDto> getAllOrders();
    void updateOrderItem(Long orderId, Long orderItemId, OrderItemDto orderItemDto);
    void deleteOrder(Long id);
    void updateOrder(Long id, OrderDto orderDTO);
}
