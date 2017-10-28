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
  <title>发文单</title>
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
    <div class="dept">${object.department}</div>
    <div class="doc-date">日期：<span>${object.sentDate}</span>&nbsp;&nbsp;封发</div>
    <div class="doc-flag">
      <span>沪外：</span><span class="s1">${object.dn}</span>
      <span>二司：</span><span class="s2">${object.sntype}</span>
      <span>字：</span><span class="s2">${object.noyear}</span>
      <span>第${object.no}号</span>
    </div>
    <div class="attr-name f-fl  ">主办部门：</div>
    <div class="attr-value f-fl  ">${object.sponsor}</div>
    <div class="attr-name f-fl  ">会签：</div>
    <div class="attr-value f-fl  ">${object.hqbm}</div>
    <div class="attr-name f-fl  ">核稿：</div>
    <div class="attr-value f-fl  ">${object.audit}</div>
    <div class="attr-name f-fl  ">拟稿人：</div>
    <div class="attr-value f-fl  ">${object.writer}</div>
    <div class="attr-name f-fl  ">签发：</div>
    <div class="attr-value f-fl  ">${object.approval}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">事由：</div>
    <div class="attr-value f-fl  ">${object.reason}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">附件信息：</div>
    <div class="attr-value f-fl  ">${object.attachment}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">主送单位：</div>
    <div class="attr-value f-fl  ">
      ${object.mainSender}
    </div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">内部抄送：</div>
    <div class="attr-value f-fl  ">
      ${object.departmentAudit}
    </div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">抄送单位：</div>
    <div class="attr-value f-fl  ">${object.ccername2}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">抄报单位：</div>
    <div class="attr-value f-fl  ">${object.ccername1}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">会签意见：</div>
    <div class="attr-value f-fl  ">${object.reviewer}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">缮印：</div>
    <div class="attr-value f-fl  ">${object.kid}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">校对：</div>
    <div class="attr-value f-fl  ">${object.checkperson}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <span class="">${object.sentDate}</span>印
    <span class="">${object.fen}</span>份
  </div>

  <%@include file="common-submit.jsp"%>
</body>
</html>
