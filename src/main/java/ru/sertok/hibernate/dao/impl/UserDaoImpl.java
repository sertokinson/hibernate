package ru.sertok.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sertok.hibernate.dao.api.UserDao;
import ru.sertok.hibernate.models.User;
import ru.sertok.hibernate.repository.DateBaseRepository;
import ru.sertok.hibernate.utils.Utils;

import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private DateBaseRepository sessionFactory;

    private String[] mutableHash = new String[1];


    @Override
    public void save(User user) {
        try (Session session = sessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public Optional<User> find(Integer id) {
        try (Session session = sessionFactory.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User user where user.id=:id", User.class);
            query.setParameter("id", id);
            return Optional.of(query.getSingleResult());
        }
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User user", User.class);
            return query.getResultList();
        }
    }

    @Override
    public Boolean isExist(String name, String password) {
        try (Session session = sessionFactory.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User user where user.name=:name", User.class);
            query.setParameter("name", name);
            List<User> users = query.getResultList();
            for (User user : users) {
                if (user.getName().equals(name) && Utils.verifyAndUpdateHash(password, user.getPassword(), hash -> {
                    mutableHash[0] = hash;
                    return true;
                }))
                    return true;
            }
            return false;
        }
    }


}
