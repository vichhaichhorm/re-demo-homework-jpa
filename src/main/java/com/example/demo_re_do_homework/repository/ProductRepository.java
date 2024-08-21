package com.example.demo_re_do_homework.repository;

import com.example.demo_re_do_homework.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product ,Long> {
}
