package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Category;
import com.rsupport.bucketlist.core.repository.CategoryRepository;
import com.rsupport.bucketlist.core.vo.ModifyCategoryRequestVO;
import com.rsupport.bucketlist.core.vo.RemoveCategoryRequestVO;
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

  @Override
  public void modify(ModifyCategoryRequestVO requestVO) {
    Category category = categoryRepository.getOne(requestVO.getCategoryId());
    //카테고리 우선순위 조정

    categoryRepository.save(category);
  }

  @Override
  public void remove(String categoryId) {
    categoryRepository.deleteById(categoryId);
  }

}
