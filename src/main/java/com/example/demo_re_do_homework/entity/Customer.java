package com.example.demo_re_do_homework.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customer_tb")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(nullable = false, length = 30)
    private String customerName;

    @Column(nullable = false, length = 20)
    private String address;

    @Column(nullable = false, length = 12)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "email_id", referencedColumnName = "id")
    private Email email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();
}
