package ru.sertok.hibernate.servlets;

import ru.sertok.hibernate.dao.impl.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users",userDao.findAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/users.jsp");
        dispatcher.forward(req,resp);
    }
}
