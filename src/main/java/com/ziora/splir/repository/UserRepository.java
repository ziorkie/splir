package com.ziora.splir.repository;

import com.ziora.splir.model.User;
import com.ziora.splir.payload.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT new com.ziora.splir.payload.UserResponse(u.id, u.username, u.name)  FROM User u" )
    List<UserResponse> getAll();

    List<User> findAllByRoom_id(Long id);

    Optional<User> findById(Long id);

}