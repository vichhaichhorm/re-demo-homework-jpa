package com.example.demo_re_do_homework.dto.dtoOrder.dtoOrderRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DTOOrderRequest {
    private int quantity;
    private Long productId;

}
