package online_shop.online_shop.dto;

public record OrderItemResponseDto(Long id, int quantity, double price, ProductResponseDto product) {

}
