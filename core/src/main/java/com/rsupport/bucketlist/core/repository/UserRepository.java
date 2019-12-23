package com.rsupport.bucketlist.core.repository;

import com.rsupport.bucketlist.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom {
}
