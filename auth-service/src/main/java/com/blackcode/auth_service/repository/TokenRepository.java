package com.blackcode.auth_service.repository;

import com.blackcode.auth_service.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByToken(String token);

    Optional<Token> findByUserId(Long userId);

    Optional<Token> findByUserIdAndToken(Long userId, String token);

    void deleteByToken(String token);
}
