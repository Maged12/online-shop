package online_shop.online_shop.dto;

public class CartItemDto {
    private CartDto cartDto;
    private ProductDto productDto;

    private int quantity;
    private double price;

    public CartItemDto() {
    }
    public CartItemDto(CartDto cartDto, ProductDto productDto) {
        this.cartDto = cartDto;
        this.productDto = productDto;
    }

    public CartDto getCartDto() {
        return cartDto;
    }

    public void setCartDto(CartDto cartDto) {
        this.cartDto = cartDto;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
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