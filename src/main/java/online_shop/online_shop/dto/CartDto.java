package online_shop.online_shop.dto;

import online_shop.online_shop.domain.User;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private User user;
    private List<CartItemDto> cartItems;
    private double totalPrice;

    public CartDto() {}
    public CartDto(User user, double totalPrice) {
        this.user = user;
        this.cartItems = new ArrayList<>();
    }
    public void addCartItem(CartItemDto cartItemDto){
        this.cartItems.add(cartItemDto);
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
