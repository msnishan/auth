package com.msnishan.auth.user.service;

import com.msnishan.auth.base.db.EntityFactory;
import com.msnishan.auth.dto.UserDTO;
import com.msnishan.auth.user.domain.FeatureAccess;
import com.msnishan.auth.user.domain.FeatureAccessEmployee;
import com.msnishan.auth.user.domain.QFeatureAccess;
import com.msnishan.auth.user.domain.QUser;
import com.msnishan.auth.user.domain.User;
import com.msnishan.auth.user.respository.FeatureAccessEmployeeRepository;
import com.msnishan.auth.user.respository.UserRepository;
import com.msnishan.gen.type.request.RequestContext;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private FeatureAccessEmployeeRepository featureAccessEmployeeRepository;
    private FeatureAccessService featureAccessService;
    private EntityFactory entityFactory;
    private PasswordEncoder passwordEncoder;


    @PersistenceContext
    private EntityManager entityManager;

    public UserService(EntityFactory entityFactory, UserRepository userRepository, FeatureAccessEmployeeRepository featureAccessEmployeeRepository,
                       FeatureAccessService featureAccessService,
                       @Qualifier("userPasswordEncoder")
                       @Lazy
                       PasswordEncoder passwordEncoder) {
        this.entityFactory = entityFactory;
        this.userRepository = userRepository;
        this.featureAccessEmployeeRepository = featureAccessEmployeeRepository;
        this.featureAccessService = featureAccessService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User createUser(RequestContext context, UserDTO userDTO) {
        User user = entityFactory.newEntity(User.class);
        user.setEmail(userDTO.getMailId());
        user.setEmployeeId(userDTO.getEmployeeId());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPosId(context.getPosId());
        user.setRequestId(context.getRequestId());
        user.setDesignation(userDTO.getDesignation());
        user.setEnabled(true);
        userDTO.getFeatureAccess().forEach(featureAccessDTO -> {
            FeatureAccess featureAccess = featureAccessService.findFeatureAccessById(context, featureAccessDTO.getFeatureAccessId());
            FeatureAccessEmployee featureAccessEmployee = entityFactory.newEntity(FeatureAccessEmployee.class);
            featureAccessEmployee.setPosId(context.getPosId());
            featureAccessEmployee.setRequestId(context.getRequestId());
            featureAccessEmployee.setEnabled(true);
            user.addFeatureAccessEmployee(featureAccessEmployee);
            featureAccess.addFeatureAccessEmployee(featureAccessEmployee);
            featureAccessEmployeeRepository.save(featureAccessEmployee);
            featureAccessService.update(context, featureAccess);
            userRepository.save(user);
        });
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUserById(RequestContext context, String email) {
        JPAQuery query = new JPAQuery(entityManager);
        QUser user = QUser.user;
        return query.from(user)
                .where(user.email.eq(email).and(user.posId.eq(context.getPosId()))).uniqueResult(user);
    }


    @Transactional(readOnly = true)
    public List<FeatureAccess> findFeatureAccessByEmployeeId(RequestContext context, String employeeId) {
        JPAQuery query = new JPAQuery(entityManager);
        QUser qUser = QUser.user;
        User user =  query.from(qUser)
                .where(qUser.employeeId.eq(employeeId)).uniqueResult(qUser);
        return user.getFeatureAccessEmployees().stream()
                                .map(FeatureAccessEmployee::getFeature)
                                .collect(Collectors.toList());
    }
}