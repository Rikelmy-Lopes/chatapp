package br.com.chatapp.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.chatapp.dto.MessageBodyDTO;

@ApplicationScoped
public class MessageDB implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final MessageDB INSTANCE = new MessageDB();
  private List<MessageBodyDTO> messages = new ArrayList<>();

  public static MessageDB getInstance() {
    return INSTANCE;
  }

  public boolean add(MessageBodyDTO message) {
    return INSTANCE.messages.add(message);
  }

  public List<MessageBodyDTO> getAll() {
    return INSTANCE.messages;
  }
}
