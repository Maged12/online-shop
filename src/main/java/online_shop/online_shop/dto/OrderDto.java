package online_shop.online_shop.dto;

import online_shop.online_shop.domain.OrderItem;
import online_shop.online_shop.domain.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDto {
    private Date orderDate;
    private String status;
    private double totalAmount;
    private UserDto userDto;
    private List<OrderItemDto> itemDtos = new ArrayList<OrderItemDto>();

    public OrderDto() {}

    public OrderDto(Date orderDate, String status, double totalAmount, UserDto userDto) {
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.userDto = userDto;
    }

    public void addItemDto(OrderItemDto orderItemDto) {
        itemDtos.add(orderItemDto);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<OrderItemDto> getItemDtos() {
        return itemDtos;
    }

    public void setItemDtos(List<OrderItemDto> itemDtos) {
        this.itemDtos = itemDtos;
    }
}
