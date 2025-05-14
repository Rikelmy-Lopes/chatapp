package br.com.chatapp.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
  private static SessionFactory sessionFactory;

  private static SessionFactory buildSessionFactory() {
    try {
      SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

      return sessionFactory;
    } catch (Exception e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  @Produces
  @ApplicationScoped
  public static SessionFactory getSessionFactory() {
    if (HibernateUtil.sessionFactory == null) {
      HibernateUtil.sessionFactory = buildSessionFactory();
    }
    return sessionFactory;
  }
}
