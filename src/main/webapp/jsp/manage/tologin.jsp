<%--
  Created by IntelliJ IDEA.
  User: gaoyang
  Date: 17/9/12
  Time: 下午11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript">
    window.onload=function(){
        var username ="${sessionScope.user.username}";
        if(null==username || ''==username){
            top.location.href='${ctx}/admin/login';

        }}
</script>
</body>
</html>
