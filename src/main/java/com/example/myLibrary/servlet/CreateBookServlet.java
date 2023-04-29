package com.example.myLibrary.servlet;

import com.example.myLibrary.manager.AuthorManager;
import com.example.myLibrary.manager.BookManager;
import com.example.myLibrary.model.Author;
import com.example.myLibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/createBook")
public class CreateBookServlet extends HttpServlet {

    private BookManager bookManager = new BookManager();
    private AuthorManager authorManager = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> all = authorManager.getAll();
        req.setAttribute("authors", all);
        req.getRequestDispatcher("WEB-INF/createBook.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setPrice(price);
        book.setAuthor(authorManager.getById(authorId));
        bookManager.save(book);
        resp.sendRedirect("/books");
    }
}
