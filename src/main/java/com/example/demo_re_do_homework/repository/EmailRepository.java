package com.example.demo_re_do_homework.repository;

import com.example.demo_re_do_homework.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email , Long> {
    Email findByEmail(String email);
}
