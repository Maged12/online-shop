package online_shop.online_shop.dto;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private UserDto userDto;
    private List<CartItemDto> items = new ArrayList<CartItemDto>();
    private double totalPrice;

    public CartDto() {
    }

    public CartDto(UserDto userDto, double totalPrice) {
        this.userDto = userDto;
        this.totalPrice = totalPrice;
    }

    public void addItem(CartItemDto item) {
        items.add(item);
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
