package com.rsupport.bucketlist.core.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

  void upload(MultipartFile file);
}
