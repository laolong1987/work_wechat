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
  <title>劳保总务用品领用单</title>
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
  <%@include file="common-head.jsp"%>


  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">部门：</div>
    <div class="attr-value f-fl  ">${object.department}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">附件信息：</div>
    <div class="attr-value f-fl  ">${object.attachment}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">申请日期：</div>
    <div class="attr-value f-fl  ">${object.orderDate}</div>
  </div>
  <div class="sub-head">
    物资领用列表
  </div>

  <c:forEach var="detail" items="${detailList}">
    <div class="info-row mgt-20 ">
      <div class="clearfloat">
        <div class="attr-name f-fl  ">物资编码：</div>
        <div class="attr-value f-fl  ">${detail.materialCode}</div>
      </div>
      <div class="clearfloat">
        <div class="attr-name f-fl  ">物资类型：</div>
        <div class="attr-value f-fl  ">${detail.materialType}</div>
      </div>
      <div class="clearfloat">
        <div class="attr-name f-fl  ">物资名称：</div>
        <div class="attr-value f-fl  ">${detail.materialName}</div>
      </div>
      <div class="clearfloat">
        <div class="attr-name f-fl  ">单位：</div>
        <div class="attr-value f-fl  ">${detail.unit}</div>
      </div>

      <div class="clearfloat">
        <div class="attr-name f-fl  ">数量：</div>
        <div class="attr-value f-fl  ">${detail.materialNum}</div>
      </div>
      <div class="clearfloat">
        <div class="attr-name f-fl  ">金额：</div>
        <div class="attr-value f-fl  ">${detail.fee}</div>
      </div>
    </div>

  </c:forEach>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">附注：</div>
    <div class="attr-value f-fl  " attr-name="Fulu">${object.remark}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">审核人：</div>
    <div class="attr-value f-fl  "  attr-name="Zhuguan">${object.reviewer}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">领用人：</div>
    <div class="attr-value f-fl  " attr-name="Lyren">${object.applicant}</div>
  </div>

  <%@include file="common-submit.jsp"%>
</body>
</html>
