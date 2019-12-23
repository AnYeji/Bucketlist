package com.rsupport.bucketlist.core.repository;

import com.rsupport.bucketlist.core.domain.Category;

import java.util.List;

public interface CategoryRepositoryCustom {

  Category getDefaultCategory(String userId);

  int getLastPriorityCategory(String userId);

  List<Category> getCategoryListByUserId(String userId);
}
