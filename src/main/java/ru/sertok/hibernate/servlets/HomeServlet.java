package ru.sertok.hibernate.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String signIn = req.getParameter("signIn");
        String registration = req.getParameter("registration");
        if ("signIn".equals(signIn))
            resp.sendRedirect(req.getContextPath() + "/login");
        if ("registration".equals(registration))
            resp.sendRedirect(req.getContextPath() + "/signUp");
    }
}
