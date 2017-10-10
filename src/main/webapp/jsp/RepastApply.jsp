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
    <div class="attr-value f-fl  ">${object.actuallyStandard}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">实际用用餐数量：</div>
    <div class="attr-value f-fl  ">${object.actuallyFoodNumber}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">共消费金额：</div>
    <div class="attr-value f-fl  ">${object.totalConsumption}</div>
  </div>
  <div class="info-row mgt-20 clearfloat">
    <div class="attr-name f-fl  ">部门审核：</div>
    <div class="attr-value f-fl  ">${object.approachResult}</div>
  </div>
  <div class="remark">
    注：中餐请于11点前提交申请，晚餐请于16点前提交申请。
  </div>

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
