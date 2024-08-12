package online_shop.online_shop.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import online_shop.online_shop.adapter.OrderAdapter;
import online_shop.online_shop.domain.Order;
import online_shop.online_shop.dto.request.OrderRequestDto;
import online_shop.online_shop.dto.response.OrderResponseDto;
import online_shop.online_shop.repository.OrderRepository;
import online_shop.online_shop.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderAdapter orderAdapter;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderDto) {

        var order = orderAdapter.toCreateOrderEntity(orderDto);
        var savedOrder = orderRepository.save(order);
        return orderAdapter.toResponseDto(savedOrder);
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        return orderAdapter.toResponseDto(order);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream().map(orderAdapter::toResponseDto).toList();
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long id, String newStatus) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(newStatus);
        var updatedOrder = orderRepository.save(order);

        return orderAdapter.toResponseDto(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}
