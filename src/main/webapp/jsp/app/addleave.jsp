<%--
  Created by IntelliJ IDEA.
  User: gaoyang
  Date: 17/10/10
  Time: 下午8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,
                                     initial-scale=1.0,
                                     maximum-scale=1.0,
                                     user-scalable=no">
    <title>员工申请单</title>
    <%@ include file="csscommon.jsp" %>
    <style>

        body{
            background-image: url(${ctx}/images/add/bg.jpg);
        }
        .ziti1{
            width: 82px;
            height: 27px;
            font-family: PingFangSC;
            font-size: 14px;
            line-height: 1.43;
            text-align: center;
            color: #545454;
        }
        .ziti2 {
            width: 118px;
            height: 27px;
            font-family: PingFangSC;
            font-size: 14px;
            line-height: 1.43;
            text-align: center;
            color: #000000;
        }
        .top{
            margin-top: 25px;
        }
        .content{
            background-image: url(${ctx}/images/add/smallbg.png);
            background-size:100% 100%;
            margin-top: 10px;
            height: 55px;
        }
        .content2{
            background-image: url(${ctx}/images/add/smallbg.png);
            background-size:100% 100%;
            margin-top: 10px;
            height: 160px;
        }
        .lab{
            margin-top: 15px;
        }
        .lab2{
            text-align: right;
        }
        .lab3{
            background-image: url(${ctx}/images/add/arrow.png);
            background-position: right;
            background-repeat: no-repeat;
            background-size: 8px;
            background-origin: content-box;
        }
        .addbtn{
            width: 100%;height: 50px;;
            background-image: url(${ctx}/images/add/btn.png);
            background-size:100% 100%;
            color: white;
        }
        .touming{
            border: solid 0px;
            width: 100%;
            height: auto;
            font-size: 14px;
            /*background-color: #fff;*/
            background-image: none;
            line-height: 1.42857143;
            margin-top: 15px;
            background-color: transparent;
            -webkit-appearance: initial;
        }
        .topbut{
            position:fixed;top:0;margin:auto;left:0; right:0;
            background-image: url(${ctx}/images/add/topbg.png);
            background-size:100% 100%;
            height: 54px;
            width: 100%;
            text-align: right;
            z-index: 999;
        }
        .topbut button{
            width: 80px;
            height: 30px;
            margin-right: 20px;
            margin-top: 8px;
            background-image: url(${ctx}/images/add/listbtn.png);
            background-size:100% 100%;
            color: white;
            font-family: PingFangSC;
            font-size: 13px;
        }
        .topbut img{
            margin-right: 20px;
            width: 20px;
            margin-top: 4px;
        }
        .touming:focus{outline:none !important;}
    </style>
</head>
<body>
<div class="topbut">
    <button type="button" class="btn btn-default" onclick="tolist()">查看列表</button>
    <%--<img src="${ctx}/images/add/arrowtop.png">--%>
</div>
<div class="container">
    <div class="row top" style="margin-top: 60px">
        <div class="col-xs-4">
            <span class="ziti1">${name}</span>
        </div>
        <div class="col-xs-8 text-right">
            <span class="ziti2">申请部门:</span>
            <span class="ziti1">${dept}</span>
        </div>
    </div>
    <form  id="addform" action="createleave" name="addform" method="post" >
        <%--<input  type="hidden" id="day" name="day" >--%>
    <div class="row content">
        <label class="col-xs-2 control-label lab lab2">类型</label>
        <div class="col-xs-6">
            <select class="touming" id="type" name="type">
                <option value="" disabled selected>请选择请假类型(必填)</option>
                <option value="年休假">年休假</option>
                <option value="调休假">调休假</option>
                <option value="病假">病假</option>
                <option value="事假">事假</option>
                <option value="婚丧假">婚丧假</option>
                <option value="公假">公假</option>
                <option value="探亲假">探亲假</option>
                <option value="产假">产假</option>
                <option value="哺乳假">哺乳假</option>
                <option value="产前假">产前假</option>
                <option value="授乳假">授乳假</option>
            </select>
        </div>
        <label class="col-xs-4 control-label lab lab3" for="type">请选择(必填)
        </label>
    </div>

    <div class="row content">
        <label class="col-xs-2 control-label lab lab2">开始</label>
        <div class="col-xs-6">
            <input  type="text" class="form_datetime touming" placeholder="请选择开始时间(必填)" name="date1" id="date1" value="" onchange="btnCount_Click(this)" required>
        </div>
        <label class="col-xs-4 control-label lab lab3" for="date1">请选择(必填)</label>
    </div>

    <div class="row content">
        <label class="col-xs-2 control-label lab lab2">结束</label>
        <div class="col-xs-6">
            <input  type="text" class="form_datetime touming" placeholder="请选择结束时间(必填)" name="date2" id="date2" value="" onchange="btnCount_Click(this)" required>
        </div>
        <label class="col-xs-4 control-label lab lab3" for="date2">请选择(必填)</label>
    </div>

    <div class="row content">
        <label class="col-xs-2 control-label lab lab2">计</label>
        <div class="col-xs-6">
            <%--<p class="form-control-static" style="margin-top: 7px" id="days"></p>--%>
            <input  type="text" class="touming"  name="day" id="day" value="" required>
        </div>
        <label class="col-xs-4 control-label lab lab3">天</label>
    </div>

    <div class="row content2" style="margin-bottom: 50px">
        <label class="col-xs-2 control-label lab lab2">理由</label>
        <div class="col-xs-10">
            <textarea class="touming" rows="5" name="desc" id="desc" placeholder="请填写备注(必填)"  required></textarea>
        </div>
    </div>
    </form>
    <div  style="position:fixed;bottom:0;margin:auto;left:0; right:0;text-align: center">
        <button type="button" class="btn btn-primary addbtn" onclick="add()">提交审批</button>
    </div>
    <%--<div class="text-right">--%>
        <%--<div style="width: 300px;text-decoration:underline;color: black">--%>
            <%--<a href="${ctx}/approval/self-list/349">查看列表</a>--%>
        <%--</div>--%>
    <%--</div>--%>
</div>
<script type="text/javascript" src='${ctx}/js/jquery.min.js'></script>
<%--<script type="text/javascript" src='${ctx}/js/bootstrap.min.js'></script>--%>
<%--<script type="text/javascript" src='${ctx}/js/bootstrap-datetimepicker.min.js'></script>--%>
<%--<script type="text/javascript" src='${ctx}/js/bootstrap-datetimepicker.zh-CN.js'></script>--%>
<script type="text/javascript"
        src="http://cache.shchengdian.com/plugins/mobiscroll.custom-3.0.0-beta2.min.js"
        charset="UTF-8"></script>
<script>
    var ctx='${ctx}';
</script>
<script type="text/javascript" src='${ctx}/js/work/addleave.js' />
</body>
</html>