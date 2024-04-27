package com.ecomapp.userservice.Repositories;

import com.ecomapp.userservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    List<User> findAllBy();
    Optional<User> findUserById(Long id);
    @Query(value = "select * from User u where u.email = ?1", nativeQuery = true)
    Optional<User> findUserByEmail(String username);
}
