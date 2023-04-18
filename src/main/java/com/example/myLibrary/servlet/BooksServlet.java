package com.example.myLibrary.servlet;

import com.example.myLibrary.manager.BookManager;
import com.example.myLibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {

    private BookManager bookManager = new BookManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Book> all = null;
        if (keyword == null || keyword.equals("")) {
            all = bookManager.getAll();
        }else{
            all = bookManager.searchByTitle(keyword);
        }
        req.setAttribute("books",all);
        req.getRequestDispatcher("WEB-INF/books.jsp").forward(req,resp);
    }
}
