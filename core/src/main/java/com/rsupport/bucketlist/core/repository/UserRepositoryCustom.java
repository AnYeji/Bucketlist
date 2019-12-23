package com.rsupport.bucketlist.core.repository;


import com.rsupport.bucketlist.core.domain.User;

public interface UserRepositoryCustom {

    User getUserByEmail(String email);
}
