package online_shop.online_shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import online_shop.online_shop.dto.response.ProductResponseDto;

@JsonIgnoreProperties({ "cartDto" })
public class CartItemDto {
    private CartDto cartDto;
    private ProductResponseDto productDto;

    private int quantity;
    private double price;

    public CartItemDto() {
    }

    public CartItemDto(CartDto cartDto, ProductResponseDto productDto) {
        this.cartDto = cartDto;
        this.productDto = productDto;
    }

    public CartDto getCartDto() {
        return cartDto;
    }

    public void setCartDto(CartDto cartDto) {
        this.cartDto = cartDto;
    }

    public ProductResponseDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductResponseDto productDto) {
        this.productDto = productDto;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}