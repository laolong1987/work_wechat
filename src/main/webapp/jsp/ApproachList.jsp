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
<html>
<head>
  <title>我的审批</title>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport"
        content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <link rel="stylesheet" type="text/css"
        href="http://cache.shchengdian.com/css/zv_index.css">
  <link rel="stylesheet" type="text/css"
        href="../css/approval.css">
  <script>
    document.documentElement.style.fontSize = document.documentElement.clientWidth / 7.5 + 'px';
    window.onresize = function () {
      document.documentElement.style.fontSize = document.documentElement.clientWidth / 7.5 + 'px';
    };
  </script>
</head>
<body>
<div class="container pd-lr">
  <div class="clearfloat">
    <div class="approach-nav approach-nav-choose" data="waitProcess">待我审批单的(0)
    </div>
    <div class="approach-nav" data="processed">我已审批的</div>
  </div>
  <div>
    <div class="waitProcess">
      <c:forEach var="item" items="${waitProcessList}">
        <div class="approach-list">
          <div class="clearfloat title">
            <span class="attr-name">单号：</span>
            <span class="attr-value">${item.orderNum}</span>
            <span class="doc-date">${item.writeDate}</span>
          </div>
          <div class="content">
            <div class="clearfloat">
              <span class="attr-name">简称：</span>
              <span class="attr-value">${item.templateType}</span>
            </div>
            <div class="clearfloat">
              <span class="attr-name">主题：</span>
              <span class="attr-value">${item.subject}</span>
            </div>
          </div>
          <div class="status">${item.status}</div>
        </div>

      </c:forEach>

    </div>

    <div class="processed f-dn">
      <c:forEach var="item" items="${processedList}">
        <div class="approach-list">
          <div class="clearfloat title">
            <span class="attr-name">单号：</span>
            <span class="attr-value">${item.orderNum}</span>
            <span class="doc-date">${item.writeDate}</span>
          </div>
          <div class="content">
            <div class="clearfloat">
              <span class="attr-name">简称：</span>
              <span class="attr-value">${item.templateType}</span>
            </div>
            <div class="clearfloat">
              <span class="attr-name">主题：</span>
              <span class="attr-value">${item.subject}</span>
            </div>
          </div>
          <div class="status">${item.status}</div>
        </div>

      </c:forEach>

    </div>

  </div>


</div>
<!-- jquery -->
<script type="text/javascript" src='../js/jquery.min.js'></script>
<script>
  $(function () {
    $(".approach-nav").on("click", function () {
      $(this).addClass("approach-nav-choose").siblings().removeClass("approach-nav-choose");
      var _class = "." + $(this).attr("data");
      $(_class).removeClass("f-dn").siblings().addClass("f-dn");

    })
  })

</script>
</body>
</html>
