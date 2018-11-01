package ru.sertok.hibernate.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sertok.hibernate.models.User;


public class DateBaseRepository {
    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
