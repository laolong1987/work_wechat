<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/9/3
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  request.setAttribute("contextPath", request.getContextPath());
  String webRoot = "http://" + request.getServerName() + ":"
    + request.getServerPort() + request.getContextPath();
%>
<html>
<head>
  <title>加班申请单</title>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport"
        content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <link rel="stylesheet" type="text/css"
        href="http://cache.shchengdian.com/css/zv_index.css">
  <link rel="stylesheet" type="text/css"
        href="<%=webRoot%>/css/approval.css">
  <script>
    document.documentElement.style.fontSize = document.documentElement.clientWidth / 7.5 + 'px';
    window.onresize = function () {
      document.documentElement.style.fontSize = document.documentElement.clientWidth / 7.5 + 'px';
    };
  </script>
</head>
<body>
<div class="container-detail">
  <div class="info-row subject ">

    <div class="clearfloat">
      <div class="attr-name f-fl vehicle-middle ">类型：</div>
      <div class="attr-value f-fl vehicle-middle ">${object.subject}</div>
    </div>
    <div class="clearfloat">
      <div class="attr-name f-fl vehicle-middle ">单号：</div>
      <div class="attr-value f-fl vehicle-middle ">${object.orderNum}</div>
    </div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">部门：</div>
    <div class="attr-value f-fl  ">${object.department}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">申请日期：</div>
    <div class="attr-value f-fl  ">${object.applyDate}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">附件类型：</div>
    <div class="attr-value f-fl  ">${object.attachment}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">预计工作时间：</div>
    <div class="attr-value f-fl  ">${object.estimatedTime}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">工作内容：</div>
    <div class="attr-value f-fl  ">
      ${object.content}
    </div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">加班人员姓名：</div>
    <div class="attr-value f-fl  ">${object.staff}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">部门主任：</div>
    <div class="attr-value f-fl  ">${object.divisionChief}</div>
  </div>

  <div class="apply-info">
    <span class="attr-name ">制单：</span>
    <span class="attr-value">${object.operator}</span>
    <span class="attr-name ">日期：</span>
    <span class="attr-value ">${object.orderDate}</span>
  </div>
  <%@include file="common-submit.jsp"%>
</body>
</html>
