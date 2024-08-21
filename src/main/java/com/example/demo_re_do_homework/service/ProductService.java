package com.example.demo_re_do_homework.service;

import com.example.demo_re_do_homework.dto.dtoProduct.dtoProductRequest.DTOProductRequest;
import com.example.demo_re_do_homework.dto.dtoProduct.dtoProductResponse.DTOProductResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {
    DTOProductResponse saveProduct(DTOProductRequest dtoProductRequest);

    DTOProductResponse getProductById(Long id);

    void deleteProductById(Long id);

    DTOProductResponse updateProductById(DTOProductRequest dtoProductRequest, Long id);

    List<DTOProductResponse> getAllProduct(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction sortDirection);
}
