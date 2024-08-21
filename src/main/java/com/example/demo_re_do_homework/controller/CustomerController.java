package com.example.demo_re_do_homework.controller;

import com.example.demo_re_do_homework.apiResponse.APIDeResponse;
import com.example.demo_re_do_homework.apiResponse.APIResponse;
import com.example.demo_re_do_homework.dto.dtoCustomer.dtoCustomerRequest.DTOCustomerRequest;
import com.example.demo_re_do_homework.dto.dtoCustomer.dtoCustomerResponse.DTOCustomerResponse;
import com.example.demo_re_do_homework.dto.dtoCustomer.dtoCustomerResponse.DTOSaveCustomerResponse;
import com.example.demo_re_do_homework.entity.Customer;
import com.example.demo_re_do_homework.service.CustomerService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/saveCustomer")
    public ResponseEntity<APIResponse<Object>> saveCustomer(@RequestBody DTOCustomerRequest dtoCustomerRequest){
        DTOSaveCustomerResponse savedCustomer = customerService.saveCustomer(dtoCustomerRequest);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("A new customer is inserted successfully.")
                .payload(savedCustomer)
                .status(HttpStatus.CREATED)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<APIResponse<Object>> getAllCustomer(
            @RequestParam(defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(defaultValue = "customerId", required = false) String sortBy,
            @RequestParam(defaultValue = "DESC", required = false) Sort.Direction sortDirection
    ){
        List<DTOCustomerResponse> getCustomers = customerService.getAllCustomer(pageNo,pageSize,sortBy,sortDirection);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get all customer successful .")
                .payload(getCustomers)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<APIResponse<Object>> getCustomerById(@PathVariable Long id){
        DTOSaveCustomerResponse getCustomer = customerService.getCustomerById(id);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get customer id "+ id +" successfully.")
                .payload(getCustomer)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/deleteCustomerById/{id}")
    public ResponseEntity<APIDeResponse<Object>> deleteCustomerById(@PathVariable Long id){
        customerService.deleteCustomerById(id);
        APIDeResponse<Object> apiResponse = APIDeResponse.builder()
                .message("Deleted customer successful with id "+ id)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/updateCustomerById/{id}")
    public ResponseEntity<APIResponse<Object>> updateCustomerById(
            @RequestBody DTOCustomerRequest dtoCustomerRequest,
            @PathVariable Long id
    ){
        DTOSaveCustomerResponse upCustomer = customerService.updateCustomerById(dtoCustomerRequest,id);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Customer id "+ id +" is updated successfully")
                .payload(upCustomer)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }



}
