package com.example.demo_re_do_homework.controller;

import com.example.demo_re_do_homework.apiResponse.APIDeResponse;
import com.example.demo_re_do_homework.apiResponse.APIResponse;
import com.example.demo_re_do_homework.dto.dtoProduct.dtoProductRequest.DTOProductRequest;
import com.example.demo_re_do_homework.dto.dtoProduct.dtoProductResponse.DTOProductResponse;
import com.example.demo_re_do_homework.entity.Product;
import com.example.demo_re_do_homework.service.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/saveProduct")
    public ResponseEntity<APIResponse<Object>> saveProduct(@RequestBody DTOProductRequest dtoProductRequest){
        DTOProductResponse savedProductResponse = productService.saveProduct(dtoProductRequest);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Created product successful")
                .payload(savedProductResponse)
                .status(HttpStatus.CREATED)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<APIResponse<Object>> getAllProduct(
            @RequestParam(defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(defaultValue = "productId", required = false) String sortBy,
            @RequestParam(defaultValue = "DESC", required = false) Sort.Direction sortDirection
    ){
        List<DTOProductResponse> getProducts = productService.getAllProduct(pageNo, pageSize, sortBy, sortDirection);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get all products successfully.")
                .payload(getProducts)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }



    @GetMapping("/getProductById/{id}")
    public ResponseEntity<APIResponse<Object>> getProductById(@PathVariable Long id){
        DTOProductResponse dtoProductResponse = productService.getProductById(id);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get product by id "+ id +" successful .")
                .payload(dtoProductResponse)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<APIDeResponse<Object>> deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
        APIDeResponse<Object> apiDeResponse = APIDeResponse.builder()
                .message("Deleted product with id "+ id +" successful .")
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiDeResponse);
    }
    @PutMapping("/updateProductById/{id}")
    public ResponseEntity<APIResponse<Object>> updateProductById(
            @RequestBody DTOProductRequest dtoProductRequest,
            @PathVariable Long id){
        DTOProductResponse dtoProductResponse = productService.updateProductById(dtoProductRequest,id);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Updated product with id "+ id +" successful .")
                .payload(dtoProductResponse)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
