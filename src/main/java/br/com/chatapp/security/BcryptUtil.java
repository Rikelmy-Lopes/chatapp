package br.com.chatapp.security;

import java.io.Serializable;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class BcryptUtil implements Serializable {
  private static final long serialVersionUID = 1L;

  public String hashPassword(String rawPassword) {
    return BCrypt.withDefaults().hashToString(12, rawPassword.toCharArray());
  }

  public boolean verifyPassword(String rawPassword, String hashedPassword) {
    return BCrypt.verifyer().verify(rawPassword.toCharArray(), hashedPassword).verified;
  }
}
