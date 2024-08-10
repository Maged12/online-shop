package online_shop.online_shop.adapter;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import online_shop.online_shop.domain.Order;
import online_shop.online_shop.domain.OrderItem;
import online_shop.online_shop.domain.Product;
import online_shop.online_shop.domain.User;
import online_shop.online_shop.dto.OrderItemResponseDto;
import online_shop.online_shop.dto.OrderRequestDto;
import online_shop.online_shop.dto.OrderResponseDto;
import online_shop.online_shop.dto.UserResponseDto;
import online_shop.online_shop.repository.ProductRepository;
import online_shop.online_shop.repository.UserRepository;

@Component
public class OrderAdapter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public OrderRequestDto getOrderDtoFromOrder(Order order) {
        if (order == null)
            return null;
        OrderRequestDto orderDto = new OrderRequestDto(order.getTotalAmount(), order.getUser().getId(),
                Optional.of(
                        order.getOrderItems().stream().map(OrderItemAdapter::getOrderItemDtoFromOrderItem).toList()));
        return orderDto;
    }

    public List<OrderRequestDto> getOrderDtoListFromOrderList(List<Order> orderList) {
        return orderList.stream()
                .map(this::getOrderDtoFromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponseDto toResponseDto(Order order) {
        var orderUser = new UserResponseDto(order.getUser().getId(), order.getUser().getName(),
                order.getUser().getEmail());

        var orderItemsList = order.getOrderItems().stream().map(orderItem -> {
            return new OrderItemResponseDto(orderItem.getId(), orderItem.getQuantity(),
                    orderItem.getPrice(), ProductAdapter.getProductDtoFromProduct(orderItem.getProduct()));
        }).toList();

        return new OrderResponseDto(order.getId(), order.getTotalAmount(), order.getOrderDate(), order.getStatus(),
                orderUser, orderItemsList);
    }

    public Order toEntity(OrderRequestDto orderDto) {
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setStatus("Pending");
        order.setTotalAmount(orderDto.totalAmount());

        final User user = userRepository.findById(orderDto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);

        List<OrderItem> orderItems = orderDto.orderItem().get().stream().map(orderItemDto -> {
            Product prod = productRepository.findById(orderItemDto.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            var orderItem = new OrderItem(null, orderItemDto.quantity(), orderItemDto.price(), prod, null);
            return orderItem;
        }).toList();
        order.setOrderItems(orderItems);
        return order;
    }

}