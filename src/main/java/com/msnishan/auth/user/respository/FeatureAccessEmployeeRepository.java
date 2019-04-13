package com.msnishan.auth.user.respository;

import com.msnishan.auth.user.domain.FeatureAccessEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureAccessEmployeeRepository extends JpaRepository<FeatureAccessEmployee, Long> {
}
