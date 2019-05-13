package com.rsupport.bucketlist.core.util;

import java.io.File;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public final class FileUtil {

  public static File upload(MultipartFile multipartFile, String uploadDir, String saveFileName) {
    File dirPath = new File(uploadDir);
    if (!dirPath.exists()) {
      boolean made = dirPath.mkdirs();
      if (!made) {
        throw new RuntimeException(String.format("make directory(%s) fail", uploadDir));
      }
    }

    String targetFilePath = uploadDir + "/" + saveFileName;
    File targetFile = new File(targetFilePath);
    try {
      multipartFile.transferTo(targetFile);
      log.debug(String.format("upload finished...(%s) ", targetFilePath));
    } catch (IOException ex) {
      throw new RuntimeException(String.format("file upload error:%s", targetFilePath), ex);
    }
    return targetFile;
  }

  public static String getExtension(String filename) {
    filename = new File(filename).getName();
    if (filename == null) {
      return null;
    }
    int index = FilenameUtils.indexOfExtension(filename);
    if (index == -1) {
      return "";
    } else {
      return filename.substring(index + 1);
    }
  }
}
