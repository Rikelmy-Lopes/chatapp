package br.com.chatapp.service;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import javax.inject.Inject;

import br.com.chatapp.db.MessageDB;
import br.com.chatapp.dto.MessageBody;
import br.com.chatapp.util.JsonParser;
import br.com.chatapp.websocket.ChatWebSocket;
import br.com.chatapp.websocket.event.WebsocketEventType;
import br.com.chatapp.websocket.model.WebSocketEvent;

public class ChatWebSocketService implements Serializable {
  @Inject private ChatWebSocket socket;
  @Inject private MessageDB messageDB;
  private JsonParser jsonParser = new JsonParser();

  public void sendAll(String name, String message) {
    List<MessageBody> messages = this.messageDB.getAll();
    MessageBody messageBody = new MessageBody(name, message, LocalTime.now());
    messages.add(messageBody);

    WebSocketEvent<MessageBody> we =
        new WebSocketEvent<>(WebsocketEventType.NEW_MESSAGE, messageBody);

    String json = this.jsonParser.objectToString(we);
    this.socket.sendAll(json);
  }
}
