package com.msnishan.auth.user.service;

import com.msnishan.auth.base.db.EntityFactory;
import com.msnishan.auth.dto.FeatureAccessDTO;
import com.msnishan.auth.user.domain.FeatureAccess;
import com.msnishan.auth.user.domain.QFeatureAccess;
import com.msnishan.auth.user.respository.FeatureAccessRepository;
import com.msnishan.gen.type.request.RequestContext;
import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class FeatureAccessService {

    private FeatureAccessRepository featureAccessRepository;
    private EntityFactory entityFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public FeatureAccessService(FeatureAccessRepository featureAccessRepository, EntityFactory entityFactory) {
        this.featureAccessRepository = featureAccessRepository;
        this.entityFactory = entityFactory;
    }

    @Transactional(readOnly = true)
    public FeatureAccess findFeatureAccessById(RequestContext context, String featureId) {
        JPAQuery query = new JPAQuery(entityManager);
        QFeatureAccess qFeatureAccess = QFeatureAccess.featureAccess;
        return query.from(qFeatureAccess)
                    .where(qFeatureAccess.featureId.eq(featureId)).uniqueResult(qFeatureAccess);
    }

    @Transactional(readOnly = true)
    public List<FeatureAccess> findAllFeatureAccess(RequestContext context) {
        return featureAccessRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public FeatureAccess saveFeatureAccess(RequestContext context, FeatureAccessDTO featureAccessDTO) {
        FeatureAccess featureAccess = entityFactory.newEntity(FeatureAccess.class);
        featureAccess.setFeatureId(featureAccessDTO.getFeatureAccessId());
        featureAccess.setFeatureName(featureAccessDTO.getFeatureName());
        featureAccess.setFeatureUrl(featureAccessDTO.getFeatureUrl());
        featureAccess.setMethod(featureAccessDTO.getMethod());
        //
        featureAccess.setCreatedBy(context.getUserId());
        featureAccess.setEnabled(true);
        featureAccess.setRequestId(context.getRequestId());
        return featureAccessRepository.save(featureAccess);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public FeatureAccess update(RequestContext context, FeatureAccess featureAccess) {
        return featureAccessRepository.save(featureAccess);
    }
}
