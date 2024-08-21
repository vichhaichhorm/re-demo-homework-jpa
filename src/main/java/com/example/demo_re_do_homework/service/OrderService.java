package com.example.demo_re_do_homework.service;

import com.example.demo_re_do_homework.dto.dtoOrder.dtoOrderRequest.DTOOrderRequest;
import com.example.demo_re_do_homework.dto.dtoOrder.dtoOrderResponse.DTOOrderProductResponse;
import com.example.demo_re_do_homework.entity.enums.Status;

import java.util.List;

public interface OrderService {
    DTOOrderProductResponse saveOrder(List<DTOOrderRequest> dtoOrderRequest, Long customerId);
    DTOOrderProductResponse getOrderById(Long id);
    DTOOrderProductResponse getOrderByCustomerId(Long customerId);
    DTOOrderProductResponse updateOrderStatus(Status status, Long orderId);

}
