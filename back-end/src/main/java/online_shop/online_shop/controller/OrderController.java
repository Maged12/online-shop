package online_shop.online_shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online_shop.online_shop.dto.request.OrderRequestDto;
import online_shop.online_shop.dto.response.OrderResponseDto;
import online_shop.online_shop.repository.OrderRepository;
import online_shop.online_shop.repository.UserRepository;
import online_shop.online_shop.service.OrderService;

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
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long id) {
        OrderResponseDto orderDTO = orderService.getOrderById(id);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto orderDto) {

        if (orderDto.userId() == null || orderDto.totalAmount() <= 0 || orderDto.orderItem() == null
                || orderDto.orderItem().isEmpty())
            return new ResponseEntity<>(Map.of(
                    "message", "Order status updated successfully"), HttpStatus.BAD_REQUEST);

        var order = orderService.createOrder(orderDto);
        return new ResponseEntity<>(Map.of("order_id", order.id(), "message", "Order created successfully"),
                HttpStatus.CREATED);

    }

    // @PutMapping("/{orderId}/items/{orderItemId}")
    // public ResponseEntity<?> updateOrderItem(@PathVariable Long orderId,
    // @PathVariable Long orderItemId,
    // @RequestBody OrderItemDto orderItemDto) {
    // orderService.updateOrderItem(orderId, orderItemId, orderItemDto);
    // return new ResponseEntity<>(HttpStatus.OK);
    // }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long id, @RequestBody String status) {
        var orderResponse = orderService.updateOrderStatus(id, status);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
