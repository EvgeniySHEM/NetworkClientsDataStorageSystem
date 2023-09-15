<%--<%@ page import="ru.sanctio.model.dto.AddressDTO" %>--%>
<%--<% AddressDTO address = (AddressDTO) request.getAttribute("address"); %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Update client</title>
</head>
<body>
<form action="UpdateServlet" method="post" align="center" autocomplete="off">
    <p>
    <h1> Update client ${address.client().clientId()}</h1>
    </p>
    <p>
        <input type="hidden" name="clientId" value=${address.client().clientId()}>
        <input type="hidden" name="addressId" value=${address.id()}>
    </p>
    <table align="center" cellpadding="5">
        <tr>
            <td><label>Client name</label></td>
            <td><input type="text" name="clientName" value="${ address.client().clientName()}" pattern="^[а-яёА-ЯЁ{\-\s,.}]+$"
                       required autocomplete="off" title="Допустимо использование  только  русского  алфавита"></td>
        </tr>
        <tr>
            <td><label>IP-адрес (IPv4)</label></td>
            <td><input type="text" name="ip" value="${address.ip()}"
                    pattern="^(([01]?\d?\d|2[0-4]\d|25[0-5])\.){3}([01]?\d?\d|2[0-4]\d|25[0-5])$"
                       required autocomplete="off" title="пример: 255.255.255.255"></td>
        </tr>
        <tr>
            <td><label>Mac-адрес</label></td>
            <td><input type="text" name="mac" value="${address.mac()}"
                    pattern="^([0-9A-Za-z]{2}[-]){5}([0-9A-Za-z]{2})$"
                    required autocomplete="off" title="пример: 77-6s-52-7f-ja-4h"></td>
        </tr>
        <tr>
            <td><label>Model</label></td>
            <td><input type="text" name="model" value="${address.model()}"
                    required autocomplete="off"></td>
        </tr>
        <tr>
            <td><label>Address</label></td>
            <td><input type="text" name="address" value="${address.address()}"
                    required autocomplete="off"></td>
        </tr>
        <tr>
            <td><label>Type</label></td>
            <td><select name="type">
                <option value="${address.client().type()}">${address.client().type()}</option>
                <option value="Физическое лицо">Физическое лицо</option>
                <option value="Юридическое лицо">Юридическое лицо</option>
            </select></td>
        </tr>
        <tr>
            <td><label>Registration date</label></td>
            <td><input type="date" name="date" value="${address.client().added()}"
                    autocomplete="off"></td>
        </tr>
    </table>
    <p><input type="submit" value="Save"></p>
</form>
</body>
</html>