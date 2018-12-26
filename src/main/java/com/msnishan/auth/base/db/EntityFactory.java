package com.msnishan.auth.base.db;

import com.msnishan.auth.base.domain.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Service
public class EntityFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityFactory.class);

    private SequenceGenerator sequenceGenerator;

    public EntityFactory(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    public <T extends BaseEntity> T newEntity(Class<T> clazz) {
        Constructor<T> constructor;
        T entity;
        try {
            constructor= clazz.getConstructor(Long.class);
        } catch (NoSuchMethodException e) {
            LOGGER.error("ERROR:");
            throw new IllegalArgumentException(e);
        }

        try {
            entity = constructor.newInstance(sequenceGenerator.nextId());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("ERROR:");
            throw new IllegalArgumentException(e);
        }
        return entity;
    }
}
