package edu.security.second.service;

import edu.security.second.repository.UserRepository;
import edu.security.second.vo.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/*
* UserDetailsService : Springsecurity에서 제공하는 유저 검증 프로세스를 위한 인터페이스
* */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUserName(username).orElseThrow(() -> new IllegalArgumentException(username + "is not exist"));
    return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
  }

  @Override
  public User SignUp(User user) {
    return userRepository.save(user);
  }
}
