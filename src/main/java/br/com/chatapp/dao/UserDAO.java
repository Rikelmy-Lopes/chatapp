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

  public User findByUsername(String username) {
    Session session = this.sessionFactory.openSession();
    try {
      Query<User> query =
          session.createQuery("from User u where u.username = :pUsername", User.class);
      query.setParameter("pUsername", username);

      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    } finally {
      session.close();
    }
  }

  public User save(User user) {
    Session session = this.sessionFactory.openSession();
    session.beginTransaction();
    session.save(user);
    session.getTransaction().commit();
    session.close();
    return user;
  }
}
