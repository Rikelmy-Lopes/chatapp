package br.com.chatapp.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.chatapp.model.Result;
import br.com.chatapp.model.User;
import br.com.chatapp.service.RegisterService;

@Named("registerBean")
@SessionScoped
public class RegisterBean implements Serializable {
  private static final long serialVersionUID = 1L;
  @Inject private FacesContext facesContext;
  @Inject private RegisterService registerService;

  private String name;
  private String username;
  private String email;
  private String password;
  private String confPassword;

  public String register() {
    if (!this.password.equals(this.confPassword)) {
      this.facesContext.addMessage(
          null,
          new FacesMessage(
              FacesMessage.SEVERITY_WARN,
              "Senhas diferentes!",
              "Ambas as senhas devem ser iguais!"));
      return "register";
    }

    Result<User, String> result =
        this.registerService.registerUser(this.name, this.username, this.email, this.password);

    if (result.isFailure()) {
      this.facesContext.addMessage(
          null,
          new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar", result.getError()));
      return "register";
    } else {
      HttpSession httpSession =
          (HttpSession) this.facesContext.getExternalContext().getSession(false);
      httpSession.setAttribute("user", result.getValue());
      return "chat?faces-redirect=true";
    }
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public String getConfPassword() {
    return this.confPassword;
  }

  public void setConfPassword(String confPassword) {
    this.confPassword = confPassword;
  }
}
