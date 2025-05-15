package br.com.chatapp.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import br.com.chatapp.model.User;

public class UserDAO implements Serializable {

  private static final long serialVersionUID = 1L;
  @Inject private SessionFactory sessionFactory;

  public User findByEmail(String email) {
    Session session = this.sessionFactory.openSession();
    try {
      Query<User> query = session.createQuery("from User u where u.email = :pEmail", User.class);
      query.setParameter("pEmail", email);

      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    } finally {
      session.close();
    }
  }
}
