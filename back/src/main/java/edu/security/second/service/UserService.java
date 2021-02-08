package edu.security.second.service;

import edu.security.second.vo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  //  User SignIn(String username, String password);
  //
  //  User SignOut(String username, String password);
  //
  User SignUp(User user);

  User authenticationByEmailAndPassword(String email, String password);
}
