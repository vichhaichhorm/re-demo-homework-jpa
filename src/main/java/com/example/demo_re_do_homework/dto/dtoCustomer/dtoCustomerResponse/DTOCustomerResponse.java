package com.example.demo_re_do_homework.dto.dtoCustomer.dtoCustomerResponse;

import com.example.demo_re_do_homework.dto.dtoOrder.dtoOrderResponse.DTOOrderProductResponse;
import com.example.demo_re_do_homework.entity.Customer;
import com.example.demo_re_do_homework.entity.Email;
import com.example.demo_re_do_homework.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DTOCustomerResponse {
    private Long customerId;
    private String customerName;
    private String address;
    private String phoneNumber;
    private Email email;
    private List<DTOOrderProductResponse> orderList = new ArrayList<>();

    public void responseCustomerToDTO(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.customerName = customer.getCustomerName();
        this.address = customer.getAddress();
        this.phoneNumber = customer.getPhoneNumber();
        this.email = customer.getEmail();

        this.orderList.clear(); // Clear the list before adding new items

        for (Order order : customer.getOrders()) {
            DTOOrderProductResponse dtoOrderProductResponse = new DTOOrderProductResponse();
            dtoOrderProductResponse.responseOrderProductTODTO(order);
            this.orderList.add(dtoOrderProductResponse);
        }
    }


}
