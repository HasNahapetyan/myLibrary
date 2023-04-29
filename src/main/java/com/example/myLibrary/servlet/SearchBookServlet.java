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

@WebServlet("/searchBook")
public class SearchBookServlet extends HttpServlet {
    private BookManager bookManager = new BookManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String subTitle = req.getParameter("inputtedSubTitle");
        List<Book> allResults = bookManager.getAllByTitle(subTitle);
        req.setAttribute("books",allResults);
        req.getRequestDispatcher("WEB-INF/books.jsp").forward(req,resp);
    }
}
