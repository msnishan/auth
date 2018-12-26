package com.msnishan.auth.user.service;

import com.msnishan.auth.base.db.EntityFactory;
import com.msnishan.auth.dto.GroupDTO;
import com.msnishan.auth.user.domain.Group;
import com.msnishan.auth.user.domain.QGroup;
import com.msnishan.auth.user.respository.GroupRepository;
import com.msnishan.auth.user.util.DtoFactory;
import com.msnishan.gen.type.request.RequestContext;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class GroupService {

    private EntityFactory entityFactory;
    private GroupRepository groupRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public GroupService(EntityFactory entityFactory, GroupRepository groupRepository, EntityManager entityManager) {
        this.entityFactory = entityFactory;
        this.groupRepository = groupRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Group createGroup(RequestContext context, GroupDTO groupDTO) {
        Group group = entityFactory.newEntity(Group.class);
        group.setGroupId(groupDTO.getGroupId());
        group.setDescription(groupDTO.getDescription());
        return groupRepository.save(group);
    }

    public Group findGroupById(RequestContext context, String groupId) {
        JPAQuery query = new JPAQuery(entityManager);
        QGroup group = QGroup.group;
        return query.from(group)
                .where(group.groupId.eq(groupId).and(group.companyId.eq(context.getCompanyId()))).uniqueResult(group);
    }
}
