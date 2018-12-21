package com.rsupport.bucketlist.core.repository;

import com.rsupport.bucketlist.core.domain.Bucketlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketlistRepository extends JpaRepository<Bucketlist, String>, BucketlistRepositoryCustom {
}
