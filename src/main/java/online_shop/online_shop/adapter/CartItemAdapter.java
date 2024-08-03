package online_shop.online_shop.adapter;

import online_shop.online_shop.domain.CartItem;
import online_shop.online_shop.domain.Product;
import online_shop.online_shop.dto.CartItemDto;
import online_shop.online_shop.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

import static online_shop.online_shop.adapter.CartAdapter.getCartDtofromCart;
import static online_shop.online_shop.adapter.CartAdapter.getCartFromCartDto;
import static online_shop.online_shop.adapter.ProductAdapter.getProductDtoFromProduct;
import static online_shop.online_shop.adapter.ProductAdapter.getProductFromProductDto;

public class CartItemAdapter {
    public static CartItem getCartItemFromCartItemDto(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(getCartFromCartDto(cartItemDto.getCartDto()));
        cartItem.setProduct(getProductFromProductDto(cartItemDto.getProductDto()));
        cartItem.setPrice(cartItemDto.getPrice());
        cartItem.setQuantity(cartItemDto.getQuantity());
        return cartItem;
    }

    public static CartItemDto getCartItemDtoFromCartItem(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setCartDto(getCartDtofromCart(cartItem.getCart()));
        cartItemDto.setProductDto(getProductDtoFromProduct(cartItem.getProduct()));
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setQuantity(cartItem.getQuantity());
        return cartItemDto;
    }

    public static List<CartItem> getCartItemListFromCartItemDtoList(List<CartItemDto> cartItemDtoList ){
        List<CartItem> cartItemList = new ArrayList<>();
        for (CartItemDto cartItemDto : cartItemDtoList) {
            CartItem cartItem = getCartItemFromCartItemDto(cartItemDto);
            cartItemList.add(cartItem);
        }
        return cartItemList;
    }

    public static List<CartItemDto> getCartItemDtoListFromCartItemList(List<CartItem> cartItemList) {
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            CartItemDto cartItemDto = getCartItemDtoFromCartItem(cartItem);
            cartItemDtoList.add(cartItemDto);
        }
        return cartItemDtoList;
    }
}
