package com.example.demo_re_do_homework.dto.dtoProduct.dtoProductRequest;

import com.example.demo_re_do_homework.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DTOProductRequest {
    private String productName;
    private Float unitPrice;
    private String description;
    public void requestProductToDTO(Product product){
        product.setProductName(this.productName);
        product.setUnitPrice(this.unitPrice);
        product.setDescription(this.description);
    }
}
