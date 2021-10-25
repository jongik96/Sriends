package com.project.autonomous.user.repository;

import com.project.autonomous.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    User findUserByEmail(String email);
    Optional<User> findByEmail(String email);
}
=======

public interface UserRepository extends JpaRepository<User, Long> {

}
>>>>>>> 50a2b1ba14286ef47887be3dca4daffc9b9e54b7
