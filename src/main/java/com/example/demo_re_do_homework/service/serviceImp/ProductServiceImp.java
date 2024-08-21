package com.example.demo_re_do_homework.service.serviceImp;

import com.example.demo_re_do_homework.dto.dtoProduct.dtoProductRequest.DTOProductRequest;
import com.example.demo_re_do_homework.dto.dtoProduct.dtoProductResponse.DTOProductResponse;
import com.example.demo_re_do_homework.entity.Product;
import com.example.demo_re_do_homework.repository.ProductRepository;
import com.example.demo_re_do_homework.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public DTOProductResponse saveProduct(DTOProductRequest dtoProductRequest) {
        Product product = new Product();
        dtoProductRequest.requestProductToDTO(product);
        Product savedProduct = productRepository.save(product);
        DTOProductResponse dtoProductResponse = new DTOProductResponse();
        dtoProductResponse.dtoResponseProduct(savedProduct);
        return dtoProductResponse;
    }

    @Override
    public DTOProductResponse getProductById(Long id) {
        Product findProduct = productRepository.findById(id).orElse(null);
        if (!Objects.isNull(findProduct)){
            DTOProductResponse dtoProductResponse = new DTOProductResponse();
            dtoProductResponse.dtoResponseProduct(findProduct);
            return dtoProductResponse;
        }
        return null;
    }

    @Override
    public void deleteProductById(Long id) {
        Product findProduct = productRepository.findById(id).orElse(null);
        if (!Objects.isNull(findProduct)){
            productRepository.deleteById(id);
        }
    }

    @Override
    public DTOProductResponse updateProductById(DTOProductRequest dtoProductRequest, Long id) {
        Product showProduct = productRepository.findById(id).orElse(null);
        if (showProduct != null) {
            dtoProductRequest.requestProductToDTO(showProduct);
            Product updatedProduct = productRepository.save(showProduct);
            DTOProductResponse dtoProductResponse = new DTOProductResponse();
            dtoProductResponse.dtoResponseProduct(updatedProduct);
            return dtoProductResponse;
        }
        return null;
    }

    @Override
    public List<DTOProductResponse> getAllProduct(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection , sortBy);
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Product> pageProducts = productRepository.findAll(pageable);
        List<Product> showProducts = pageProducts.getContent();
        for (Product product : showProducts){
            DTOProductResponse dto = new DTOProductResponse();
            dto.dtoResponseProduct(product);
            return Collections.singletonList(dto);
        }
        return null;
    }


}
