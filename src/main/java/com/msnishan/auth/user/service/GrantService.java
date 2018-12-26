package com.msnishan.auth.user.service;

import com.msnishan.auth.base.db.EntityFactory;
import com.msnishan.auth.dto.GrantDTO;
import com.msnishan.auth.dto.GroupDTO;
import com.msnishan.auth.user.domain.Grant;
import com.msnishan.auth.user.domain.Group;
import com.msnishan.auth.user.domain.QGrant;
import com.msnishan.auth.user.domain.QGroup;
import com.msnishan.auth.user.respository.GrantRepository;
import com.msnishan.auth.user.respository.GroupRepository;
import com.msnishan.gen.type.request.RequestContext;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class GrantService {

    private EntityFactory entityFactory;
    private GrantRepository grantRepository;
    private GroupService groupService;

    @PersistenceContext
    private EntityManager entityManager;

    public GrantService(EntityFactory entityFactory, GrantRepository grantRepository, EntityManager entityManager,
                        GroupService groupService) {
        this.entityFactory = entityFactory;
        this.grantRepository = grantRepository;
        this.groupService = groupService;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Grant createGroup(RequestContext context, GrantDTO grantDTO) {
        Grant grant = entityFactory.newEntity(Grant.class);
        grant.setGrantId(grantDTO.getGrantId());
        grant.setGroup(groupService.findGroupById(context, grantDTO.getGroupId()));
        return grantRepository.save(grant);
    }

    @Transactional(readOnly = true)
    public Grant findGrantById(RequestContext context, String grantId) {
        JPAQuery query = new JPAQuery(entityManager);
        QGrant grant = QGrant.grant;
        return query.from(grant)
                .where(grant.grantId.eq(grantId).and(grant.companyId.eq(context.getCompanyId()))).uniqueResult(grant);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Grant saveGrant(RequestContext context, Grant grant) {
        return grantRepository.save(grant);
    }
}
