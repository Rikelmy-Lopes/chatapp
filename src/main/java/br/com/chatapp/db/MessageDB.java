package br.com.chatapp.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;

import br.com.chatapp.dto.MessageBody;

@ApplicationScoped
public class MessageDB implements Serializable {
  private static final MessageDB INSTANCE = new MessageDB();
  private List<MessageBody> messages = new ArrayList<>();

  public static MessageDB getInstance() {
    return INSTANCE;
  }

  public boolean add(MessageBody message) {
    return INSTANCE.messages.add(message);
  }

  public List<MessageBody> getAll() {
    return INSTANCE.messages;
  }
}
