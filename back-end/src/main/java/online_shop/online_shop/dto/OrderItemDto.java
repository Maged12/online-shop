package online_shop.online_shop.dto;

public record OrderItemDto(
        int quantity,
        double price, Long productId,
        Long orderId) {

}
