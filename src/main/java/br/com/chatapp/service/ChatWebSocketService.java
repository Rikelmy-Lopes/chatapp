package br.com.chatapp.service;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import javax.inject.Inject;

import br.com.chatapp.db.MessageDB;
import br.com.chatapp.dto.MessageBodyDTO;
import br.com.chatapp.util.JsonParserUtil;
import br.com.chatapp.websocket.ChatWebSocket;
import br.com.chatapp.websocket.dto.WebSocketDTO;
import br.com.chatapp.websocket.event.WebsocketEventType;

public class ChatWebSocketService implements Serializable {
  private static final long serialVersionUID = 1L;
  @Inject private ChatWebSocket socket;
  @Inject private MessageDB messageDB;
  @Inject private JsonParserUtil jsonParser;

  public void sendAll(String name, String message) {
    List<MessageBodyDTO> messages = this.messageDB.getAll();
    MessageBodyDTO messageBody = new MessageBodyDTO(name, message, LocalTime.now());
    messages.add(messageBody);

    WebSocketDTO<MessageBodyDTO> we =
        new WebSocketDTO<>(WebsocketEventType.NEW_MESSAGE, messageBody);

    String json = this.jsonParser.objectToString(we);
    this.socket.sendAll(json);
  }
}
