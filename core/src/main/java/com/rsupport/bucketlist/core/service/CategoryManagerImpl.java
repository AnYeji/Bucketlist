package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Category;
import com.rsupport.bucketlist.core.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryManager")
public class CategoryManagerImpl implements CategoryManager{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategoriesByUserId() {
        return categoryRepository.getCategoriesByUserId();
    }
}
