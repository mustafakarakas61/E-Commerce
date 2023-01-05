package com.bringtome.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", initialValue = 17, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "description")
    private String description;

    @Column(name = "product_title")
    private String productTitle;

    @Column(name = "producer")
    private String producer;

    @Column(name = "year")
    private Integer year;
    
    @Column(name = "city")
    private String city;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "colors")
    private String colors;

    @Column(name = "price")
    private Integer price;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "product_rating")
    private Double productRating;

    @OneToMany
    @ToString.Exclude
    private List<ReviewEntity> reviews;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ProductEntity product = (ProductEntity) object;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
