package com.msnishan.auth.user.respository;

import com.msnishan.auth.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
