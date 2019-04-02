package com.rsupport.bucketlist.core.repository;

import com.rsupport.bucketlist.core.domain.Category;

import java.util.List;

public interface CategoryRepositoryCustom {

    List<Category> getCategoriesByUserId();
}
