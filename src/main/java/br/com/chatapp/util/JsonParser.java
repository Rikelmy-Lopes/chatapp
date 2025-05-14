package br.com.chatapp.util;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ApplicationScoped
public class JsonParser implements Serializable {

  private static final long serialVersionUID = 1L;
  private ObjectMapper om = new ObjectMapper().registerModule(new JavaTimeModule());
  private ObjectWriter ow = this.om.writer().withDefaultPrettyPrinter();

  public String objectToString(Object object) {
    try {
      return this.ow.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public <T> T stringToObject(String json, Class<T> valueType) {
    try {
      return this.om.readValue(json, valueType);
    } catch (JsonMappingException e) {
      throw new RuntimeException(e);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
