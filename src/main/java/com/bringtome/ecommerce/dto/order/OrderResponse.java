package com.bringtome.ecommerce.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private String address;
    private String city;
    private LocalDate date;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Integer postIndex;
    private Double totalPrice;
}
