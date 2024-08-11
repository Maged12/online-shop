// package online_shop.online_shop.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import online_shop.online_shop.dto.CartDto;
// import online_shop.online_shop.dto.CartItemDto;
// import online_shop.online_shop.service.CartService;

// @RestController
// @RequestMapping("/api/carts")
// public class CartController {
// @Autowired
// private CartService cartService;

// @GetMapping
// public ResponseEntity<?> getAllCarts() {
// List<CartDto> carts = cartService.getAllCarts();
// return new ResponseEntity<>(carts, HttpStatus.OK);
// }

// @GetMapping("/{cartId}")
// public ResponseEntity<?> getCart(@PathVariable Long cartId) {
// CartDto cartDTO = cartService.getCartById(cartId);
// return new ResponseEntity<>(cartDTO, HttpStatus.OK);
// }

// @PostMapping("/{cartId}/items")
// public ResponseEntity<String> addItemToCart(@PathVariable Long cartId,
// @RequestBody CartItemDto cartItemDto) {
// try {
// cartService.addItemToCart(cartId, cartItemDto);
// return ResponseEntity.ok().build();
// } catch (RuntimeException e) {
// return
// ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
// }
// }

// @DeleteMapping("/{cartId}/items/{itemId}")
// public ResponseEntity<String> removeItemFromCart(@PathVariable Long cartId,
// @PathVariable Long itemId) {
// try {
// cartService.removeItemFromCart(cartId, itemId);
// return ResponseEntity.ok().build();
// } catch (RuntimeException e) {
// return
// ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
// }
// }

// @DeleteMapping("/{cartId}")
// public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
// try {
// cartService.clearCart(cartId);
// return ResponseEntity.ok().build();
// } catch (RuntimeException e) {
// return
// ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
// }
// }

// }