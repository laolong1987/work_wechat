<%--
  Created by IntelliJ IDEA.
  User: gaoyang
  Date: 17/11/5
  Time: 下午11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>无权限</title>
    <link rel="stylesheet" type="text/css"
          href="http://cache.shchengdian.com/css/zv_index.css">
    <link rel="stylesheet" type="text/css"
          href="${ctx}/css/approval.css">
    <script>
      document.documentElement.style.fontSize = document.documentElement.clientWidth / 7.5 + 'px';
      window.onresize = function () {
        document.documentElement.style.fontSize = document.documentElement.clientWidth / 7.5 + 'px';
      };
    </script>
    <style>
        .img-tip{
            width: 4.87rem;
            height:3.23rem ;
            margin: 0 auto;
            margin: 4.32rem auto 0 auto;
        }
        .img-tip img{
            width: 100%;
        }
    </style>
</head>
<body>
<div class="img-tip">
    <img  class="img-responsive" src="${ctx}/images/add/noquanxian.png">
</div>
</body>
</html>
