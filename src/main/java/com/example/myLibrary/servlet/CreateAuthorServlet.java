package com.example.myLibrary.servlet;

import com.example.myLibrary.manager.AuthorManager;
import com.example.myLibrary.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createAuthor")
public class CreateAuthorServlet extends HttpServlet {
    private AuthorManager authorManager = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/createAuthor.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        Author author = new Author();
        author.setName(name);
        author.setSurname(surname);
        author.setEmail(email);
        author.setAge(age);
        authorManager.save(author);
        resp.sendRedirect("/authors");
    }
}
