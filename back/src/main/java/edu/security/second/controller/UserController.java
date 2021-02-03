package edu.security.second.controller;

import edu.security.second.service.UserService;
import edu.security.second.vo.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
@AllArgsConstructor
public class UserController {

  private UserService userService;

  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @PostMapping("/signup")
  public ResponseEntity<Map<String, Object>> SignUp(@RequestBody User reqUser) {
    log.info("User SignUP" + reqUser.toString());
    Map<String, Object> map = new HashMap<>();
    reqUser.setPassword(bCryptPasswordEncoder.encode(reqUser.getPassword()));
    map.put("result", userService.SignUp(reqUser));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @PostMapping("/signin")
  public ResponseEntity<Map<String, Object>> Signin() {
    Map<String, Object> map = new HashMap<>();
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("/admin")
  public ResponseEntity<Map<String, Object>> Admin() {
    Map<String, Object> map = new HashMap<>();
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ROLE_MEMBER')")
  @GetMapping("/info")
  public ResponseEntity<Map<String, Object>> Info() {
    Map<String, Object> map = new HashMap<>();
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  @GetMapping("/denied")
  public ResponseEntity<Map<String, Object>> AuthDenied() {
    Map<String, Object> map = new HashMap<>();
    log.info("Access Security Denied");

    return new ResponseEntity<>(map, HttpStatus.OK);
  }
}
