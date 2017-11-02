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
  <title>收文单</title>
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

  <div class="receive-doc-title">
    <span>收文编号：</span><span class="content">${object.receiveNum}</span>
    <span>沪外电二司：</span><span class="content">${object.SWBMJC}</span>
    <span class="content">收字第${object.no}号</span>
    <span>收到日期：</span><span class="">${object.receivedDate}</span>
  </div>

  <div class="info-row clearfloat">
    <div class="attr-name f-fl  ">来文机关：</div>
    <div class="attr-value f-fl  ">${object.sentDept}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">来文：</div>
    <div class="attr-value f-fl  ">字号${object.zihao}字 &nbsp;&nbsp;&nbsp;&nbsp;第${object.zihaoSize}号</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">日期：</div>
    <div class="attr-value f-fl  ">${object.sentDate}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
      <div class="attr-name f-fl  ">附件：</div>
      <div class="attr-value f-fl  ">${object.attach}</div>
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
    <div class="attr-name f-fl  ">主办人员：</div>
    <div class="attr-value f-fl  " attr-name="ZBRY">${object.sponsor}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">阅处人员：</div>
    <div class="attr-value f-fl  "  attr-name="YCRY">${object.reviewPeople}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">拟办和批示：</div>
    <div class="attr-value f-fl  " attr-name="pibanyijian">${object.instructions}</div>
  </div>


  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">待阅人员：</div>
    <div class="attr-value f-fl  "  attr-name="XZDYRY">${object.waitReaders}</div>
  </div>
 <div class="receive-doc-fd">
   <span >本文件审阅完成日期：${object.finishDate}</span>
 </div>
    <jsp:include page="./app/adddepartment.jsp" />
  <%@include file="common-submit.jsp"%>
</body>
</html>
