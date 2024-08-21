package com.example.demo_re_do_homework.dto.dtoOrder.dtoOrderResponse;

import com.example.demo_re_do_homework.dto.dtoProduct.dtoProductResponse.DTOProductResponse;
import com.example.demo_re_do_homework.entity.Order;
import com.example.demo_re_do_homework.entity.ProductOrder;
import com.example.demo_re_do_homework.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DTOOrderProductResponse {
    private Long orderId;
    private LocalDate orderDate;
    private Float totalAmount;
    private Status status;
    private List<DTOProductResponse> productList = new ArrayList<>();

    public void responseOrderProductTODTO(Order order){
        this.orderId = order.getOrderId();
        this.orderDate = order.getOrderDate();
        this.totalAmount = order.getTotalAmount();
        this.status = order.getStatus();
        // Loop through each ProductOrder in the Order
        for (ProductOrder productOrder : order.getProductOrder()){
            // Create a new DTOProductResponse for each ProductOrder
            DTOProductResponse dtoProductResponse = new DTOProductResponse();
            dtoProductResponse.setProductId(productOrder.getProduct().getProductId());
            dtoProductResponse.setProductName(productOrder.getProduct().getProductName());
            dtoProductResponse.setUnitPrice(productOrder.getProduct().getUnitPrice());
            dtoProductResponse.setDescription(productOrder.getProduct().getDescription());
            // Add the DTOProductResponse to the productList
            this.productList.add(dtoProductResponse);
        }
    }


}
