<%-- 
    Document   : products
    Created on : Sep 24, 2016, 4:47:00 PM
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

        <h2>Products</h2>

        <table>
            <tr>
                <th>Code</th>
                <th>Description</th>
                <th class="right">Price</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>

            <c:forEach var="element" items="${products}">
                <tr>

                    <td><c:out value="${element.code}"/></td>
                    <td><c:out value="${element.description}"/></td>
                    <td><c:out value="${element.priceCurrencyFormat}"/></td>
                    <td>
                        <a href="/Project_Group_20_P2/productManagement?action=displayProduct&amp;productCode=${element.code}">Edit</a>
                    </td>
                    <td>
                        <a href="/Project_Group_20_P2/productManagement?action=deleteProduct&amp;productCode=${element.code}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <%--
        <table>
            <tr>
                <th style="font-weight: bold">Code</th>
                <th style="font-weight: bold">Description</th>
                <th style="font-weight: bold">Price</th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <th>8601</th>
                <th>86 (the band) True Life Songs and Pictures</th>
                <th>$15.95</th>
                <th><a href="product.jsp">Edit</a></th>
                <th><a href="confirmDelete.jsp">Delete</a></th>
            </tr>
            <tr>
                <th>pf01</th>
                <th>Paddlefoot - The first CD</th>
                <th>$12.95</th>
                <th><a href="product.jsp">Edit</a></th>
                <th><a href="confirmDelete.jsp">Delete</a></th>
            </tr>
            <tr>
                <th>pf02</th>
                <th>Paddlefoot - The second CD</th>
                <th>$14.95</th>
                <th><a href="product.jsp">Edit</a></th>
                <th><a href="confirmDelete.jsp">Delete</a></th>
            </tr>
            <tr>
                <th>jr01</th>
                <th>Joe Rut - Genuine Wood Grained Finish</th>
                <th>$14.95</th>
                <th><a href="product.jsp">Edit</a></th>
                <th><a href="confirmDelete.jsp">Delete</a></th>
            </tr>
        </table>
        --%>
        <form action="/Project_Group_20_P2/productManagement" method="get">
            <input type="hidden" name="action" value="addProduct">
            <p><input type="submit" value="Add Product"></p>
        </form>

        <p>
            <a href='index.jsp'>Back to Index</a>
        </p>

    </body>
</html>
