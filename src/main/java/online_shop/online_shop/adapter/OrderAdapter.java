package online_shop.online_shop.adapter;

import online_shop.online_shop.domain.Order;
import online_shop.online_shop.dto.OrderDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderAdapter {
    public static OrderDto getOrderDtoFromOrder(Order order) {
        if (order == null) return null;

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setStatus(order.getStatus());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setUserDto(UserAdapter.getUserDtoFromUser(order.getUser()));
        orderDto.setOrderItemDtos(order.getOrderItems().stream()
                .map(OrderItemAdapter::getOrderItemDtoFromOrderItem)
                .collect(Collectors.toList()));
        return orderDto;
    }

    public static Order getOrderFromOrderDto(OrderDto orderDto) {
        if (orderDto == null) return null;

        Order order = new Order();
        order.setOrderDate(orderDto.getOrderDate());
        order.setStatus(orderDto.getStatus());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setUser(UserAdapter.getUserFromUserDto(orderDto.getUserDto()));
        order.setOrderItems(orderDto.getOrderItemDtos().stream()
                .map(OrderItemAdapter::getOrderItemFromOrderItemDto)
                .collect(Collectors.toList()));

        return order;
    }

    public static List<OrderDto> getOrderDtoListFromOrderList(List<Order> orderList) {
        return orderList.stream()
                .map(OrderAdapter::getOrderDtoFromOrder)
                .collect(Collectors.toList());
    }

    public static List<Order> getOrderListFromOrderDtoList(List<OrderDto> orderDtoList) {
        return orderDtoList.stream()
                .map(OrderAdapter::getOrderFromOrderDto)
                .collect(Collectors.toList());
    }
}