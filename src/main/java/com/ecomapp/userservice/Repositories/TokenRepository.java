package com.ecomapp.userservice.Repositories;

import com.ecomapp.userservice.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);

    @Query("select t.Value from Token t where t.Deleted = false")
    Optional<Token> findTokenByValueAndDeletedEquals(String value, boolean deleted);
}
