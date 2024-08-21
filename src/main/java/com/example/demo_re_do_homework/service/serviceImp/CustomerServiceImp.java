package com.example.demo_re_do_homework.service.serviceImp;

import com.example.demo_re_do_homework.dto.dtoCustomer.dtoCustomerRequest.DTOCustomerRequest;
import com.example.demo_re_do_homework.dto.dtoCustomer.dtoCustomerResponse.DTOCustomerResponse;
import com.example.demo_re_do_homework.dto.dtoCustomer.dtoCustomerResponse.DTOSaveCustomerResponse;
import com.example.demo_re_do_homework.entity.Customer;
import com.example.demo_re_do_homework.entity.Email;
import com.example.demo_re_do_homework.repository.CustomerRepository;
import com.example.demo_re_do_homework.repository.EmailRepository;
import com.example.demo_re_do_homework.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EmailRepository emailRepository;

    public CustomerServiceImp(CustomerRepository customerRepository, EmailRepository emailRepository) {
        this.customerRepository = customerRepository;
        this.emailRepository = emailRepository;
    }
    @Override
    public DTOSaveCustomerResponse saveCustomer(DTOCustomerRequest dtoCustomerRequest) {
        Customer customer = new Customer();
        dtoCustomerRequest.requestCustomerToDTO(customer);
        Customer savedCustomer = customerRepository.save(customer);
        DTOSaveCustomerResponse dtoSaveCustomerResponse = new DTOSaveCustomerResponse();
        dtoSaveCustomerResponse.responseCustomerSaveToDTO(savedCustomer);
        return dtoSaveCustomerResponse;
    }
    @Override
    public DTOSaveCustomerResponse getCustomerById(Long id) {
        Customer savedCustomer = customerRepository.findById(id).orElse(null);
        DTOSaveCustomerResponse dtoSaveCustomerResponse = new DTOSaveCustomerResponse();
        dtoSaveCustomerResponse.responseCustomerSaveToDTO(Objects.requireNonNull(savedCustomer));
        return dtoSaveCustomerResponse;
    }

    @Override
    public void deleteCustomerById(Long id) {
        Customer deCustomer = customerRepository.findById(id).orElse(null);
        if (!Objects.isNull(deCustomer)){
            customerRepository.deleteById(id);
        }
    }

    @Override
    public DTOSaveCustomerResponse updateCustomerById(DTOCustomerRequest dtoCustomerRequest, Long id) {
        Customer findCustomer  = customerRepository.findById(id).orElse(null);
        if (!Objects.isNull(findCustomer)){
//          convert code three line above to dto requestUpdateCustomerTODTO
            dtoCustomerRequest.requestUpdateCustomerTODTO(findCustomer);
            if (dtoCustomerRequest.getEmail() != null && !dtoCustomerRequest.getEmail().equals(findCustomer.getEmail().getEmail())){
                Email oldEmail = findCustomer.getEmail();
                Email emailInDatabase = emailRepository.findByEmail(dtoCustomerRequest.getEmail());
                if (!Objects.isNull(emailInDatabase)){
                    findCustomer.setEmail(emailInDatabase);
                }else {
                    oldEmail.setEmail(dtoCustomerRequest.getEmail());
                }
            }
            Customer savedCustomer = customerRepository.save(findCustomer);
            DTOSaveCustomerResponse dtoSaveCustomerResponse = new DTOSaveCustomerResponse();
            dtoSaveCustomerResponse.responseCustomerSaveToDTO(savedCustomer);
            return dtoSaveCustomerResponse;
        }
        return null;
    }

    @Override
    public List<DTOCustomerResponse> getAllCustomer(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        List<Customer> customerList = customerPage.getContent();
        List<DTOCustomerResponse> dtoCustomerResponseList = new ArrayList<>();
        for (Customer customer : customerList) {
            DTOCustomerResponse dtoCustomerResponse = new DTOCustomerResponse();
            dtoCustomerResponse.responseCustomerToDTO(customer);
            dtoCustomerResponseList.add(dtoCustomerResponse);
        }
        return dtoCustomerResponseList;
    }

}
