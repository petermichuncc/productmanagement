<%-- 
    Document   : confirmDelete
    Created on : Sep 24, 2016, 3:02:59 PM
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

        <h2>Are you sure you want to delete this product?</h2>

        <section>
           Code:      <c:out value="${product.code}"/>  <br>
            Description:    <c:out value="${product.description}"/>  <br>
           Price:      $<c:out value="${product.price}"/> <br>

            <%-- Form to Delete the product --%>
            <form action="/Project_Group_20_P2/productManagement" method="get"> 
                <input type="hidden" name="action" value="actuallyDelete">
                <input type="hidden" name="productCode" value="<c:out value="${product.code}"/>">
                <input type="submit" value="Yes" id="button1">
            </form>

            <%-- Form to go back to product display the product --%>
            <form action="/Project_Group_20_P2/productManagement" method="get"">       
                <input type="hidden" name="action" value="displayProducts">
                <input type="submit" value = "No" id="button1">
            </form>


        </section>    
    </body>
</html>
