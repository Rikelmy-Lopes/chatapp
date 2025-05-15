package br.com.chatapp.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("registerBean")
@ViewScoped
public class RegisterBean implements Serializable {
  private static final long serialVersionUID = 1L;
  @Inject FacesContext facesContext;

  private String name;
  private String username;
  private String email;
  private String password;
  private String confPassword;

  public void register() {
    if (!this.password.equals(this.confPassword)) {
      this.facesContext.addMessage(
          null,
          new FacesMessage(
              FacesMessage.SEVERITY_WARN,
              "Senhas diferrentes!",
              "Ambas as senhas devem ser iguais!"));
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
