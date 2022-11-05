package com.bmod.ecommerce.repository;

import com.bmod.ecommerce.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByOrderByIdAsc();

    Page<UserEntity> findAllByOrderByIdAsc(Pageable pageable);

    Optional<UserEntity> findByActivationCode(String code);

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT user.email FROM UserEntity user WHERE user.passwordResetCode = :code")
    Optional<String> getEmailByPasswordResetCode(String code);
}
