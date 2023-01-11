package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.*;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;


public class UserDaoHibernateImpl implements UserDao {
    private static UserDaoHibernateImpl instance;
    private static final SessionFactory sessionFactory = getSessionFactory();
    private UserDaoHibernateImpl() {
    }

    public static UserDaoHibernateImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoHibernateImpl();
        }
        return instance;
    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        String sql = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, username VARCHAR(45), lastname VARCHAR(45), age INT )";
        Session session = sessionFactory.openSession();
        System.out.println("Session opened!");
        try {
            session.beginTransaction();
            transaction = session.getTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Something wrong while creating new table!");
        } finally {
            session.close();
            System.out.println("Session closed!");
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        System.out.println("Session opened!");
        try {
            session.beginTransaction();
            transaction = session.getTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Something wrong while deleting the table");
        } finally {
            session.close();
            System.out.println("Session closed!");
        }
    }

    @Override
    public void saveUser(User user) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        System.out.println("Session opened!");
        try {
            session.beginTransaction();
            transaction = session.getTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("В таблицу был добавлен User с именем " + user.getName());
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Something wrong while trying to add new user");
        } finally {
            session.close();
            System.out.println("Session closed!");
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        System.out.println("Session opened!");
        try {
            session.beginTransaction();
            transaction = session.getTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            System.out.println("User successfully removed, id - " + id);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getStackTrace();
            System.out.println("Something wrong while trying to delete user");
        } finally {
            session.close();
            System.out.println("Session closed!");
        }

    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        System.out.println("Session opened!");
        List<User> users = null;
        String hql = "FROM User";
        try {
            session.beginTransaction();
            transaction = session.getTransaction();
            Query query = session.createQuery(hql);
            users = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Something wrong while trying to get all users");
        } finally {
            session.close();
            System.out.println("Session closed!");

        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        System.out.println("Session opened!");
        String hql = "DELETE FROM User";
        try {
            session.beginTransaction();
            transaction = session.getTransaction();
            session.createQuery(hql).executeUpdate();
            transaction.commit();
            System.out.println("Cleaned the mess!");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Something wrong while trying to clean table!");
        } finally {
            session.close();
            System.out.println("Session closed!");
        }
    }
}
