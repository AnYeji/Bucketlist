package com.rsupport.bucketlist.core.service;

import com.rsupport.bucketlist.core.domain.Category;
import com.rsupport.bucketlist.core.domain.User;
import com.rsupport.bucketlist.core.exception.UserAlreadyExistsException;
import com.rsupport.bucketlist.core.repository.CategoryRepository;
import com.rsupport.bucketlist.core.repository.UserRepository;
import com.rsupport.bucketlist.core.util.DateUtil;
import com.rsupport.bucketlist.core.vo.CreateProfileRequestVO;
import com.rsupport.bucketlist.core.vo.HostSignInRequestVO;
import com.rsupport.bucketlist.core.vo.HostSignUpRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManagerImpl implements UserManager {

  private String authServerAddress = "http://18.213.99.39";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private FileUploadService fileUploadService;

  @Override
  public User getUserById(String userId) {
    return userRepository.getOne(userId);
  }

  @Override
  public User getUserByEmail(String email) {
    return userRepository.getUserByEmail(email);
  }

  @Override
  @Transactional
  public User signup(HostSignUpRequestVO requestVO) {
    if(userRepository.getUserByEmail(requestVO.getEmail()) != null)
      throw new UserAlreadyExistsException("user already exists");

    User user = new User();
    user.setEmail(requestVO.getEmail());

    Category category = new Category();
    category.setName("없음");
    category.setPriority(0);
    category.setUser(user);
    categoryRepository.save(category);

    return userRepository.save(user);
  }

  @Override
  @Transactional
  public User signin(HostSignInRequestVO requestVO) {
    User user = userRepository.getOne(requestVO.getUserId());
    user.setLastLoginDate(DateUtil.getDate());
    return userRepository.save(user);
  }

  @Override
  @Transactional
  public void createProfile(CreateProfileRequestVO requestVO) {
    User user = userRepository.getOne(requestVO.getUserId());
    user.setName(requestVO.getName());

    if(requestVO.isDefaultImg())
      user.setImgUrl(null);

    if(requestVO.getMultipartFile() != null) {
      String imgUrl = fileUploadService.upload(requestVO.getMultipartFile());
      user.setImgUrl(authServerAddress + imgUrl);
    }

    userRepository.save(user);
  }

  @Override
  public void remove(String userId) {
    userRepository.deleteById(userId);
  }
}
