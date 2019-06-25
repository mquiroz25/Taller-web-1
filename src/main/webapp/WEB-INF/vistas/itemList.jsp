<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <meta charset="ISO-8859-1">
</head>
<body>
<div class="container">
    <br>
    <c:choose>
    <c:when test="${items.isEmpty()}">
        <h4><span>No hay resultados</span></h4>
    </c:when>
    <c:otherwise>

    <div id="tablaLista">
        <table class="table table-bordered table-hover ">
            <tr class="table-primary">
                <th>Foto</th>
                <th>Producto</th>
                <th></th>
            </tr>

            <c:forEach items="${items}" var="item">
                <tr>
                    <td><img src="${item.urlImage}" width="50"></td>
                    <td>${item.brand} </td>
                    <td><form:form action="productDetail" method="POST" modelAttribute="message">
                        <form:input path="idItem" id="idItem" value="${item.id}" type="hidden"/>
                        <input type="submit" value="Ver detalles" class="btn btn-primary"/>
                    </form:form></td>

                </tr>
            </c:forEach>
        </table>
        </div>
        </c:otherwise>
        </c:choose>

    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</body>
</html>