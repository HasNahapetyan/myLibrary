<%@ page import="com.example.myLibrary.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.myLibrary.model.User" %>
<%@ page import="com.example.myLibrary.model.UserType" %>
<%@ page import="com.example.myLibrary.constants.SharedConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<%List<Book> books = (List<Book>) request.getAttribute("books");
    User user = (User) session.getAttribute("user");
    String keyword = request.getParameter("keyword") == null
            || request.getParameter("keyword").equals("null")?"":request.getParameter("keyword");
%>
<body>
<a href="/home">Back</a>

<h2>Books</h2>
<form action="/books" method="get">
    Search Book by Title:
    <input name="keyword" type="text" value="<%=keyword%>">
    <input type="submit" value="search">
</form>
<a href="/createBook">Add Book</a>
<table border="2">
    <tr>
        <th>image</th>
        <th>title</th>
        <th>description</th>
        <th>price</th>
        <th>Author id</th>
        <th>User id</th>
        <% if (user.getUserType() == UserType.ADMIN) { %>
        <th>action</th>
        <%}%>
    </tr>
    <%if (books != null && !books.isEmpty())
        for(Book book: books){%>
    <tr>
        <td><% if (book.getImageName() == null || book.getImageName().equalsIgnoreCase("null")) { %>
            <img src="/img/book_icon.png" width="100">
            <%} else {%>
            <a href="/getImage?imageName=<%=book.getImageName()%>">
            <img src="/getImage?imageName=<%=book.getImageName()%>" width="150"></a>
            <%}%>
        </td>
        <td><%=book.getTitle()%></td>
        <td><%=book.getDescription()%></td>
        <td><%=book.getPrice()%></td>
        <td><%=book.getAuthor().getId()%></td>
        <td><%=book.getUser().getId()%></td>
        <% if (user.getUserType() == UserType.ADMIN) { %>

        <td><a href="/removeBook?id=<%=book.getId()%>">Delete</a></td>
        <%}%>
    </tr>
    <%}%>
</table>
</body>
</html>
