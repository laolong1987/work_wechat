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
  <title>饭客申请</title>
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
    <div class="attr-name f-fl  ">事由：</div>
    <div class="attr-value f-fl  ">
      ${object.reason}
    </div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">附件信息：</div>
    <div class="attr-value f-fl  ">${object.attachment}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">用餐种类：</div>
    <div class="attr-value f-fl  ">${object.repastType}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">用餐时间：</div>
    <div class="attr-value f-fl  ">${object.repastTime}</div>
  </div>

  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">用餐日期：</div>
    <div class="attr-value f-fl  ">${object.repastDate}</div>
  </div>


  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">用餐标准：</div>
    <div class="attr-value f-fl  ">${object.standard}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">用餐地点：</div>
    <div class="attr-value f-fl  ">${object.address}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">用餐数量：</div>
    <div class="attr-value f-fl  ">${object.foodNumber}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">备注：</div>
    <div class="attr-value f-fl  ">${object.remark}</div>
  </div>
  <div class="sub-head">
    以下由食堂填写
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">实际用餐标准：</div>
    <c:choose>
      <c:when test="${fn:contains(editfields, 'sjycbz')}">
        <input class="attr-value f-fl edit" type="text" name="sjycbz"
               value="${object.actuallyStandard}" placeholder="请输入">
      </c:when>
      <c:otherwise>
        <div class="attr-value f-fl  ">${object.actuallyStandard}</div>
      </c:otherwise>
    </c:choose>

  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">实际用用餐数量：</div>
    <c:choose>
      <c:when test="${fn:contains(editfields, 'sjycsl')}">
        <input class="attr-value f-fl edit" type="text" name="sjycsl"
               value="${object.actuallyFoodNumber}" placeholder="请输入">
      </c:when>
      <c:otherwise>
        <div class="attr-value f-fl  ">${object.actuallyFoodNumber}</div>
      </c:otherwise>
    </c:choose>

  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">共消费金额：</div>
    <c:choose>
      <c:when test="${fn:contains(editfields, 'xfje')}">
        <input class="attr-value f-fl edit" type="text" name="xfje"
               value="${object.totalConsumption}" placeholder="请输入">
      </c:when>
      <c:otherwise>
        <div class="attr-value f-fl  ">${object.totalConsumption}</div>
      </c:otherwise>
    </c:choose>

  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">部门审核：</div>
    <c:choose>
      <c:when test="${fn:contains(editfields, 'bmsh')}">
        <input class="attr-value f-fl edit" type="text" name="bmsh"
               value="${object.approachResult}" placeholder="请输入">
      </c:when>
      <c:otherwise>
        <div class="attr-value f-fl  ">${object.approachResult}</div>
      </c:otherwise>
    </c:choose>

  </div>
  <div class="remark">
    注：中餐请于11点前提交申请，晚餐请于16点前提交申请。
  </div>

  <%@include file="common-submit.jsp" %>
</body>
</html>
