package com.bringtome.ecommerce.entity;

import com.bringtome.ecommerce.enums.AuthProviderEnum;
import com.bringtome.ecommerce.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", initialValue = 6, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "activation_code")
    private String activationCode;

    @Column(name = "active")
    private boolean active;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "password_reset_code")
    private String passwordResetCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "post_index")
    private String postIndex;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private AuthProviderEnum provider;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> roles;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass()
                != object.getClass()) return false;
        UserEntity user = (UserEntity) object;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
