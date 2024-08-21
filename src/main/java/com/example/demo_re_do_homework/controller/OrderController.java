package com.example.demo_re_do_homework.controller;

import com.example.demo_re_do_homework.apiResponse.APIResponse;
import com.example.demo_re_do_homework.dto.dtoOrder.dtoOrderRequest.DTOOrderRequest;
import com.example.demo_re_do_homework.dto.dtoOrder.dtoOrderResponse.DTOOrderProductResponse;
import com.example.demo_re_do_homework.entity.enums.Status;
import com.example.demo_re_do_homework.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/order-product")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("saveOrder/{customerId}")
    public ResponseEntity<APIResponse<Object>> saveOrder(
            @RequestBody List<DTOOrderRequest> dtoOrderRequest,
            @PathVariable Long customerId){
        DTOOrderProductResponse createdOrder = orderService.saveOrder(dtoOrderRequest, customerId);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("A new order is created successfully.")
                .payload(createdOrder)
                .status(HttpStatus.CREATED)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<APIResponse<Object>> getOrderById(@PathVariable Long id){
        DTOOrderProductResponse getOrderProductById = orderService.getOrderById(id);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get order id "+ id +" successfully.")
                .payload(getOrderProductById)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getOrderByCustomerId/{customerId}")
    public ResponseEntity<APIResponse<Object>> getOrderByCustomerId(@PathVariable Long customerId) {
        DTOOrderProductResponse getOrderByCustomer = orderService.getOrderByCustomerId(customerId);
        if (Objects.isNull(getOrderByCustomer)) {
            APIResponse<Object> apiResponse = APIResponse.builder()
                    .message("Order not found for Customer ID " + customerId)
                    .payload(null)
                    .status(HttpStatus.NOT_FOUND)
                    .localDateTime(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get Order By Customer ID " + customerId + " successful.")
                .payload(getOrderByCustomer)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/updateOrder/status")
    public ResponseEntity<APIResponse<Object>> updateOrderStatus(
            @RequestParam Status status,
            @RequestParam Long orderId
            ){
        DTOOrderProductResponse updatedOderStatus = orderService.updateOrderStatus(status,orderId);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Successfully updated the status of order to "+status)
                .payload(updatedOderStatus)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
