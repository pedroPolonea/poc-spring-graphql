package com.sb.pb.dto;

import java.util.Objects;

public class ProductDto {
    private String name;
    private String description;
    private int amount;
    private boolean active;
    private ProductTypeDto productTypeDto;

    public ProductDto() {
    }

    public ProductDto(String name, String description, int amount, boolean active, ProductTypeDto productTypeDto) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.active = active;
        this.productTypeDto = productTypeDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ProductTypeDto getProductType() {
        return productTypeDto;
    }

    public void setProductType(ProductTypeDto productTypeDto) {
        this.productTypeDto = productTypeDto;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return amount == that.amount && active == that.active && Objects.equals(name, that.name) && Objects.equals(description, that.description) && productTypeDto == that.productTypeDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, amount, active, productTypeDto);
    }
}
