<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="Create" method="get" align="center" autocomplete="off">
    <p>
    <h1> Add client address</h1></p>
    <p>
        <input type="hidden" name="clientId" value="<%= request.getParameter("hidden") %>">
    </p>
    <table align="center" cellpadding="5">
        <tr>
            <td><label>IP-адрес (IPv4)</label></td>
            <td><input type="text" name="ip"
                       pattern="^(([01]?\d?\d|2[0-4]\d|25[0-5])\.){3}([01]?\d?\d|2[0-4]\d|25[0-5])$"
                       required autocomplete="off" title="пример: 255.255.255.255"></td>
        </tr>
        <tr>
            <td><label>Mac-адрес</label></td>
            <td><input type="text" name="mac" pattern="^([0-9A-Za-z]{2}[-]){5}([0-9A-Za-z]{2})$"
                       required autocomplete="off" title="пример: 77-6s-52-7f-ja-4h"></td>
        </tr>
        <tr>
            <td><label>Model</label></td>
            <td><input type="text" name="model" required autocomplete="off"></td>
        </tr>
        <tr>
            <td><label>Address</label></td>
            <td><input type="text" name="address" required autocomplete="off"></td>
        </tr>
    </table>
    <p><input type="submit" value="Save"></p>
</form>
</body>
</html>
