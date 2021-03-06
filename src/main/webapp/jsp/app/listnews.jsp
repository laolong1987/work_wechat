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
            margin: 8px -6px 8px;
        }
        .box{
            /*box-shadow: 10px 10px 5px #888888;*/
            /*background-color: #F4F4F4;*/
            /*border-radius: 5px;*/
            background-image: url(${ctx}/images/add/noreadbg.png);
            background-size:100% 100%;
            padding-top: 0px;
            padding-bottom: 10px;
            height: 100px;

        }
        .box img{
            max-width: 100px;
            max-height: 100px;
        }
        .imgdiv{
            /*padding-left: 0;*/
            /*padding-right: 0;*/
            height: 70px;
            background-size: cover;
            left:7px;
            top: 10px;
        }

        .listdiv{
            margin: 0px 8px 5px;
        }
        /*.row {*/
            /*display: -webkit-box;*/
            /*display: -webkit-flex;*/
            /*display: -ms-flexbox;*/
            /*display: flex;*/
            /*flex-wrap: wrap;*/
        /*}*/
        /*.row > [class*='col-'] {*/
            /*display: flex;*/
            /*flex-direction: column;*/
        /*}*/
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
            line-clamp: 1;
            -webkit-line-clamp: 1;
            -webkit-box-orient: vertical;
            text-indent:25px;
            font-size: 12px;
            color: #777777;
            margin: 5px 0px 5px;
        }
        .time{
            color: #777777;
            font-size: 12px;
            float:left
        }
        .box.read{
            /*background: linear-gradient(#E7E7E7,  #FFFFFF); !* 标准的语法（必须放在最后） *!*/
            /*box-shadow: 0px 5px 1px  #EBEBEB;*/
            background-image: url(${ctx}/images/add/readbg.png);
            background-size:100% 100%;
            /*background-color: #FFFFFF;*/
        }
        .ms{
            width: 38px;
            margin-top: 1px;
            margin-left: 3px;
        }

        .formdiv{
            display: block;
            width: 100%;
            height: 34px;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            color: #555;
            background-color: #fff;
            background-image: none;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
    </style>

</head>
<body>
<div class="container">
    <div class="topdiv row">
        <div class="col-xs-1" style="padding: 0px">
            <div style="position: relative">
                <img src="${ctx}/images/add/ms.png" class="ms">
                <%--<span class="" aria-hidden="true"> </span>--%>
                <%--<span class="badge" style="background-color: red;position: absolute ;right: -5px;top: 0">5</span>--%>
            </div>
        </div>
        <div class="col-xs-11" style="padding-right: 0px;">
            <form action="listnews" method="post" id="form1" name="form1">
            <div class="has-feedback">
                <div>
                    <span class="glyphicon glyphicon-search form-control-feedback" style="color: #DCDCDC"></span>
                    <input type="text" name="title" id="title" class="formdiv" placeholder="搜索关键字" value="${title}">
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
                <div class="row box
                  <c:if test="${l.readflag eq 1}">
                read
                </c:if>
                " onclick="shownews('${l.id}')">
                    <div class="col-xs-3 imgdiv" style="background-image: url(${ctx}/file/doDownload/${l.topimg})">
                        <%--<img src="${ctx}/file/doDownload/${l.topimg}"  class="">--%>
                    </div>
                    <div class="col-xs-9" style="margin-top: 10px;right: 5px;">
                        <div class="cut">${l.title}</div>
                        <div><p class="content">${l.cont}</p></div>
                        <div class="time">发布于
                            <fmt:formatDate value="${l.puttime}" pattern="yyyy-MM-dd HH:mm"/>
                        </div>
                        <c:if test="${userid ne '' && l.readflag ne 0}">
                            <div style="float:right;color: #777777">已读</div>
                        </c:if>
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