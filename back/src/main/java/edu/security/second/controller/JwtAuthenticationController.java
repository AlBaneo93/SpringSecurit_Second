package edu.security.second.controller;

import edu.security.second.config.JwtTokenUtil;
import edu.security.second.service.UserService;
import edu.security.second.vo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

  private JwtTokenUtil jwtTokenUtil;

  private UserService userService;


  @PostMapping("/authenticate")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
    final User user = userService.authenticationByEmailAndPassword(authenticationRequest.getEmail(), authenticationRequest.getPassword());

    final String token = jwtTokenUtil.generateToken(user.getEmail());
    return ResponseEntity.ok(new JwtResponse(token)); // TODO : ReponseEntity.ok()
  }

  // DTO
  @Data
  class JwtRequest {
    private String email;

    private String password;
  }

  @Data
  @AllArgsConstructor
  class JwtResponse {
    private String token;
  }
}
