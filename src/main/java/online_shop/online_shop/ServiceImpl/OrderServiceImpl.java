package online_shop.online_shop.ServiceImpl;

import jakarta.transaction.Transactional;
import online_shop.online_shop.adapter.OrderAdapter;
import online_shop.online_shop.domain.Order;
import online_shop.online_shop.domain.OrderItem;
import online_shop.online_shop.dto.OrderDto;
import online_shop.online_shop.dto.OrderItemDto;
import online_shop.online_shop.repository.OrderItemRepository;
import online_shop.online_shop.repository.OrderRepository;
import online_shop.online_shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;


    @Override
    public void createOrder(OrderDto orderDTO) {
        Order order = OrderAdapter.getOrderFromOrderDto(orderDTO);
        orderRepository.save(order);
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        return OrderAdapter.getOrderDtoFromOrder(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return OrderAdapter.getOrderDtoListFromOrderList(orderRepository.findAll());
    }


    @Override
    public void updateOrder(Long id, OrderDto orderDTO) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus(orderDTO.getStatus());
            order.setTotalAmount(orderDTO.getTotalAmount());
            orderRepository.save(order);
        }
    }


    @Override
    public void updateOrderItem(Long orderId, Long orderItemId, OrderItemDto orderItemDto) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            OrderItem orderItem = orderItemRepository.findById(orderItemId).orElse(null);
            if (orderItem != null) {
                orderItem.setQuantity(orderItemDto.getQuantity());
                orderItem.setPrice(orderItemDto.getPrice());
                orderItemRepository.save(orderItem);
                updateOrderTotalAmount(order);
            }
        }
    }

    private void updateOrderTotalAmount(Order order) {
        double newTotalAmount = order.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalAmount(newTotalAmount);
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}
