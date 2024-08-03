package online_shop.online_shop.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {
    private String name;
    private String description;
    private List<ProductDto> productDtos = new ArrayList<ProductDto>();

    public CategoryDto() {}
    public CategoryDto(String name, String description) {}
    public void addProductDto(ProductDto productDto) {
        productDtos.add(productDto);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }
}
