package online_shop.online_shop.adapter;

import online_shop.online_shop.domain.OrderItem;
import online_shop.online_shop.dto.OrderItemDto;

import java.util.List;
import java.util.stream.Collectors;


public class OrderItemAdapter {
    public static OrderItemDto getOrderItemDtoFromOrderItem(OrderItem orderItem) {
        if (orderItem == null) return null;

        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setProductDto(ProductAdapter.getProductDtoFromProduct(orderItem.getProduct()));
        return orderItemDto;
    }

    public static OrderItem getOrderItemFromOrderItemDto(OrderItemDto orderItemDto) {
        if (orderItemDto == null) return null;

        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setProduct(ProductAdapter.getProductFromProductDto(orderItemDto.getProductDto()));
        return orderItem;
    }

    public static List<OrderItemDto> getOrderItemDtoListFromOrderItemList(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(OrderItemAdapter::getOrderItemDtoFromOrderItem)
                .collect(Collectors.toList());
    }
}
