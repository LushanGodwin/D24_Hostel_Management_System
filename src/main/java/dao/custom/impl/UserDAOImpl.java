package dao.custom.impl;

import dao.custom.UserDAO;
import entity.Student;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean check(String userName, String password) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createNativeQuery("SELECT * FROM User WHERE userName = ?", User.class)
                .setParameter(1,userName)
                .getResultList();
        for (User user:userList) {
            transaction.commit();
            session.close();
            if (user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean save(User entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<User> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<User> userArrayList = session.createNativeQuery("SELECT * FROM User").addEntity(User.class).list();

        transaction.commit();
        session.close();
        return userArrayList;
    }

    @Override
    public boolean update(User student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(User entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(entity);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public String generateNextId() {
        return null;
    }

    @Override
    public User search(String id) {
        return null;
    }


}
