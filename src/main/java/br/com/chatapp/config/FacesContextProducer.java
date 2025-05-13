package br.com.chatapp.config;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesContextProducer implements Serializable {

  @RequestScoped
  @Produces
  public FacesContext getFacesContext() {
    return FacesContext.getCurrentInstance();
  }
}
