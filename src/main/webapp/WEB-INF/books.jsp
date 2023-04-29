<%@ page import="com.example.myLibrary.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.myLibrary.model.User" %>
<%@ page import="com.example.myLibrary.model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<%List<Book> books = (List<Book>) request.getAttribute("books");
    User user = (User) session.getAttribute("user");%>

<body>
<a href="/home">Back</a>
<form action="/searchBook" method="get">
    Search Book by Title:<input name="inputtedSubTitle" type="text">
    <input type="submit" value="search">
</form>
<h2>Books</h2><a href="/createBook">Add Book</a>
<table border="2">
    <tr>
        <th>title</th>
        <th>description</th>
        <th>price</th>
        <th>Author id</th>
        <% if (user.getUserType() == UserType.ADMIN) { %>
        <th>action</th>
        <%}%>
    </tr>
    <%if (books != null && !books.isEmpty())
        for(Book book: books){%>
    <tr>
        <td><%=book.getTitle()%></td>
        <td><%=book.getDescription()%></td>
        <td><%=book.getPrice()%></td>
        <td><%=book.getAuthor().getId()%></td>
        <% if (user.getUserType() == UserType.ADMIN) { %>

        <td><a href="/removeBook?id=<%=book.getId()%>">Delete</a></td>
        <%}%>
    </tr>
    <%}%>
</table>
</body>
</html>
