package online_shop.online_shop.dto;

import java.util.List;

import online_shop.online_shop.dto.response.CategoryProductResponseDto;

public record CategoryResponseDto(String name, String description, List<CategoryProductResponseDto> productDtos) {
}
