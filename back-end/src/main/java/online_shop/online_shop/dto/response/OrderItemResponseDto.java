package online_shop.online_shop.dto.response;

public record OrderItemResponseDto(Long id, int quantity, double price, ProductResponseDto product) {

}
