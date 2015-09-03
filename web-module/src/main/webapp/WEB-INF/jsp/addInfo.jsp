<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--
  Created by IntelliJ IDEA.
  User: shashika
  Date: 8/20/15
  Time: 5:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add Info</title>

  <style>
    label{
      display:inline-block;
      float: left;
      padding-top: 5px;
      text-align: left;
      width: 140px;
    }

     .error
     {
       color: chocolate;
       font-weight: bold;
       font-size: 12px;

     }

  </style>
</head>
<body>
<h1>Add Movie Information</h1>

<form:form commandName="movieForm" modelAttribute="movieForm" method="post">

  <label >Name </label>
  <form:input path="name" placeholder="Movie Name"/>
  <form:errors path="name" cssClass="error"/>
  <br>
  <label >Rating</label>
  <form:input path="rating" placeholder="Rating"/>
    <form:errors path="rating" cssClass="error"/>

  <br><br>
  <input type="submit" value="Submit">
  <br><br>
  <a href="movieInfo"><button type="button">Show movie information</button></a>
</form:form>



</body>
</html>
