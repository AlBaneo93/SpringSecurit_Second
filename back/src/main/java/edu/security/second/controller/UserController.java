package edu.security.second.controller;

import edu.security.second.Exception.UserNotFoundException;
import edu.security.second.service.UserService;
import edu.security.second.vo.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {

  private UserService userService;

  private PasswordEncoder passwordEncoder;

  @PostMapping("/users")
  public ResponseEntity<Map<String, Object>> signUp(@RequestBody User reqUser) {
    log.info("User SignUP: " + reqUser.toString());
    Map<String, Object> map = new HashMap<>();

    //User의 패스워드 암호화
    reqUser.setPassword(passwordEncoder.encode(reqUser.getPassword()));
    User ret = userService.saveUser(reqUser);
    ret.setPassword("");

    map.put("result", ret);
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @GetMapping("/users")
  public ResponseEntity<Map<String, Object>> getAllUsers() {
    Map<String, Object> map = new HashMap<>();
    map.put("result", userService.getAllUsers());
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @GetMapping("/users/{email}")
  public ResponseEntity<Map<String, Object>> getOneUser(@PathVariable("email") String email) throws UserNotFoundException {
    Map<String, Object> map = new HashMap<>();
    log.info("Get specific user data: " + email);

    map.put("result", userService.getOneUser(email));

    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @DeleteMapping("/users/{email}")
  public ResponseEntity<Map<String, Object>> userDelete(@PathVariable("email") String email) {
    Map<String, Object> map = new HashMap<>();
    log.info("Delete user: " + email);
    map.put("result", userService.deleteUser(email));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @PutMapping("/users")
  public ResponseEntity<Map<String, Object>> userUpdate(@RequestBody User user) {
    Map<String, Object> map = new HashMap<>();
    log.info("Update User info: " + user.getName());
    userService.saveUser(user);
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
  //  @GetMapping("/admin")
  //  public ResponseEntity<Map<String, Object>> Admin() {
  //    Map<String, Object> map = new HashMap<>();
  //    return new ResponseEntity<>(map, HttpStatus.OK);
  //  }
  //
  //  @PreAuthorize("hasRole('ROLE_MEMBER')")
  //  @GetMapping("/info")
  //  public ResponseEntity<Map<String, Object>> Info() {
  //    Map<String, Object> map = new HashMap<>();
  //    return new ResponseEntity<>(map, HttpStatus.OK);
  //  }
  //
  //  @GetMapping("/denied")
  //  public ResponseEntity<Map<String, Object>> AuthDenied() {
  //    Map<String, Object> map = new HashMap<>();
  //    log.info("Access Security Denied");
  //
  //    return new ResponseEntity<>(map, HttpStatus.OK);
  //  }
}
