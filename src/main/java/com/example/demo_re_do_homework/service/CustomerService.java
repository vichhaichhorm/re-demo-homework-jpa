package com.example.demo_re_do_homework.service;

import com.example.demo_re_do_homework.dto.dtoCustomer.dtoCustomerRequest.DTOCustomerRequest;
import com.example.demo_re_do_homework.dto.dtoCustomer.dtoCustomerResponse.DTOCustomerResponse;
import com.example.demo_re_do_homework.dto.dtoCustomer.dtoCustomerResponse.DTOSaveCustomerResponse;
import com.example.demo_re_do_homework.entity.Customer;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CustomerService {
    DTOSaveCustomerResponse saveCustomer(DTOCustomerRequest dtoCustomerRequest);

    DTOSaveCustomerResponse getCustomerById(Long id);

    void deleteCustomerById(Long id);

    DTOSaveCustomerResponse updateCustomerById(DTOCustomerRequest dtoCustomerRequest, Long id);

    List<DTOCustomerResponse> getAllCustomer(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction sortDirection);
}
