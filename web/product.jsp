<%-- 
    Document   : product
    Created on : Sep 24, 2016, 4:53:47 PM
    Author     : Andrew Legra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="commonStyles.css">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>Product Management</title>
    </head>
    <body>
        <c:if test="${user.firstName != null}">
            <p>You are currently logged in as: <c:out value="${user.firstName} ${user.lastName}"/></p>
            <a href="/Project_Group_20_P2/membership?action=logout">User Logout</a>
        </c:if>

        <h2>Product</h2>

        <form action="/Project_Group_20_P2/productManagement" method="post">
            <p>    
                <label class="leftHeading">Code</label>
                <input type="text" name="code" value="<c:out value="${product.code}"/>">
            </p>

            <p>
                <label class="leftHeading">Description</label>
                <textarea rows="4" cols="25" name="desc"><c:out value = "${product.description}"/></textarea>
            </p>

            <p>
                <label class="leftHeading">Price</label>
                <input type="text" name="price" value="<c:out value="${product.price}"/>">
            </p>

            <div class="rightButton">
                <input type="hidden" name="action" value="updateProduct">
                <input type="hidden" name="productCode" value="<c:out value="${product.code}"/>">           
                <input type="submit" value="Update Product">

            </div>
        </form>

        <form action="/Project_Group_20_P2/productManagement" method="get">
            <input type="hidden" name="action" value="displayProducts">
            <input type="submit" value ="View Products">
        </form>
    </body>
</html>
