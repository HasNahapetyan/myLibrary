<%@ page import="com.example.myLibrary.model.Author" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
</head>
<%List<Author> authors = (List<Author>) request.getAttribute("authors"); %>
<body>
<a href="/books">Back</a>
<h2>Create Book</h2>
<form action="/createBook" method="post">
    title: <input type="text" name = "title"><br>
    description: <input type="text" name = "description"><br>
    price: <input type="text" name = "price"><br>
    Choose an Auther:
    <select name="authorId">
        <%if (authors != null && !authors.isEmpty())
            for(Author author: authors){%>
        <option value=<%=author.getId()%>><%=author.getName()%></option>
        <%}%>
    </select>
    <br>
    <input type="submit" value="create">
</form>
</body>
</html>
