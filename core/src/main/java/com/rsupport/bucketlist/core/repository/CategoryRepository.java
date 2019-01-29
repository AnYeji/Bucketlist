package com.rsupport.bucketlist.core.repository;

import com.rsupport.bucketlist.core.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String>{
}
