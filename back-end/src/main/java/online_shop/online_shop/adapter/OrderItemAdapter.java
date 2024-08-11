package online_shop.online_shop.adapter;

import java.util.List;
import java.util.stream.Collectors;

import online_shop.online_shop.domain.OrderItem;
import online_shop.online_shop.dto.OrderItemDto;
import online_shop.online_shop.dto.response.OrderItemResponseDto;

public class OrderItemAdapter {
    public static OrderItemDto getOrderItemDtoFromOrderItem(OrderItem orderItem) {
        if (orderItem == null)
            return null;
        var orderItemDTO = new OrderItemDto(
                orderItem.getQuantity(),
                orderItem.getPrice(),

                orderItem.getId(),
                orderItem.getOrder().getId());
        return orderItemDTO;
    }

    public static List<OrderItemDto> getOrderItemDtoListFromOrderItemList(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(OrderItemAdapter::getOrderItemDtoFromOrderItem)
                .collect(Collectors.toList());
    }

    public static OrderItemResponseDto getOrderItemResponseDtoFromOrderItem(
            OrderItem orderItem) {
        return new OrderItemResponseDto(orderItem.getId(), orderItem.getQuantity(),
                orderItem.getPrice(), ProductAdapter.getProductDtoFromProduct(orderItem.getProduct()));
    }

    public static List<OrderItemResponseDto> getOrderItemResponseDtoListFromOrderItemList(
            List<OrderItem> orderItemList) {
        return orderItemList.stream().map(OrderItemAdapter::getOrderItemResponseDtoFromOrderItem).toList();
    }

}
