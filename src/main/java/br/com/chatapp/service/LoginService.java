package br.com.chatapp.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.chatapp.dao.UserDAO;
import br.com.chatapp.model.Result;
import br.com.chatapp.model.User;
import br.com.chatapp.security.BcryptUtil;

public class LoginService implements Serializable {
  private static final long serialVersionUID = 1L;
  @Inject private UserDAO userDAO;
  @Inject private BcryptUtil bcryptUtil;

  public Result<User, String> verifyCredentials(String email, String password) {
    User user = this.userDAO.findByEmail(email);

    if (user == null) {
      return Result.fail("Usuario não encontrado!");
    }

    if (!this.bcryptUtil.verifyPassword(password, user.getPassword())) {
      return Result.fail("Senha incorreta!");
    }

    return Result.ok(user);
  }
}
