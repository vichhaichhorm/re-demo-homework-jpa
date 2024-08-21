package com.example.demo_re_do_homework.repository;

import com.example.demo_re_do_homework.entity.Customer;
import com.example.demo_re_do_homework.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> getOrderByCustomer(Customer customer);
//    List<Order> OrderByCustomerId(Long customerId);
}
