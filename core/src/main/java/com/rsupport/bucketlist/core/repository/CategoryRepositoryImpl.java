package com.rsupport.bucketlist.core.repository;

import com.mysema.query.jpa.impl.JPAQuery;
import com.rsupport.bucketlist.core.domain.Category;
import com.rsupport.bucketlist.core.domain.QCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("categoryRepository")
public class CategoryRepositoryImpl implements CategoryRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Category> getCategoriesByUserId() {
        JPAQuery query = new JPAQuery(entityManager);
        QCategory category = QCategory.category;

        query.from(category).where(category.user().id.eq("user01"));
        return query.list(category);
    }
}
