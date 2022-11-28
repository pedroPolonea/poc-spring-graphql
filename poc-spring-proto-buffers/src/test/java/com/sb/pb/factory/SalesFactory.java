package com.sb.pb.factory;

import com.sb.pb.proto.SalesOrder;

public final class SalesFactory {

    public static SalesOrder createSalesOrder(){
        return SalesOrder.newBuilder()
                .setNumOrder(1)
                .setSeller(SellerFactory.createSeller())
                .addOrderedItem(OrderedItemFactory.createOrderedItem(10))
                .addOrderedItem(OrderedItemFactory.createOrderedItem(15))
                .build();
    }
}
