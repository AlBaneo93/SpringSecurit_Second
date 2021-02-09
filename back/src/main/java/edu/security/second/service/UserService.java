package edu.security.second.service;

import edu.security.second.Exception.UserNotFoundException;
import edu.security.second.vo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

  User authenticationByEmailAndPassword(String email, String password);

  User getOneUser(String email) throws UserNotFoundException;

  List<User> getAllUsers();

  Boolean deleteUser(String email);


  User saveUser(User user);
}
