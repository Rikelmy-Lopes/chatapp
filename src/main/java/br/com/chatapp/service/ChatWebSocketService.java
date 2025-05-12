package br.com.chatapp.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.websocket.Session;

import br.com.chatapp.util.JsonParser;
import br.com.chatapp.websocket.ChatWebSocket;
import br.com.chatapp.websocket.model.WebSocketEvent;

public class ChatWebSocketService implements Serializable {
  @Inject private ChatWebSocket socket;
  private JsonParser jsonParser = new JsonParser();

  public void send(WebSocketEvent<?> we, Session session) {
    String json = this.jsonParser.objectToString(we);
    this.socket.send(json, session);
  }

  public void sendAll(WebSocketEvent<?> we) {
    String json = this.jsonParser.objectToString(we);
    this.socket.sendAll(json);
  }
}
