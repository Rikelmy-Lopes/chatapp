package br.com.chatapp.beans;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.chatapp.db.MessageDB;
import br.com.chatapp.dto.MessageBody;
import br.com.chatapp.service.ChatWebSocketService;
import br.com.chatapp.websocket.event.WebsocketEventType;
import br.com.chatapp.websocket.model.WebSocketEvent;

@Named("chat")
@ViewScoped
public class Chat implements Serializable {
  @Inject private ChatWebSocketService chatWebSocketService;
  @Inject private MessageDB messageDB;
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

  public void enviar() {
    List<MessageBody> messages = this.messageDB.getAll();
    MessageBody message = new MessageBody(this.name, this.message, LocalTime.now());
    messages.add(message);

    this.chatWebSocketService.sendAll(
        new WebSocketEvent<MessageBody>(WebsocketEventType.NEW_MESSAGE, message));
    this.message = "";
  }
}
