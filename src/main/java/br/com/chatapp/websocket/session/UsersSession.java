package br.com.chatapp.websocket.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

@ApplicationScoped
public class UsersSession implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final UsersSession INSTANCE = new UsersSession();
  private List<Session> sessions = new ArrayList<>();

  public static UsersSession getInstance() {
    return INSTANCE;
  }

  public boolean add(Session session) {
    return this.sessions.add(session);
  }

  public boolean remove(Session session) {
    return this.sessions.remove(session);
  }

  public List<Session> getAll() {
    return this.sessions;
  }
}
