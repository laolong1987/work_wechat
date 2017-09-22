<%--
  Created by IntelliJ IDEA.
  User: gaoyang
  Date: 17/9/4
  Time: 下午8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,
                                     initial-scale=1.0,
                                     maximum-scale=1.0,
                                     user-scalable=no">
    <title>公告详情</title>
    <%@ include file="csscommon.jsp" %>
    <style>
        body {
            font-family: PingFangSC-Regular;
        }

        .topdiv {
            margin: 15px 10px 15px;
            /*width: 340px;height: 261px;*/
            justify-content: center;
            display: flex;
        }

        .topdiv img {
            /*width: 340px;height: 261px;*/
        }

        .titlediv {
            padding-left: 10px;
            padding-right: 10px;
            font-size: 12px;
            color: #777777;
        }

        .titledivc {
            margin-top: 10px;
            padding-left: 10px;
            padding-right: 10px;
            font-size: 14px;
            color: #000000;
            font-weight: bold;
        }

        .contentdiv {
            margin-top: 10px;
            padding-left: 10px;
            padding-right: 10px;
            margin-bottom: 10px;
        }

        .footdiv {
            padding: 10px 10px 0px;
            background: linear-gradient(to bottom, #F3F3F3, #FFFFFF); /* 标准的语法 */
            color: #777777;
            font-size: 13px;
        }

        .footdiv .shuliang {
            color: #000000;
            font-weight: bold;
        }

        .namediv {
            color: #777777;
            font-size: 13px;
        }

        hr {
            margin-top: 5px;
            margin-bottom: 5px;
        }
    </style>

</head>
<body>
<div class="container">
    <div class="topdiv">
        <img src="../file/doDownload/${news.topimg}" class="img-responsive">
    </div>
    <div class="titlediv row">
        <div class="col-xs-6 text-left">
            来源于:${news.source}
        </div>
        <div class="col-xs-6 text-right">
            发布于:2${puttime}
        </div>
    </div>
    <div class="titledivc text-center">${news.title}</div>
    <div class="contentdiv">
        ${news.content}
    </div>

    <div class="footdiv row">
        <div class="col-xs-6 text-left">
            已读成员 <span class="shuliang">${flagsize}位</span>
        </div>
        <div class="col-xs-6 text-right" data-toggle="collapse"
             data-target="#demo">
            展开<span class="glyphicon glyphicon-chevron-up"></span>
        </div>
    </div>
    <hr/>
    <div id="demo" class="panel-collapse collapse namediv">
        ${names}
    </div>
</div>
</body>
<!-- jquery -->
<script type="text/javascript" src='${ctx}/js/app/jquery.min.js'></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script>

    $('#demo').on('show.bs.collapse', function () {
        $(".glyphicon").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
    })
    $('#demo').on('hide.bs.collapse', function () {
        $(".glyphicon").removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
    })
</script>
</html>