package online_shop.online_shop.dto.request;

import java.util.List;

import online_shop.online_shop.dto.response.ProductResponseDto;

public record CategoryRequestDto(String name, String description, List<ProductResponseDto> productDtos) {
}
