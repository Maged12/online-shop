package online_shop.online_shop.adapter;

import online_shop.online_shop.domain.Order;
import online_shop.online_shop.dto.OrderDto;

import java.util.ArrayList;
import java.util.List;

import static online_shop.online_shop.adapter.UserAdapter.getUserDtoFromUser;
import static online_shop.online_shop.adapter.UserAdapter.getUserFromUserDto;

public class OrderAdapter {
    public static Order getOrderFromOrderDto(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderDate(orderDto.getOrderDate());
        order.setStatus(orderDto.getStatus());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setUser(getUserFromUserDto(orderDto.getUserDto()));
        return order;
    }

    public static OrderDto getOrderDtoFromOrder(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setStatus(order.getStatus());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setUserDto(getUserDtoFromUser(order.getUser()));
        return orderDto;
    }

    public static List<Order> getOrderListFromOrderDtoList(List<OrderDto> orderDtoList) {
        List<Order> orderList = new ArrayList<>();
        for (OrderDto orderDto : orderDtoList) {
            orderList.add(getOrderFromOrderDto(orderDto));
        }
        return orderList;
    }

    public static List<OrderDto> getOrderDtoListFromOrderList(List<Order> orderList) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orderList) {
            orderDtoList.add(getOrderDtoFromOrder(order));
        }
        return orderDtoList;
    }
}
