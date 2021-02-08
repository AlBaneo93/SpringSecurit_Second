package edu.security.second.service;

import edu.security.second.repository.UserRepository;
import edu.security.second.vo.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/*
 * UserDetailsService : Springsecurity에서 제공하는 유저 검증 프로세스를 위한 인터페이스
 * */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private UserRepository userRepository;

  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByName(username).orElseThrow(() -> new IllegalArgumentException(username + "is not exist"));
    return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
  }

  @Override
  public User SignUp(User user) {
    return userRepository.save(user);
  }

  /*
   * User객체를 반환해주는 역할
   * 컨트롤러에서 넘어온 email과 password값이 db에 저장된 비밀번호와 일치하는지 검사
   * */
  @Override
  public User authenticationByEmailAndPassword(String email, String password) {
    User user = userRepository.findByEmail(email)
                              .orElseThrow(() -> new UsernameNotFoundException(email));

    if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Password not Matched");
    }

    return user;
  }
}
