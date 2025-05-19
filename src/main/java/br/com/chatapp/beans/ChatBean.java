package br.com.chatapp.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.chatapp.model.User;
import br.com.chatapp.service.ChatWebSocketService;

@Named("chatBean")
@ViewScoped
public class ChatBean implements Serializable {
  private static final long serialVersionUID = 1L;

  @Inject private ChatWebSocketService chatWebSocketService;
  @Inject private FacesContext facesContext;
  private User user;
  private String message = "";

  @PostConstruct
  public void init() {
    this.user = (User) this.facesContext.getExternalContext().getSessionMap().get("user");
  }

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
    this.chatWebSocketService.sendAll(this.user.getName(), this.message);
    this.message = "";
  }
}
