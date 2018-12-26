package com.msnishan.auth.user.service;

import com.msnishan.auth.base.db.EntityFactory;
import com.msnishan.auth.dto.UserDTO;
import com.msnishan.auth.user.domain.Grant;
import com.msnishan.auth.user.domain.QUser;
import com.msnishan.auth.user.domain.User;
import com.msnishan.auth.user.domain.UserGrant;
import com.msnishan.auth.user.respository.UserRepository;
import com.msnishan.gen.type.request.RequestContext;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService {

    private UserRepository userRepository;
    private GrantService grantService;
    private EntityFactory entityFactory;
    private PasswordEncoder passwordEncoder;


    @PersistenceContext
    private EntityManager entityManager;

    public UserService(EntityFactory entityFactory, UserRepository userRepository, GrantService grantService,
                       @Qualifier("userPasswordEncoder") PasswordEncoder passwordEncoder) {
        this.entityFactory = entityFactory;
        this.userRepository = userRepository;
        this.grantService = grantService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User createUser(RequestContext context, UserDTO userDTO) {
        User user = entityFactory.newEntity(User.class);
        user.setEmail(userDTO.getMailId());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setCompanyId(userDTO.getCompanyId());
        userDTO.getGrants().forEach(grantId -> {
            Grant grant = grantService.findGrantById(context, grantId);
            UserGrant userGrant = entityFactory.newEntity(UserGrant.class);
            user.addUserGrant(userGrant);
            grant.addUserGrant(userGrant);
            grantService.saveGrant(context, grant);
        });
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUserById(RequestContext context, String email) {
        JPAQuery query = new JPAQuery(entityManager);
        QUser user = QUser.user;
        return query.from(user)
                .where(user.email.eq(email).and(user.companyId.eq(context.getCompanyId()))).uniqueResult(user);
    }
}