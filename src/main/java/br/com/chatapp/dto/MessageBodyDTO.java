package br.com.chatapp.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class MessageBodyDTO {
  private String name;
  private String message;

  @JsonFormat(pattern = "HH:mm", shape = Shape.STRING)
  private LocalTime hour;

  public MessageBodyDTO() {}

  public MessageBodyDTO(String name, String message, LocalTime hour) {
    this.name = name;
    this.message = message;
    this.hour = hour;
  }

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

  public LocalTime getHour() {
    return this.hour;
  }

  public void setHour(LocalTime hour) {
    this.hour = hour;
  }
}
