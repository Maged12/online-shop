package online_shop.online_shop.adapter;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import online_shop.online_shop.domain.Order;
import online_shop.online_shop.domain.OrderItem;
import online_shop.online_shop.domain.Product;
import online_shop.online_shop.domain.User;
import online_shop.online_shop.dto.UserResponseDto;
import online_shop.online_shop.dto.request.OrderRequestDto;
import online_shop.online_shop.dto.response.OrderResponseDto;

@Component
public class OrderAdapter {

    public OrderResponseDto toResponseDto(Order order) {

        var orderUser = new UserResponseDto(order.getUser().getId(), order.getUser().getName(),
                order.getUser().getEmail(), order.getUser().getRole(), order.getUser().getAddress());

        var orderItemsList = OrderItemAdapter.getOrderItemResponseDtoListFromOrderItemList(order.getOrderItems());

        return new OrderResponseDto(order.getId(), order.getTotalAmount(), order.getOrderDate(), order.getStatus(),
                orderUser, orderItemsList);
    }

    public Order toCreateOrderEntity(OrderRequestDto orderDto) {
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setStatus("Pending");
        order.setTotalAmount(orderDto.totalAmount());
        order.setUser(new User(orderDto.userId()));

        List<OrderItem> orderItems = orderDto.orderItem().stream()
                .map(orderItemDto -> new OrderItem(null, orderItemDto.quantity(), orderItemDto.price(),
                        new Product(orderItemDto.productId()), order))
                .toList();

        order.setOrderItems(orderItems);

        return order;
    }

}