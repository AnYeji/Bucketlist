package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Category;
import com.rsupport.bucketlist.core.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryManager")
public class CategoryManagerImpl implements CategoryManager {

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public String getLastCategoryId() {
    return categoryRepository.getLastCatoryId();
  }

  @Override
  public Category getCategoryByName(String name) {
    return categoryRepository.getCategoryByName(name);
  }
}
