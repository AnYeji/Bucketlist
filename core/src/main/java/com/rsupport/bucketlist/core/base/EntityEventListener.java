package com.rsupport.bucketlist.core.base;

import com.rsupport.bucketlist.core.domain.BaseTimestampEntity;
import com.rsupport.bucketlist.core.util.DateUtil;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EntityEventListener implements PreInsertEventListener, PreUpdateEventListener{

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        Object entity = event.getEntity();

        if (entity instanceof BaseTimestampEntity) {
            BaseTimestampEntity<?> timestampEntity = (BaseTimestampEntity<?>)entity;
            Date now = DateUtil.getDate();
            handleTimestampEntityOnInsert(event, timestampEntity, now);
        }

        return false;
    }

    private void handleTimestampEntityOnInsert(PreInsertEvent event, BaseTimestampEntity<?> timestampEntity, Date now) {
        String[] names = event.getPersister().getPropertyNames();
        Object[] values = event.getState();
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals("updatedDt") || names[i].equals("createdDt")) {
                values[i] = now;
            }
        }

        timestampEntity.setCreatedDt(now);
        timestampEntity.setUpdatedDt(now);
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        Object entity = event.getEntity();
        if (entity instanceof BaseTimestampEntity) {
            BaseTimestampEntity<?> timestampEntity = (BaseTimestampEntity<?>)entity;
            Date now = DateUtil.getDate();
            handleTimestampEntityOnUpdate(event, timestampEntity, now);
        }

        return false;
    }

    private void handleTimestampEntityOnUpdate(PreUpdateEvent event, BaseTimestampEntity<?> timestampEntity, Date now) {
        String[] names = event.getPersister().getPropertyNames();
        Object[] values = event.getState();
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals("updatedDt")) {
                values[i] = now;
            }
        }
        timestampEntity.setUpdatedDt(now);
    }
}
