<%--
  Created by IntelliJ IDEA.
  User: gaoyang
  Date: 17/8/24
  Time: 下午11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="httpclient" method="post">
    <input type="text" id="url" name="url" style="width: 500px" >
    <br>
    <textarea name="content" id="content" style="width: 500px"></textarea>
    <br>
    <input type="submit" value="send">
</form>
<br>
<textarea style="width: 500px">${ret}</textarea>

</body>
</html>
