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
  <title>旅行箱借用申请单</title>
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
    <div class="attr-name f-fl  ">日期：</div>
    <div class="attr-value f-fl  ">
      从${object.orderDate}&nbsp;&nbsp;预计${object.totalDay}天
    </div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">附件信息：</div>
    <div class="attr-value f-fl  ">${object.attachment}</div>
  </div>


  <div class="sub-head">
    旅行箱领用列表
  </div>

  <c:forEach var="detail" items="${detailList}">
    <div class="info-row mgt-20 ">
      <div class="clearfloat">
        <div class="attr-name f-fl  ">品牌：</div>
        <div class="attr-value f-fl  ">${detail.brand}</div>
      </div>
      <div class="clearfloat">
        <div class="attr-name f-fl  ">规格：</div>
        <div class="attr-value f-fl  ">${detail.size}</div>
      </div>
      <div class="clearfloat">
        <div class="attr-name f-fl  ">单位：</div>
        <div class="attr-value f-fl  ">${detail.unit}</div>
      </div>
      <div class="clearfloat">
        <div class="attr-name f-fl  ">数量：</div>
        <div class="attr-value f-fl  ">${detail.borrowNum}</div>
      </div>

    </div>

  </c:forEach>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">部门领导审核：</div>
    <c:choose>
      <c:when test="${fn:contains(editfields, 'Bmldsh')}">
        <input class="attr-value f-fl edit" type="text" name="Bmldsh"
               value="${object.departmentApproachResult}">
      </c:when>
      <c:otherwise>
        <div class="attr-value f-fl  ">${object.departmentApproachResult}</div>
      </c:otherwise>
    </c:choose>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">总经负责人：</div>
    <div class="attr-value f-fl  ">${object.approachResult}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">归还日期：</div>
    <div class="attr-value f-fl  ">${object.returnDate}</div>
  </div>
  <div class="red-note">
    1. 因公出国，出差需借用行李箱，凭批准申请单至劳保仓库办理借用手续<br>
    2. 旅行箱在出差回沪后一周内归还

  </div>

  <%@include file="common-submit.jsp"%>
</body>
</html>
