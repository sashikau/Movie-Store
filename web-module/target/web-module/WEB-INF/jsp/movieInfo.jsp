<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shashika
  Date: 8/20/15
  Time: 12:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Movie Info</title>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <style>
  .table-nonfluid {
  width: auto !important;
  margin-left: 25px;
  }
  </style>
</head>
<body>
<h1>Movie Information</h1>
<table <%--border="1"--%> class= "table table-hover table-nonfluid"<%--style="width: auto;"--%>>
  <thead>
  <tr>
    <th>Movie Name</th>
    <th>Rating</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${list}" var="entry">
    <tr>
      <td>${entry.getName()}</td>
      <td>${entry.getRating()}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<br><br>
<a href="addInfo"><button type="button">Back to add information</button></a>
</body>
</html>
