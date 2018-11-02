package ru.sertok.hibernate.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.sertok.hibernate.dao.api.UserDao;
import ru.sertok.hibernate.models.User;
import ru.sertok.hibernate.utils.Utils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    @Autowired
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
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
        userDao.save(
                User.builder()
                        .name(name)
                        .password(Utils.hash(password))
                        .birthDate(birthDate).build()
        );
        resp.sendRedirect(req.getContextPath() + "/users");
    }


}
