<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시간이 왜 이렇게 안가지</title>
</head>
<body>
    <form action="input.message" method="post">
        <table border=1 align="center">
            <tr>
                <th>Input Message</th>
            </tr>
            <tr>
                <td><input type="text" name="writer" placeholder="Input your name."></td>
            </tr>
            <tr>
                <td><input type="text" name="message" placeholder="Input your message."></td>
            </tr>
            <tr>
                <td align="center">
                    <button>Send</button>
                    <a href="index.html"><button type="button">Back</button></a>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>