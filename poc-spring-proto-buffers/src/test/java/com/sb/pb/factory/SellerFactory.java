package com.sb.pb.factory;

import com.sb.pb.proto.Seller;

public final class SellerFactory {

    public static Seller createSeller(){
        return Seller.newBuilder()
                .setName("Polo")
                .build();
    }
}
