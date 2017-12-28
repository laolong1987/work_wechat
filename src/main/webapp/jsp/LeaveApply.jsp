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
  <title>请假单</title>
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
  <style>
    
  </style>
</head>
<body>
<div class="container-detail">
  <%@include file="common-head.jsp"%>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">编号：</div>
    <div class="attr-value f-fl  ">${object.code}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">部门：</div>
    <div class="attr-value f-fl  ">${object.department}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">姓名：</div>
    <div class="attr-value f-fl  ">${object.applicant}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">请假类型：</div>
    <div class="attr-value f-fl  ">${object.leaveType}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">日期：</div>
    <div class="attr-value f-fl  ">
      ${object.leaveDateStart}至${object.leaveDateEnd}&nbsp;&nbsp;共计${object.totalDay}天
    </div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">附件信息：</div>
    <div class="attr-value f-fl  ">${object.attachment}</div>
  </div>


  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">事由：</div>
    <div class="attr-value f-fl  ">
      ${object.reason}
    </div>
  </div>


  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">班组意见：</div>

    <c:choose>
      <c:when test="${fn:contains(editfields, 'BanzuYJ')}">
        <input class="attr-value f-fl edit" type="text" name="BanzuYJ"
               value="${object.groupApproach}"  placeholder="请输入……">
      </c:when>
      <c:otherwise>
        <div class="attr-value f-fl  ">${object.groupApproach}</div>
      </c:otherwise>
    </c:choose>

  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">部门意见：</div>
    <div class="attr-value f-fl  " attr-name="DepartYJ">${object.departmentApproach}</div>

  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">人资部意见：</div>

    <c:choose>
      <c:when test="${fn:contains(editfields, 'RzbYJ')}">
        <input class="attr-value f-fl edit" type="text" name="RzbYJ"
               value="${object.humanResourceApproach}"  placeholder="请输入……">
      </c:when>
      <c:otherwise>
        <div class="attr-value f-fl  ">${object.humanResourceApproach}</div>
      </c:otherwise>
    </c:choose>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">公司意见：</div>

    <c:choose>
      <c:when test="${fn:contains(editfields, 'GsYJ')}">
        <input class="attr-value f-fl edit" type="text" name="GsYJ"
               value="${object.firmApproach}" placeholder="请输入……">
      </c:when>
      <c:otherwise>
        <div class="attr-value f-fl  ">${object.firmApproach}</div>
      </c:otherwise>
    </c:choose>
  </div>

  <%@include file="common-submit.jsp"%>
</body>
</html>
