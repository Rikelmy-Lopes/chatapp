package br.com.chatapp.websocket;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import br.com.chatapp.db.MessageDB;
import br.com.chatapp.dto.MessageBodyDTO;
import br.com.chatapp.util.JsonParserUtil;
import br.com.chatapp.websocket.dto.WebSocketDTO;
import br.com.chatapp.websocket.event.WebsocketEventType;
import br.com.chatapp.websocket.session.UsersSession;

@ServerEndpoint("/websocket/chat")
public class ChatWebSocket implements Serializable {

  private static final long serialVersionUID = 1L;

  private JsonParserUtil jsonParser = new JsonParserUtil();
  private UsersSession usersSession = UsersSession.getInstance();
  private MessageDB messageDB = MessageDB.getInstance();

  @OnOpen
  public void onOpen(Session session) {
    this.usersSession.add(session);

    WebSocketDTO<List<MessageBodyDTO>> webSocketEvent =
        new WebSocketDTO<>(WebsocketEventType.NEW_CONNECTION, this.messageDB.getAll());
    String json = this.jsonParser.objectToString(webSocketEvent);
    this.send(json, session);
  }

  @OnClose
  public void onClose(Session session) {
    this.usersSession.remove(session);
  }

  public void send(String json, Session session) {
    try {
      session.getBasicRemote().sendText(json);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void sendAll(String json) {
    for (Session session : this.usersSession.getAll()) {
      try {
        session.getBasicRemote().sendText(json);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
