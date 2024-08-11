package online_shop.online_shop.dto.response;

public record ProductResponseDto(Long id, String name, String description, double price,
        ProductCategoryResponseDto categoryDto,
        String imageUrl) {

}
