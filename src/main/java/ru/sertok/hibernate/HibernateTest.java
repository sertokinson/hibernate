package ru.sertok.hibernate;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sertok.hibernate.dao.api.UserDao;
import ru.sertok.hibernate.models.User;
import ru.sertok.hibernate.servlets.LoginServlet;

import java.util.List;

public class HibernateTest {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:\\context.xml");
        UserDao bean = context.getBean(UserDao.class);
        System.out.println("user = " + bean);
    }
}
