package com.example.myLibrary.manager;


import com.example.myLibrary.db.DBConnectionProvider;
import com.example.myLibrary.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {

    private Connection connection = DBConnectionProvider.getINSTANCE().getConnection();
    private AuthorManager authorManager = new AuthorManager();

    private UserManager userManager = new UserManager();
    public void save(Book book) {
        try (Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO book(title,description,price,author_id,image_name,user_id) VALUES('%s','%s','%d',%d,'%s',%d)";
            statement.executeUpdate(String.format(sql, book.getTitle(), book.getDescription(), book.getPrice(),
                    book.getAuthor().getId(),book.getImageName(),book.getUser().getId()), Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }
            System.out.println("book inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> searchByTitle(String keyword) {
        List<Book> books = new ArrayList<>();
        try {
            String sql = "Select * from book where title like ?";
            keyword = "'%"+keyword+"%'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,keyword);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                books.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from book");
            while (resultSet.next()) {
                books.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setDescription(resultSet.getString("description"));
        book.setPrice(Integer.parseInt(resultSet.getString("price")));
        int authorId = resultSet.getInt("author_id");
        book.setAuthor(authorManager.getById(authorId));
        book.setImageName(resultSet.getString("image_name"));
        int userId = resultSet.getInt("user_id");
        book.setUser(userManager.getById(userId));
        return book;
    }


    public void removeById(int bookId) {
        String sql = "DELETE FROM book WHERE id = " + bookId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}