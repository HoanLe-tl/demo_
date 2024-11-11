<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<br/>
<form action="/add" method="post">
<table>
    <tr>
        <td>Search</td>
        <td><input type="text" name="search"></td>
        <td>
            <select name="loai">
                <option>id</option>
                <option>fullName</option>
            </select>
        </td>
        <td>
            <input type="submit" value="Search" formaction="/search" formmethod="post">
        </td>
    </tr>
    <tr>
        <td><label>Id</label></td>
        <td><input type="text" name="id" value="${user.id}"></td>
    </tr>
    <tr>
        <td><label>Full Name</label></td>
        <td><input type="text" name="fullName" value="${user.fullName}"></td>
    </tr>
    <tr>
        <td><label>Email</label></td>
        <td><input type="text" name="email" value="${user.email}"></td>
    </tr>
    <tr>
        <td><label>Password</label></td>
        <td><input type="text" name="password" value="${user.password}"></td>
    </tr>
    <tr>
        <td><label>Admin</label></td>
        <td><label><input type="radio"  name="admin"  value="true"  ${user.admin ? 'checked' :""}>Quản Lí</label></td>
        <td><label><input type="radio"  name="admin"  value="false" ${!user.admin ? 'checked' :""}>Nhân viên</label></td>
    </tr>
    <tr>
        <td>
            <input type="submit" value="Add">
        </td>
        <td>
            <input type="submit" value="Update" formaction="/update" onclick="return confirm('Ban co muon sua hoac them khong ?')">
        </td>
    </tr>
</table>
</form>
    <table border="1" class="table table-bordered">
        <tr>
            <th>STT</th>
            <th>Id</th>
            <th>Password</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Admin</th>
        </tr>
        <c:forEach var="temp" items="${list}" varStatus="i">
            <tr>
                <td>${i.index + 1}</td>
                <td>${temp.id}</td>
                <td>${temp.password}</td>
                <td>${temp.fullName}</td>
                <td>${temp.email}</td>
                <td>${temp.admin == "true" ?"Quan Ly" : "Nhan Vien"}</td>
                <td>
                    <a href="/detail?id=${temp.id}" >Detail</a>
                    ||
                    <a href="/delete?id=${temp.id}" onclick="return confirm('Ban co muon xoa khong ?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>