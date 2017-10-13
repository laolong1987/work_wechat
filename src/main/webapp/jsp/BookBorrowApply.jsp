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
    <div class="attr-name f-fl  ">申请人：</div>
    <div class="attr-value f-fl  ">${object.applicant}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">申请部门：</div>
    <div class="attr-value f-fl  ">${object.department}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">申请日期：</div>
    <div class="attr-value f-fl  ">${object.orderDate}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">归还日期：</div>
    <div class="attr-value f-fl  ">${object.returnDate}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">附件类型：</div>
    <div class="attr-value f-fl  ">${object.attachment}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">借阅类型：</div>
    <div class="attr-value f-fl  ">${object.borrowType}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">事由：</div>
    <div class="attr-value f-fl  ">
      ${object.reason}
    </div>
  </div>
  <c:forEach var="detail" items="${detailList}">
    <div class="info-row mgt-20 ">
      <div class="clearfloat">
        <div class="attr-name f-fl  ">序号：</div>
        <div class="attr-value f-fl  ">${detail.number}</div>
      </div>
      <div class="clearfloat">
        <div class="attr-name f-fl  ">文件名称：</div>
        <div class="attr-value f-fl  ">${detail.filename}</div>
      </div>
      <div class="clearfloat">
        <div class="attr-name f-fl  ">档案编号：</div>
        <div class="attr-value f-fl  ">${detail.filename}</div>
      </div>
      <div class="clearfloat">
        <div class="attr-name f-fl  ">备注：</div>
        <div class="attr-value f-fl  ">${detail.remark}</div>
      </div>
    </div>

  </c:forEach>

  <div class="info-row">
    <c:forEach var="item" items="${noticeList}" varStatus="status">
      <div class="process-${fn:length(noticeList)-status.index} clearfloat">
        <div class="flow"></div>
        <div
          class="process-text">${item.receiverName} ${item.noticeType} ${item.action}</div>
        <div class="process-date">${item.processTime}</div>
      </div>

    </c:forEach>
  </div>
  <c:choose>
    <div class="process-button ">
      <c:when test="${fn:length(noticeList)==2}">
        <c:forEach var="item" items="${eventList}" varStatus="status">
          <c:if test="${status.index==0}">
            <div class="refuse approval" data="${item.event}">${item.name}</div>
          </c:if>
          <c:if test="${status.index==1}">
            <div class="agree approval" data="${item.event}">${item.name}</div>
          </c:if>

        </c:forEach>
      </c:when>
      <c:otherwise>
        <c:forEach var="item" items="${eventList}" varStatus="status">
          <c:if test="${status.index%2==0}">
            <div class="red-btn approval" data="${item.event}">${item.name}</div>
          </c:if>
          <c:if test="${status.index%2==1}">
            <div class="blue-btn approval" data="${item.event}">${item.name}</div>
          </c:if>

        </c:forEach>
      </c:otherwise>
    </div>
  </c:choose>
  <div class="approach-reason clearfloat f-dn" id="reason">
    <div class="tip">请填写您的意见：</div>
    <textarea class="reason"></textarea>

    <div class="close">取消</div>
    <div class="submit">提交</div>

  </div>

</div>
<script type="text/javascript" src='<%=webRoot%>/js/jquery.min.js'></script>
<script>
  $(function () {
    $(".approval").on("click", function () {
      $("#reason").removeClass("f-dn");
    })

    $(".close").on("click", function () {
      $("#refuse-reason").addClass("f-dn");
    })

  })
</script>
</body>
</html>
