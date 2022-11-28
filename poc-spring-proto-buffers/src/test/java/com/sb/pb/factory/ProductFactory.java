package com.sb.pb.factory;

import com.sb.pb.dto.ProductDto;
import com.sb.pb.dto.ProductTypeDto;
import com.sb.pb.proto.Product;
import com.sb.pb.proto.ProductType;

public final class ProductFactory {
    public static ProductDto createBaseProductDTO(){
        ProductDto productDto = new ProductDto();
        productDto.setName("TV");
        productDto.setDescription("TV 42 Sony");
        productDto.setAmount(10);
        productDto.setActive(true);
        productDto.setProductType(ProductTypeDto.ELECTRONICS);

        return productDto;
    }

    public static Product createProductProto(){
        return Product.newBuilder()
                .setName("TV")
                .setDescription("TV 42 Sony")
                .setAmount(10)
                .setActive(true)
                .setProductType(ProductType.ELECTRONICS)
                .build();
    }
}
