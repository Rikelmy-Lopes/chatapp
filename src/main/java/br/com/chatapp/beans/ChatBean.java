package br.com.chatapp.beans;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.chatapp.service.ChatWebSocketService;

@Named("chatBean")
@ViewScoped
public class ChatBean implements Serializable {
  @Inject private ChatWebSocketService chatWebSocketService;
  @Inject FacesContext facesContext;
  private String name = "";
  private String message = "";

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isButtonSendDisabled() {
    return !(this.name.length() > 2 && this.message.length() > 0);
  }

  public void send() {
    this.chatWebSocketService.sendAll(this.name, this.message);
    this.message = "";
  }
}
