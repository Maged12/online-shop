package online_shop.online_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import online_shop.online_shop.dto.response.OrderItemResponseDto;
import online_shop.online_shop.service.OrderItemService;

@RestController
@RequestMapping("/api/orderItems")

public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<OrderItemResponseDto>> getAllOrderItems(@PathVariable Long orderId) {
        List<OrderItemResponseDto> orderItems = orderItemService.getAllOrderItems(orderId);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    // @GetMapping("/{orderId}/items/{orderItemId}")
    // public ResponseEntity<OrderItemDto> getOrderItem( @PathVariable Long
    // orderItemId) {
    // OrderItemDto orderItemDto = orderItemService.getOrderItemById( orderItemId);
    // return new ResponseEntity<>(orderItemDto, HttpStatus.OK);
    // }

    // @PostMapping("/{orderId}/items")
    // public ResponseEntity<?> addOrderItemToOrder(@PathVariable Long orderId,
    // @RequestBody OrderItemDto orderItemDto) {
    // orderItemService.addOrderItemToOrder(orderId, orderItemDto);
    // return new ResponseEntity<>(orderItemDto, HttpStatus.CREATED);
    // }

    @DeleteMapping("/{orderId}/items/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderId, @PathVariable Long orderItemId) {
        orderItemService.deleteOrderItem(orderId, orderItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
