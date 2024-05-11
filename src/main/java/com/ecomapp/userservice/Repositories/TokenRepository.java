package com.ecomapp.userservice.Repositories;

import com.ecomapp.userservice.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);
    @Query(value="select * from Token t where t.value = :value" ,nativeQuery = true)
    Optional<Token> findByValue(String value); //hibernate query not working, need to check --> resolved

    Optional<Token> findByValueAndDeletedEquals(String tkn, boolean isDeleted);
    //earlier hibernate query not working due to not following naming convention in model classes,
    // use value instead of Value

<<<<<<< HEAD
    //@Query("select  from Token t where t.Deleted = false and t.Value = :value")
    Optional<Token> findTokenByValue(String token);
=======
>>>>>>> 20e07dd6f5162bb30f3d3a84eedf7fe889138651
}
