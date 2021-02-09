package edu.security.second.Exception;

public class UserNotFoundException extends Exception{
  public UserNotFoundException(String email) {
    super(email+" was not found");
  }
}
