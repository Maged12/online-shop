package online_shop.online_shop.dto;

import org.springframework.web.multipart.MultipartFile;

public record ProductRequestDto(String name, String description, double price, String categoryId,
                MultipartFile image) {
}
