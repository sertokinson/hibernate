package ru.sertok.hibernate.servlets;

import ru.sertok.hibernate.dao.impl.UserDao;
import ru.sertok.hibernate.models.User;
import ru.sertok.hibernate.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = Utils.decode(req.getParameter("name"));
        String password = req.getParameter("password");
        Date birthDate = Date.valueOf(req.getParameter("birthDate"));
        userDao.save(new User()
                .withName(name).withPassword(Utils.hash(password)).withBirthDate(birthDate)
        );
        resp.sendRedirect(req.getContextPath() + "/users");
    }


}
