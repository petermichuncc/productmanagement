<%-- 
    Document   : index
    Created on : Oct 27, 2016, 7:52:53 PM
    Author     : Andrew Legra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="commonStyles.css">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>Project Phase 2</title>
    </head>
    <body>
        <c:if test="${user.firstName != null}">
            <p>You are currently logged in as: <c:out value="${user.firstName} ${user.lastName}"/></p>
            <a href="/Project_Group_20_P2/membership?action=logout">User Logout</a>
        </c:if>

        <h2>ITIS 4166 Project - Phase 2</h2>
        <h3>Andrew Legra & Peter Larrimore</h3>
        
        <h2>Products Page</h2>
        <p><a href="/Project_Group_20_P2/productManagement?action=displayProducts">Go to Products Page (productManagement?action=displayProducts)</a></p>
        
        <h2>Direct Links to JSP's (won't validate user)</h2>
        <p><a href="products.jsp">Products Page (products.jsp)</a></p>
        <p><a href="product.jsp">Product Page (product.jsp)</a></p>
        <p><a href="confirmDelete.jsp">Delete Product Page (confirmDelete.jsp)</a></p>
        <p><a href="login.jsp">Login Page (login.jsp)</a></p>
        <p><a href="signup.jsp">Signup Page (signup.jsp)</a></p>

        
    </body>
</html>
