package ru.sertok.hibernate.servlets;

import ru.sertok.hibernate.dao.impl.UserDaoJdbcTemplate;
import ru.sertok.hibernate.repository.ConnectionUserDao;
import ru.sertok.hibernate.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDaoJdbcTemplate userDao;

    @Override
    public void init() {
        ConnectionUserDao connection = new ConnectionUserDao();
        userDao = connection.getUserDaoJdbcTemplate();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = Utils.decode(req.getParameter("name"));
        String password = req.getParameter("password");
        if(userDao.isExist(name,password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user",name);
            resp.sendRedirect(req.getContextPath() + "/users");
        }
        doGet(req,resp);
    }
}
