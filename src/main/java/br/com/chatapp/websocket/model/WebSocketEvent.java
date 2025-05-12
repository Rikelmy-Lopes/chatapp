package br.com.chatapp.websocket.model;

import br.com.chatapp.websocket.event.WebsocketEventType;

public class WebSocketEvent<T> {
  private WebsocketEventType eventType;
  private T data;

  public WebSocketEvent(WebsocketEventType eventType, T data) {
    this.eventType = eventType;
    this.data = data;
  }

  public WebsocketEventType getEventType() {
    return this.eventType;
  }

  public void setEvent(WebsocketEventType eventType) {
    this.eventType = eventType;
  }

  public T getData() {
    return this.data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
