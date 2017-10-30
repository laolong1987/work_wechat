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
  <title>我的审批</title>
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
<div class="container pd-lr">
  <div class="clearfloat">
    <div class="approach-nav approach-nav-choose" hide="processed">
      待我审批单的(${fn:length(waitProcessList)})
    </div>
    <div class="approach-nav" hide="waitProcess">我已审批的</div>
  </div>

  <div class="waitProcess">
    <div class="data-list">
      <c:forEach var="item" items="${waitProcessList}">
        <a
          href="<%=webRoot%>/approval/apply/${item.templateId}/${item.dataId}?sentby=${item.sendBy}">
          <div class="approach-list">
            <div class="clearfloat title">
              <span class="attr-name">主题：</span>
              <span class="attr-value">${item.subject}</span>

              <span class="doc-date">${item.writeDate}</span>
            </div>
            <div class="content">
              <div class="clearfloat">
                <span class="attr-name">简称：</span>
                <span class="attr-value">${item.templateType}</span>
              </div>
              <div class="clearfloat">
                <span class="attr-name">单号：</span>
                <span class="attr-value">${item.orderNum}</span>
              </div>
            </div>
            <div class="status">${item.status}</div>
          </div>
        </a>
      </c:forEach>
    </div>


  </div>

  <div class="processed f-dn">
    <div class="data-list">
      <c:forEach var="item" items="${processedList}">
        <a href="<%=webRoot%>/approval/apply/${item.templateId}/${item.dataId}">
          <div class="approach-list">
            <div class="clearfloat title">

              <span class="attr-name">主题：</span>
              <span class="attr-value">${item.subject}</span>
              <span class="doc-date">${item.writeDate}</span>
            </div>
            <div class="content">
              <div class="clearfloat">
                <span class="attr-name">简称：</span>
                <span class="attr-value">${item.templateType}</span>
              </div>
              <div class="clearfloat">
                <span class="attr-name">单号：</span>
                <span class="attr-value">${item.orderNum}</span>
              </div>
            </div>
            <div class="status">${item.status}</div>
          </div>
        </a>
      </c:forEach>
    </div>
  </div>


</div>
<!-- jquery -->
<script type="text/javascript" src='<%=webRoot%>/js/jquery.min.js'></script>
<script>
  $(function () {
    $(".approach-nav").on("click", function () {
      $(this).addClass("approach-nav-choose").siblings().removeClass("approach-nav-choose");
      var _class = "." + $(this).attr("hide");
      $(_class).addClass("f-dn").siblings().removeClass("f-dn");
    });
    loadData(".waitProcess", ".data-list", "waitProcess");
    loadData(".processed", ".data-list", "processed");

  })

  function loadData($container, $content, apiType) {
    var pageNum = 1;
    var load = true;
    var finished = false;
    $($container).scroll(function () {
      var scrollTrigger = 0.8;
      var node = $(this);
      if ($(this).scrollTop() / ($(this).find($content).height() - $(this).height()) > scrollTrigger && load && !finished) {
        //console.log('autopagerizing triggered');

        //        console.log("top:" + $(this).scrollTop() + "——data-height:" + ($(".processed .data-list").height() + "——height" + $(this).height()))
        //        console.log($(this).scrollTop() / ($(this).find(".data-list").height() - $(this).height()))
        load = false;
        $.ajax({
          url: "list/" + apiType + "/" + pageNum,
          method: "get",
          data: "",
          dataType: "JSON",
          success: function (res) {
            if (res.length < 10)
              finished = true;

            var elements = "";
            $.each(res, function (index, item) {
              var status = item.status;
              if (status == null)
                status = "";
              elements += ' <a href="apply/' + item.templateId + '/' + item.dataId + '?sentby=' + item.sentBy + '">' + '<div class="approach-list"><div class="clearfloat title">' +
                '<span class="attr-name">单号：</span><span class="attr-value">' + item.orderNum + '</span><span class="doc-date">' + item.writeDate +
                '</span></div><div class="content"><div class="clearfloat"><span class="attr-name">简称：</span><span class="attr-value">' + item.templateType +
                '</span></div><div class="clearfloat"><span class="attr-name">主题：</span><span class="attr-value">' + item.subject +
                '</span></div></div><div class="status">' + status + '</div></div></a>'
            })
            node.find($content).append(elements);
            load = true;
          }
        });
        pageNum++;
      }
    })
  }

</script>
</body>
</html>
