package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Category;

public interface CategoryManager {

  String getLastCategoryId();

  Category getCategoryByCategoryId(String cateogyrId);
}
