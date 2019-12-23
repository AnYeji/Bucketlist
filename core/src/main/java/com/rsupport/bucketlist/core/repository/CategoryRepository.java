package com.rsupport.bucketlist.core.repository;

import com.rsupport.bucketlist.core.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String>, CategoryRepositoryCustom {

}
