package com.rsupport.bucketlist.core.repository;

import com.rsupport.bucketlist.core.domain.Category;

public interface CategoryRepositoryCustom {

  String getLastCatoryId();

  Category getCategoryByName(String name);
}
