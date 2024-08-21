package com.example.demo_re_do_homework.dto.dtoProduct.dtoProductResponse;

import com.example.demo_re_do_homework.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DTOProductResponse {
    private Long productId;
    private String productName;
    private Float unitPrice;
    private String description;
    public void dtoResponseProduct(Product product){
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.unitPrice = product.getUnitPrice();
        this.description = product.getDescription();
    }
}
