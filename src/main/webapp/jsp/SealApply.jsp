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
  <title>印章申请单</title>
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
      <div class="attr-value f-fl vehicle-middle ">${object.orderNum}</div
    </div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">申请人：</div>
    <div class="attr-value f-fl  ">${object.applicant}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">申请部门：</div>
    <div class="attr-value f-fl  ">${object.department}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">印章名称：</div>
    <div class="attr-value f-fl  ">
      ${object.sealName}
    </div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">印章管理员：</div>
    <div class="attr-value f-fl  ">
      ${object.keeper}
    </div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">附件信息：</div>
    <div class="attr-value f-fl  ">${object.attachment}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">申请日期：</div>
    <div class="attr-value f-fl  ">${object.applyDate}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">分管领导：</div>
    <div class="attr-value f-fl  ">${object.leader}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">用印类别：</div>
    <c:choose>
      <c:when test="${fn:contains(editfields, 'Yylb')}">
        <input class="attr-value f-fl edit" type="text" name="Yylb"
               value="${object.useType}">
      </c:when>
      <c:otherwise>
        <div class="attr-value f-fl  ">${object.useType}</div>
      </c:otherwise>
    </c:choose>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">用印事项：</div>
    <c:choose>
      <c:when test="${fn:contains(editfields, 'Yysx')}">
        <input class="attr-value f-fl edit" type="text" name="Yysx"
               value="${object.purpose}">
      </c:when>
      <c:otherwise>
        <div class="attr-value f-fl  ">${object.purpose}</div>
      </c:otherwise>
    </c:choose>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">用印情况：</div>
    <c:choose>
      <c:when test="${fn:contains(editfields, 'Yyqk')}">
        <input class="attr-value f-fl edit" type="text" name="Yyqk"
               value="${object.circs}">
      </c:when>
      <c:otherwise>
        <div class="attr-value f-fl  ">${object.circs}</div>
      </c:otherwise>
    </c:choose>
  </div>

  <%@include file="common-submit.jsp"%>
</body>
</html>
