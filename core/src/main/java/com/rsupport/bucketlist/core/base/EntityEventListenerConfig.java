package com.rsupport.bucketlist.core.base;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

@Configuration
public class EntityEventListenerConfig {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityEventListener entityEventListener;

    @PostConstruct
    public void registerListeners() {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        EventListenerRegistry registry = ((SessionFactoryImpl)sessionFactory).getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(entityEventListener);
        registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(entityEventListener);
    }
}
