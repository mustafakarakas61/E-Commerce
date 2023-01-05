package com.bringtome.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_seq", initialValue = 6, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "post_index")
    private Integer postIndex;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(fetch = FetchType.EAGER)
    private List<OrderItemEntity> orderItems;

    public OrderEntity() {
        this.date = LocalDate.now();
        this.orderItems = new ArrayList<>();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OrderEntity orderEntity = (OrderEntity) object;
        return Objects.equals(id, orderEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
