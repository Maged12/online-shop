// package online_shop.online_shop.adapter;

// import online_shop.online_shop.domain.Cart;
// import online_shop.online_shop.domain.CartItem;
// import online_shop.online_shop.dto.CartDto;
// import online_shop.online_shop.dto.CartItemDto;

// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import static online_shop.online_shop.adapter.UserAdapter.getUserDtoFromUser;
// import static online_shop.online_shop.adapter.UserAdapter.getUserFromUserDto;

// public class CartAdapter {
// public static Cart getCartFromCartDto(CartDto cartDto) {
// Cart cart = new Cart();
// cart.setUser(getUserFromUserDto(cartDto.getUserDto()));
// cart.setTotalPrice(cartDto.getTotalPrice());
// List<CartItem> cartItems = Optional.ofNullable(cartDto.getItems())
// .orElse(Collections.emptyList())
// .stream()
// .map(CartItemAdapter::getCartItemFromCartItemDto)
// .collect(Collectors.toList());
// cartItems.forEach(cartItem -> cartItem.setCart(cart));
// cart.setItems(cartItems);
// return cart;
// }

// public static CartDto getCartDtoFromCart(Cart cart){
// CartDto cartDto = new CartDto();
// cartDto.setUserDto(getUserDtoFromUser(cart.getUser()));
// cartDto.setTotalPrice(cart.getTotalPrice());
// List<CartItemDto> cartItems = Optional.ofNullable(cart.getItems())
// .orElse(Collections.emptyList())
// .stream()
// .map(CartItemAdapter::getCartItemDtoFromCartItem)
// .collect(Collectors.toList());
// cartItems.forEach(cartItem -> cartItem.setCartDto(cartDto));
// cartDto.setItems(cartItems);
// return cartDto;
// }

// public static List<CartDto> getCartDtoListFromCartList(List<Cart> cartList) {
// List<CartDto> cartDtoList = new ArrayList<>();
// for (Cart cart : cartList) {
// CartDto cartDto = getCartDtoFromCart(cart);
// cartDtoList.add(cartDto);
// }
// return cartDtoList;
// }

// public static List<CartItemDto> getCartListFromCartDtoList(List<CartDto>
// cartDtoList) {
// List<CartItemDto> cartItemDtoList = new ArrayList<>();
// for (CartDto cartDto : cartDtoList) {
// CartItemDto cartItemDto = new CartItemDto();
// }
// return cartItemDtoList;
// }
// }
