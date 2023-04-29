<%@ page import="com.example.myLibrary.model.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.myLibrary.model.User" %>
<%@ page import="com.example.myLibrary.model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Author</title>
</head>
<%List<Author> authors = (List<Author>) request.getAttribute("authors");
  User user = (User) session.getAttribute("user");
%>
<body>
<a href="/home">Back</a>
<h2>Author</h2><a href="/createAuthor">Add Author</a>
<table border="2">
  <tr>
    <th>id</th>
    <th>name</th>
    <th>surname</th>
    <th>email</th>
    <th>age</th>
    <% if (user.getUserType() == UserType.ADMIN) { %>
    <th>action</th>
    <%}%>
  </tr>
  <%if (authors != null && !authors.isEmpty())
      for(Author author: authors){%>
        <tr>
          <td><%=author.getId()%></td>
          <td><%=author.getName()%></td>
          <td><%=author.getSurname()%></td>
          <td><%=author.getEmail()%></td>
          <td><%=author.getAge()%></td>
          <% if (user.getUserType() == UserType.ADMIN) { %>

          <td><a href="/removeAuthor?id=<%=author.getId()%>">Delete</a></td>
          <%}%>
        </tr>
      <%}%>
</table>
</body>
</html>
