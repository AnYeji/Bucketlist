package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Category;
import com.rsupport.bucketlist.core.vo.ModifyCategoryNameRequestVO;
import com.rsupport.bucketlist.core.vo.ModifyCategoryPriorityRequestVO;
import com.rsupport.bucketlist.core.vo.RemoveCategoryRequestVO;

import java.util.List;

public interface CategoryManager {

  void save(Category category);

  int getLastPriorityCategory(String userId);

  void modifyName(ModifyCategoryNameRequestVO requestVO);

  void modifyPriority(ModifyCategoryPriorityRequestVO requestVO);

  void remove(RemoveCategoryRequestVO requestVO);

  List<Category> getCategoryListByUserId(String userId);
}
