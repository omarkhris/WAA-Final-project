package com.miu.pmtbackendapi.repo.user;

import com.miu.pmtbackendapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    @Query("Select u from User u where u.email=:email")
    Optional<User> findAUserByEmail(String email);

    @Query("Select u from User u where u.email=:email AND u.userStatus='ACTIVE'")
    User findActiveUserByEmail(@Param("email") String email);

    @Query("Select u from User u JOIN u.userRole r where r.user_role=:role")
    List<User> finAllCustomers(String role);
}
