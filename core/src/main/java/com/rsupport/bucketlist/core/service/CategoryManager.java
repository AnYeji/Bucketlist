package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Category;

import java.util.List;

public interface CategoryManager {

    List<Category> getCategoriesByUserId();
}
