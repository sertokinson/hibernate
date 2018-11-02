package ru.sertok.hibernate.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.sertok.hibernate.models.User;

@Component
public class DateBaseRepository {
    private SessionFactory sessionFactory;

    public DateBaseRepository() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.configure();
        return sessionFactory;
    }
}
