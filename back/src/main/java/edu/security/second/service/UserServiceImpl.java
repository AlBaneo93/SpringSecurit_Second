package edu.security.second.service;

import edu.security.second.Exception.UserNotFoundException;
import edu.security.second.repository.UserRepository;
import edu.security.second.vo.Role;
import edu.security.second.vo.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * UserDetailsService : Springsecurity에서 제공하는 유저 검증 프로세스를 위한 인터페이스
 * */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private UserRepository userRepository;

  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException(email + " is not exist"));
    log.debug("Service - UserDetails User: " + user.toString());
    Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
    grantedAuthoritySet.add(new SimpleGrantedAuthority(Role.USER.getValue()));

    if (email.equals("my_email@gmail.com")) {
      grantedAuthoritySet.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
    }

    //    org.springframework.security.core.userdetails.User 객체의 파라미터 중 하나라도 null일 경우 cannot pass null or empty values to constructor 에러 발생
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthoritySet);
  }

  /*
   * User객체를 반환해주는 역할
   * 컨트롤러에서 넘어온 email과 password값이 db에 저장된 비밀번호와 일치하는지 검사
   * */
  @Override
  public User authenticationByEmailAndPassword(String email, String password) {
    User user = userRepository.findByEmail(email)
                              .orElseThrow(() -> new UsernameNotFoundException(email));

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Password not Matched");
    }

    return user;
  }

  @Override
  public User getOneUser(String email) throws UserNotFoundException {
    return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public Boolean deleteUser(String email) {
    return userRepository.deleteUserByEmail(email);
  }

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }


}
