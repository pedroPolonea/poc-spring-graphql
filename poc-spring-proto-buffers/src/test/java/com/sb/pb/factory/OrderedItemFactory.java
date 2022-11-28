package com.sb.pb.factory;

import com.sb.pb.proto.OrderedItem;

public final class OrderedItemFactory {

    public static OrderedItem createOrderedItem(final int amount){
        return OrderedItem.newBuilder()
                .setAmount(amount)
                .setIdSalesOrder(1)
                .build();
    }
}
