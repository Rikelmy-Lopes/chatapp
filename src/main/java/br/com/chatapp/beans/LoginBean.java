package br.com.chatapp.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.chatapp.dao.UserDAO;
import br.com.chatapp.model.User;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private String email;
  private String password;
  @Inject private UserDAO userDAO;
  @Inject private FacesContext facesContext;

  public String login() {
    User user = this.userDAO.findByEmailAndPassword(this.email, this.password);

    if (user != null) {
      HttpSession httpSession =
          (HttpSession) this.facesContext.getExternalContext().getSession(false);
      httpSession.setAttribute("user", user);
      return "chat?faces-redirect=true";
    } else {
      return "login";
    }
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
