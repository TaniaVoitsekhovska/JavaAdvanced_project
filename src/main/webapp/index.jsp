<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <title>Login</title>

    <link rel="stylesheet" href="css/login.css">

</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<form action="register" method="post">

    <label for="firstName">First Name :</label> <input name="firstName">
    <br>
    <label for="lastName">Last Name :</label> <input name="lastName">
    <br>
    <label for="email">Email :</label> <input name="email">
    <br>
    <label for="password">Password : </label> <input name="password">
    <br>
    <input type="submit" value="submit">
</form>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>