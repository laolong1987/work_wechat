<%--
  Created by IntelliJ IDEA.
  User: gaoyang
  Date: 17/9/20
  Time: 下午5:40
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
    <title>公告列表</title>
    <%@ include file="csscommon.jsp" %>
    <link rel="stylesheet" href="${ctx}/font-awesome/css/font-awesome.min.css">
    <style>
        body{
            background-color:  #FBFBFB;
        }
        .topdiv {
            margin: 5px 0px 5px;
        }
        .box{
            /*box-shadow: 10px 10px 5px #888888;*/
            background-color: #F4F4F4;
            border-radius: 5px;
            padding-top: 5px;
            padding-bottom: 5px;
        }
        .box img{
            max-width: 100px;
            max-height: 100px;
        }
        .imgdiv{
            padding-left: 0;
            padding-right: 0;
        }

        .listdiv{
            margin: 0px 10px 5px;
        }
        .row {
            display: -webkit-box;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
            flex-wrap: wrap;
        }
        .row > [class*='col-'] {
            display: flex;
            flex-direction: column;
        }
        .cut {
            overflow:hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
            font-size: 13px;
            font-weight: bold;
        }
        .content{
            overflow: hidden;
            text-overflow: ellipsis;
            display: box;
            display: -webkit-box;
            line-clamp: 2;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            text-indent:25px;
            font-size: 12px;
            color: #777777;
        }
        .time{
            color: #777777;
            font-size: 12px;
        }
        .box.read{
            background: linear-gradient(#E7E7E7,  #FFFFFF); /* 标准的语法（必须放在最后） */
            box-shadow: 0px 5px 1px  #EBEBEB;
            /*background-color: #FFFFFF;*/
        }
    </style>

</head>
<body>
<div class="container">
    <div class="topdiv row">
        <div class="col-xs-1" style="padding: 0px">
            <div style="position: relative">
                <span class="fa fa-commenting-o fa-2x" aria-hidden="true"> </span>
                <%--<span class="badge" style="background-color: red;position: absolute ;right: -5px;top: 0">5</span>--%>
            </div>
        </div>
        <div class="col-xs-10" style="padding-right: 0px;">
            <form action="listnews" method="post" id="form1" name="form1">
            <div class="form-group has-feedback">
                <div>
                    <span class="glyphicon glyphicon-search form-control-feedback" style="color: #DCDCDC"></span>
                    <input type="text" name="title" id="title" class="form-control" placeholder="搜索关键字" value="${title}">
                </div>
            </div>
                </form>
        </div>
    </div>
    <div class="listdiv">
        <%--<div class="row box">--%>
            <%--<div class="col-xs-3 imgdiv">--%>
                <%--<img src="noimg.png"  class="">--%>
            <%--</div>--%>
            <%--<div class="col-xs-9" style="">--%>
                <%--<div class="cut">第二次大会第二次大会第二次大会第二次大会第二次11111大会</div>--%>
                <%--<div><p class="content">中国人中国人中国人中国人中国人中国人中国人中国人中国人中国人中--%>
                    <%--国人中国人中国人中国人中国人中国人中国人中国人中国人中国人中国人中国人</p></div>--%>
                <%--<div class="time">发布于2014-12-12 12:12</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="row box read">--%>
            <%--<div class="col-xs-3 imgdiv">--%>
                <%--<img src="noimg.png"  class="">--%>
            <%--</div>--%>
            <%--<div class="col-xs-9" style="">--%>
                <%--<div class="cut">第二次大会第二次大会第二次大会第二次大会第二次11111大会</div>--%>
                <%--<div><p class="content">中国人中国人中国人中国人中国人中国人中国人中国人中国人中国人中--%>
                    <%--国人中国人中国人中国人中国人中国人中国人中国人中国人中国人中国人中国人</p></div>--%>
                <%--<div class="time">发布于2014-12-12 12:12</div>--%>
            <%--</div>--%>
        <%--</div>--%>
            <c:forEach items="${list}" var="l">
                <div class="row box read" onclick="shownews('${l.id}')">
                    <div class="col-xs-3 imgdiv">
                        <img src="${ctx}/file/doDownload/${l.topimg}"  class="">
                    </div>
                    <div class="col-xs-8" style="">
                        <div class="cut">${l.title}</div>
                        <div><p class="content">${l.cont}</p></div>
                        <div class="time">发布于
                            <fmt:formatDate value="${l.puttime}" pattern="yyyy-MM-dd HH:mm"/></div>

                    </div>
                </div>
            </c:forEach>
    </div>
</div>
</body>
<!-- jquery -->
<script type="text/javascript" src='${ctx}/js/app/jquery.min.js'></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script>
    function shownews(id){
        window.location.href="detailnews?id="+id;
    }

</script>
</html>