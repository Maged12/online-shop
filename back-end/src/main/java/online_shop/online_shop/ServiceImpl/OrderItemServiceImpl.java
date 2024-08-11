package online_shop.online_shop.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import online_shop.online_shop.adapter.OrderItemAdapter;
import online_shop.online_shop.domain.Order;
import online_shop.online_shop.domain.OrderItem;
import online_shop.online_shop.dto.OrderItemDto;
import online_shop.online_shop.dto.response.OrderItemResponseDto;
import online_shop.online_shop.repository.CategoryRepository;
import online_shop.online_shop.repository.OrderItemRepository;
import online_shop.online_shop.repository.OrderRepository;
import online_shop.online_shop.repository.ProductRepository;
import online_shop.online_shop.service.OrderItemService;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<OrderItemResponseDto> getAllOrderItems(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderItemAdapter.getOrderItemResponseDtoListFromOrderItemList(order.getOrderItems());
    }

    @Override
    public OrderItemDto getOrderItemById(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));
        return OrderItemAdapter.getOrderItemDtoFromOrderItem(orderItem);
    }

    // @Override
    // public void addOrderItemToOrder(Long orderId, OrderItemDto orderItemDto) {
    // Order order = orderRepository.findById(orderId)
    // .orElseThrow(() -> new RuntimeException("Order not found"));

    // // Set the order item's price based on the product's price
    // double price = product.getPrice();

    // OrderItem orderItem = new OrderItem();
    // orderItem.setOrder(order);
    // orderItem.setProduct(product);
    // orderItem.setQuantity(orderItemDto.getQuantity());
    // orderItem.setPrice(price); // Set price directly from product

    // orderItemRepository.save(orderItem);

    // // Update order total amount if needed
    // double totalAmount = order.getTotalAmount() + (price *
    // orderItem.getQuantity());
    // order.setTotalAmount(totalAmount);
    // orderRepository.save(order);
    // }

    @Override
    public void deleteOrderItem(Long orderId, Long orderItemId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));

        // Adjust the order total amount
        double newTotalAmount = order.getTotalAmount() - orderItem.getPrice() * orderItem.getQuantity();
        order.setTotalAmount(newTotalAmount);

        // Remove the order item from the order's item list
        order.getOrderItems().remove(orderItem);
        // Save the order first to persist the changes
        orderRepository.save(order);

        orderItemRepository.delete(orderItem);

    }

}