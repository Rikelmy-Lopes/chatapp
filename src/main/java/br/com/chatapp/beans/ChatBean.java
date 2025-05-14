package br.com.chatapp.beans;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.SessionFactory;

import br.com.chatapp.model.User;
import br.com.chatapp.service.ChatWebSocketService;

@Named("chatBean")
@ViewScoped
public class ChatBean implements Serializable {
  private static final long serialVersionUID = 1L;

  @Inject private ChatWebSocketService chatWebSocketService;
  @Inject FacesContext facesContext;
  @Inject SessionFactory sessionFactory;
  private String message = "";

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isButtonSendDisabled() {
    return !(this.message.length() > 0);
  }

  public void send() {
    User user = (User) this.facesContext.getExternalContext().getSessionMap().get("user");

    this.chatWebSocketService.sendAll(user.getName(), this.message);
    this.message = "";
  }
}
