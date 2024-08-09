package online_shop.online_shop.controller;

import online_shop.online_shop.adapter.OrderAdapter;
import online_shop.online_shop.domain.Order;
import online_shop.online_shop.domain.User;
import online_shop.online_shop.dto.OrderDto;
import online_shop.online_shop.dto.OrderItemDto;
import online_shop.online_shop.repository.OrderRepository;
import online_shop.online_shop.repository.UserRepository;
import online_shop.online_shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        OrderDto orderDTO = orderService.getOrderById(id);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }


    @PostMapping
    public void createOrder(@RequestBody OrderDto orderDTO) {
        Order order = OrderAdapter.getOrderFromOrderDto(orderDTO);
        if (order.getUser() != null && order.getUser().getId() != null) {
            // Fetch existing user from the database
            User user = userRepository.findById(order.getUser().getId()).orElseThrow(() -> new RuntimeException("User not found"));
            order.setUser(user);
        } else {
            // Save new user if needed
            User savedUser = userRepository.save(order.getUser());
            order.setUser(savedUser);
        }
        orderRepository.save(order);
    }

    @PutMapping("/{orderId}/items/{orderItemId}")
    public ResponseEntity<?> updateOrderItem(@PathVariable Long orderId, @PathVariable Long orderItemId, @RequestBody OrderItemDto orderItemDto) {
        orderService.updateOrderItem(orderId, orderItemId, orderItemDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDTO) {
        orderService.updateOrder(id, orderDTO);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
