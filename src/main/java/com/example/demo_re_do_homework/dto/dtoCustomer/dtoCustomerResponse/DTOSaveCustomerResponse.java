package com.example.demo_re_do_homework.dto.dtoCustomer.dtoCustomerResponse;

import com.example.demo_re_do_homework.entity.Customer;
import com.example.demo_re_do_homework.entity.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DTOSaveCustomerResponse {
    private Long customerId;
    private String customerName;
    private String address;
    private String phoneNumber;
    private Email email;
    public void responseCustomerSaveToDTO(Customer customer){
        this.customerId = customer.getCustomerId();
        this.customerName = customer.getCustomerName();
        this.address = customer.getAddress();
        this.phoneNumber = customer.getPhoneNumber();
        this.email = customer.getEmail();
    }
}
