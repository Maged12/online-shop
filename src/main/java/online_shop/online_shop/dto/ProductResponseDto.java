package online_shop.online_shop.dto;

public class ProductResponseDto {
    private String name;
    private String description;
    private double price;
    private CategoryDto categoryDto;
    private String imageUrl;

    public ProductResponseDto() {
    }

    public ProductResponseDto(String name, String description, double price, CategoryDto categoryDto, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryDto = categoryDto;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    public String getImage() {
        return imageUrl;
    }

    public void setImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
