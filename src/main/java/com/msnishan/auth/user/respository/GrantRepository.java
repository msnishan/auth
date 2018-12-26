package com.msnishan.auth.user.respository;

import com.msnishan.auth.user.domain.Grant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrantRepository extends JpaRepository<Grant, Long> {
}
