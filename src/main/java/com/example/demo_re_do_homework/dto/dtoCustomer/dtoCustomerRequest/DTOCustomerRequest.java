package com.example.demo_re_do_homework.dto.dtoCustomer.dtoCustomerRequest;

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
public class DTOCustomerRequest {
    private String customerName;
    private String address;
    private String phoneNumber;
    private String email;
    public void requestCustomerToDTO(Customer customer){
        customer.setCustomerName(this.customerName);
        customer.setAddress(this.address);
        customer.setPhoneNumber(phoneNumber);
        // add field email to set email for customer
        Email emailCustomer = new Email();
        emailCustomer.setEmail(this.email);
        customer.setEmail(emailCustomer);
    }

    public void requestUpdateCustomerTODTO(Customer customer){
        customer.setCustomerName(customerName);
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNumber);
    }
}
