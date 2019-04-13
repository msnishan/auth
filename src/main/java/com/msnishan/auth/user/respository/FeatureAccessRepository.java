package com.msnishan.auth.user.respository;

import com.msnishan.auth.user.domain.FeatureAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureAccessRepository extends JpaRepository<FeatureAccess, Long> {
}
