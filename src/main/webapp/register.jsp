<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Register Page</title>
</head>
<body>
<form action="/register" method="get">
  name:<input name="name" type="text"><br>
  surname: <input name="surname" type="text"><br>
  email: <input name="email" type="text"><br>
  password: <input name="password" type="password"><br>
  type: <select name="type">
    <option value="ADMIN">Admin</option>
    <option value="USER">User</option>
  </select>
  <input type="submit" value="register"><br>

</form>
<a href="/">Back</a>
</body>
</html>