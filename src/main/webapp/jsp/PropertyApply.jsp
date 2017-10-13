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
  <title>资产申请</title>
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
    <div class="attr-name f-fl special-width  ">申请人：</div>
    <div class="attr-value f-fl  ">${object.applicant}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  special-width">申请部门：</div>
    <div class="attr-value f-fl  ">${object.department}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl special-width ">申请日期：</div>
    <div class="attr-value f-fl  ">
      ${object.orderDate}
    </div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl special-width ">申请资产类型：</div>
    <div class="attr-value f-fl  ">
      ${object.type}
    </div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  special-width">负责人审批意见：</div>
    <div class="attr-value f-fl  ">
      ${object.managerOpinion}
    </div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  special-width">信息中心审批意见：</div>
    <div class="attr-value f-fl  ">
      ${object.ICOpinion}
    </div>
  </div>


  <div class="info-row mgt-20">
    <c:forEach var="item" items="${noticeList}" varStatus="status">
      <div class="process-${4-status.index%4} clearfloat">
        <div class="flow"></div>
        <div
          class="process-text">${item.receiverName} ${item.noticeType} ${item.action}</div>
        <div class="process-date">${item.processTime}</div>
      </div>

    </c:forEach>
  </div>

  <div class="process-button ">
    <div class="refuse">拒绝</div>
    <div class="agree">同意</div>
  </div>
  <div class="approach-reason clearfloat f-dn" id="refuse-reason">
    <div class="tip">请填写您拒绝的原因：</div>
    <textarea class="reason"></textarea>

    <div class="close">取消</div>
    <div class="submit">提交</div>

  </div>

</div>
<script type="text/javascript" src='<%=webRoot%>/js/jquery.min.js'></script>
<script>
  $(function () {
    $(".refuse").on("click", function () {
      $("#refuse-reason").removeClass("f-dn");
    })

    $(".close").on("click", function () {
      $("#refuse-reason").addClass("f-dn");
    })

  })
</script>
</body>
</html>