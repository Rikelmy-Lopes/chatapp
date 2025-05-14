package br.com.chatapp.dao;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import br.com.chatapp.model.User;

public class UserDAO implements Serializable {

  private static final long serialVersionUID = 1L;
  @Inject private SessionFactory sessionFactory;

  public User findByEmailAndPassword(String email, String password) {
    Session session = this.sessionFactory.openSession();
    Query<User> query =
        session.createQuery(
            "from User u where u.email = :pEmail and u.password = :pPassword", User.class);

    query.setParameter("pEmail", email);
    query.setParameter("pPassword", password);

    return query.getSingleResult();
  }
}
