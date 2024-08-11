// package online_shop.online_shop.controller;

// import online_shop.online_shop.dto.CartItemDto;
// import online_shop.online_shop.service.CartItemService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/cartItems")
// public class CartItemController {
// @Autowired
// private CartItemService cartItemService;

// @GetMapping("/{id}")
// public ResponseEntity<?> getCartItem(@PathVariable Long id) {
// CartItemDto cartItemDTO = cartItemService.getCartItemById(id);
// return new ResponseEntity<>(cartItemDTO, HttpStatus.OK);
// }

// @GetMapping("/cart/{cartId}")
// public ResponseEntity<List<CartItemDto>>
// getAllCartItemsByCartId(@PathVariable Long cartId) {
// List<CartItemDto> cartItemDtos =
// cartItemService.getAllCartItemsByCartId(cartId);
// return new ResponseEntity<>(cartItemDtos, HttpStatus.OK);
// }

// @PutMapping("/{cartId}/{itemId}")
// public ResponseEntity<?> updateCartItem(@PathVariable Long cartId,
// @PathVariable Long itemId, @RequestBody CartItemDto cartItemDto) {
// cartItemService.updateCartItem(cartId, itemId, cartItemDto);
// return new ResponseEntity<>(cartItemDto, HttpStatus.OK);
// }

// @DeleteMapping("/{cartId}/{itemId}")
// public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartId,
// @PathVariable Long itemId) {
// cartItemService.deleteCartItem(cartId, itemId);
// return new ResponseEntity<>(HttpStatus.NO_CONTENT);
// }
// }