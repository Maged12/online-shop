// package online_shop.online_shop.ServiceImpl;

// import jakarta.transaction.Transactional;
// import online_shop.online_shop.adapter.CartItemAdapter;
// import online_shop.online_shop.domain.Cart;
// import online_shop.online_shop.domain.CartItem;
// import online_shop.online_shop.dto.CartItemDto;
// import online_shop.online_shop.repository.CartItemRepository;
// import online_shop.online_shop.repository.CartRepository;
// import online_shop.online_shop.service.CartItemService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.stream.Collectors;

// @Service
// @Transactional
// public class CartItemServiceImpl implements CartItemService {

// @Autowired
// private CartItemRepository cartItemRepository;
// @Autowired
// private CartRepository cartRepository;

// @Override
// public CartItemDto getCartItemById(Long id) {
// CartItem cartItem = cartItemRepository.findById(id).orElse(null);
// return CartItemAdapter.getCartItemDtoFromCartItem(cartItem);
// }

// @Override
// public void updateCartItem(Long cartId, Long itemId, CartItemDto cartItemDTO)
// {
// CartItem cartItem = cartItemRepository.findById(itemId).orElse(null);
// if (cartItem != null && cartItem.getCart().getId().equals(cartId)) {
// cartItem.setQuantity(cartItemDTO.getQuantity());
// cartItem.setPrice(cartItemDTO.getPrice());
// cartItemRepository.save(cartItem);
// } else {
// throw new RuntimeException("CartItem or Cart not found");
// }
// }

// @Override
// public void deleteCartItem(Long cartId, Long itemId) {
// CartItem cartItem = cartItemRepository.findById(itemId).orElse(null);
// if (cartItem != null && cartItem.getCart().getId().equals(cartId)) {
// Cart cart = cartItem.getCart();
// double newTotalPrice = cart.getTotalPrice() - (cartItem.getPrice() *
// cartItem.getQuantity());
// cart.setTotalPrice(newTotalPrice);
// cartItemRepository.deleteById(itemId);
// cartRepository.save(cart); // Save the cart to update the total price
// } else {
// throw new RuntimeException("CartItem or Cart not found");
// }
// }

// @Override
// public List<CartItemDto> getAllCartItemsByCartId(Long cartId) {
// List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
// return cartItems.stream()
// .map(CartItemAdapter::getCartItemDtoFromCartItem)
// .collect(Collectors.toList());
// }

// }
