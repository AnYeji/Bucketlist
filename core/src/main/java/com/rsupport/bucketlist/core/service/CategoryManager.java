package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Category;
import com.rsupport.bucketlist.core.vo.ModifyCategoryRequestVO;

public interface CategoryManager {

  String getLastCategoryId();

  Category getCategoryByName(String name);

  void modify(ModifyCategoryRequestVO requestVO);

  void remove(String categoryId);

}
