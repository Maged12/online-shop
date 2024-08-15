package online_shop.online_shop.dto;

import java.util.ArrayList;
import java.util.List;

import online_shop.online_shop.domain.Address;
import online_shop.online_shop.domain.Role;
import online_shop.online_shop.dto.request.OrderRequestDto;

public class UserDto {
    private String name;
    private String email;
    private String password;
    private List<OrderRequestDto> orderDtos = new ArrayList<OrderRequestDto>();
    private Role role;
    private Address address;

    public UserDto() {
    }

    public UserDto(String name, String email, String password, Role role, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;

    }

    public void addOrderDto(OrderRequestDto orderDto) {
        orderDtos.add(orderDto);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OrderRequestDto> getOrderDtos() {
        return orderDtos;
    }

    public void setOrderDtos(List<OrderRequestDto> orderDtos) {
        this.orderDtos = orderDtos;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
}
