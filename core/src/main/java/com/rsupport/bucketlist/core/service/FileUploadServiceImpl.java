package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.util.DateUtil;
import com.rsupport.bucketlist.core.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

  @Override
  public void upload(MultipartFile file) {
    String extension = FileUtil.getExtension(file.getOriginalFilename());
    String saveFileName = String.format("%s.%s", UUID.randomUUID().toString(), extension);
    String uploadDate = DateUtil.getDateString("yyyyMMdd");

    FileUtil.upload(file, String.format("%s/%s", "D:\\dev-bucketlist\\workspace\\bucketlist\\storage", uploadDate), saveFileName);
  }
}
