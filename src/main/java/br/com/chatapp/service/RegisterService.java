package br.com.chatapp.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.chatapp.dao.UserDAO;
import br.com.chatapp.model.Result;
import br.com.chatapp.model.User;

public class RegisterService implements Serializable {

  private static final long serialVersionUID = 1L;
  @Inject UserDAO userDAO;

  public Result<User, String> registerUser(
      String name, String username, String email, String password) {

    if (this.userDAO.findByEmail(email) != null) {
      return Result.fail("Esse email ja foi cadastrado!");
    }
    if (this.userDAO.findByUsername(username) != null) {
      return Result.fail("Esse username ja foi utilizado!");
    }

    User user = this.userDAO.save(new User(name, username, email, password));
    return Result.ok(user);
  }
}
