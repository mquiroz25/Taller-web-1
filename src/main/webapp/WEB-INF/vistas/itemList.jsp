<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Rocio
  Date: 01/06/2019
  Time: 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<c:choose>
    <c:when test="${empty items}">
        <h4><span>No hay resultados</span></h4>
        <br />
    </c:when>
    <c:otherwise>
        <c:forEach items="${items}"  var="ListItem">
            
        </c:forEach>
        <br />
    </c:otherwise>
</c:choose>
</body>
</html>
