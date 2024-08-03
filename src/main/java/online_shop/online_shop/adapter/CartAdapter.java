package online_shop.online_shop.adapter;

import online_shop.online_shop.domain.Cart;
import online_shop.online_shop.domain.CartItem;
import online_shop.online_shop.dto.CartDto;
import online_shop.online_shop.dto.CartItemDto;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter {
    public static Cart getCartFromCartDto(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setUser(cartDto.getUser());
        cart.setTotalPrice(cartDto.getTotalPrice());
        return cart;
    }

    public static CartDto getCartDtofromCart(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setUser(cart.getUser());
        cartDto.setTotalPrice(cart.getTotalPrice());
        return cartDto;
    }

    public static List<CartDto> getCartDtoListFromCartList(List<Cart> cartList) {
        List<CartDto> cartDtoList = new ArrayList<>();
        for (Cart cart : cartList) {
            CartDto cartDto = getCartDtofromCart(cart);
            cartDtoList.add(cartDto);
        }
        return cartDtoList;
    }

    public static List<CartItemDto> getCartListFromCartDtoList(List<CartDto> cartDtoList) {
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        for (CartDto cartDto : cartDtoList) {
            CartItemDto cartItemDto = new CartItemDto();
        }
        return cartItemDtoList;
    }
}
