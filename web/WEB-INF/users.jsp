<%-- 
    Document   : users
    Created on : Jul 6, 2023, 4:15:29 PM
    Author     : Victo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Users Page</title>
    </head>

    <body>
        <h1>Manage Users </h1>



        <br>
        <strong>Users</strong><table border="1">

            <tr>
                <td> Email</td>
                <td> First Name</td>
                <td> Last Name</td>
                <td>Edit</td>
                <td>Delete</td>
                       <td> Role</td>

            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>



<!--  <td><a href="/?action=edit&amp;user_name=${user.firstName}">edit</a></td> -->
                    <td><a href="/?action=edit_link&amp;users_email=${user.email}&amp;user_name=${user.firstName}">edit</a></td>
                    <td><a href="/?action=delete&amp;user_first_name=${user.firstName}&amp;user_last_name=${user.lastName}" >delete</a></td>
                    <c:forEach items="${roles}" var="role">

                        <td>${role.roleName}</td>

                    </c:forEach>

                </tr>
            </c:forEach>



        </table>

        <br>
        <p>${message}</p>


        <c:choose>
            <c:when test="${edit_table == true}">

                <h2>Edit User</h2>
                <form action="user" method="post">


                    <p>${email}</p>
                    First Name:  <input type="text" name="firstname"><br>
                    Last Name <input type="text" name="lastname"><br>  
                    Password:  <input type="text" name="password"><br>

                    <select name="roles">
                        <option name="option1" value="System Admin">System Admin</option>
                        <option name="option1" value="Regular User">Regular User</option>
                    </select><br>
                    <br>

                    <input type="hidden" name="action" value="edit">
                    <input type="submit" name="submit_edits_button" value="Edit User"><input type="submit" name="cancel_button" value="Cancel">
                </form>
                <p>${error}</p>

            </c:when>    
            <c:otherwise>
                <h2>Add User</h2>
                <form action="user" method="post">


                    Email: <input  type="text" name="email" value="${email}"><br> 
                    First Name:  <input type="text" name="firstname"><br>
                    Last Name <input type="text" name="lastname"><br>  
                    Password:  <input type="text" name="password"><br>

                    <select name="roles">
                        <option name="option1" value="System Admin">System Admin</option>
                        <option name="option1" value="Regular User">Regular User</option>
                    </select><br>
                    <br>

                    <input type="hidden" name="action" value="add">
                    <input type="submit" name="add_user_button" value="Add User">
                </form>
                <p>${error}</p>
            </c:otherwise>
        </c:choose>


















        <br>

    </body>
</html>
