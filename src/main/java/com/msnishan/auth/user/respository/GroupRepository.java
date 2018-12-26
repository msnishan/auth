package com.msnishan.auth.user.respository;

import com.msnishan.auth.user.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
