package com.rsupport.bucketlist.core.repository;

import com.rsupport.bucketlist.core.domain.PushMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PushMessageRepository extends JpaRepository<PushMessage, String>, PushMessageRepositoryCustom{
}
