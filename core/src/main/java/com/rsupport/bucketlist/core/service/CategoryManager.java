package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Category;

public interface CategoryManager {

  String getLastCategoryId();

  Category getCategoryByName(String name);
}
