package online_shop.online_shop.adapter;

import online_shop.online_shop.domain.OrderItem;
import online_shop.online_shop.dto.OrderItemDto;

import java.util.ArrayList;
import java.util.List;

import static online_shop.online_shop.adapter.OrderAdapter.getOrderDtoFromOrder;
import static online_shop.online_shop.adapter.OrderAdapter.getOrderFromOrderDto;
import static online_shop.online_shop.adapter.ProductAdapter.getProductDtoFromProduct;
import static online_shop.online_shop.adapter.ProductAdapter.getProductFromProductDto;

public class OrderItemAdapter {
    public static OrderItem getOrderItemFromOrderItemDto(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(getOrderFromOrderDto(orderItemDto.getOrderDto()));
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setProduct(getProductFromProductDto(orderItemDto.getProductDto()));
        return orderItem;
    }

    public static OrderItemDto getOrderItemDtoFromOrderItem(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProductDto(getProductDtoFromProduct(orderItem.getProduct()));
        orderItemDto.setOrderDto(getOrderDtoFromOrder(orderItem.getOrder()));
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPrice(orderItem.getPrice());
        return orderItemDto;
    }

    public static List<OrderItem> getOrderItemListFromOrderItemDtoList(List<OrderItemDto> orderItemDtoList) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderItemDtoList) {
            orderItemList.add(getOrderItemFromOrderItemDto(orderItemDto));
        }
        return orderItemList;
    }

    public static List<OrderItemDto> getOrderItemDtoListFromOrderItemList(List<OrderItem> orderItemList) {
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        for (OrderItem orderItem : orderItemList) {
            orderItemDtoList.add(getOrderItemDtoFromOrderItem(orderItem));
        }
        return orderItemDtoList;
    }
}
