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
    <title>用车申请单</title>
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
        .addbtn{
            width: 300px;height: 35px;margin-top: 45px;
            background-image: url(${ctx}/images/add/btn.png);
            background-size:100% 100%;
            color: white;
        }
        .touming:focus{outline:none !important;}
    </style>
</head>
<body>

<div class="container">
    <div class="row top">
        <div class="col-xs-6">
            <span class="ziti1">${dept} ${name}</span>
        </div>
        <div class="col-xs-6 text-right">
            <span class="ziti1">2017-09-29 15:38</span>
        </div>
    </div>
    <form  id="addform" action="createcar" name="addform" method="post" >
    <div class="row content">
        <label class="col-xs-3 control-label lab lab2">预定电话</label>
        <div class="col-xs-9">
            <input  type="text" class="touming" placeholder="请填写预定人电话(必填)" value="" id="ydrphone" name="ydrphone" required>
        </div>

    </div>

    <div class="row content">
        <label class="col-xs-3 control-label lab lab2">出发时间</label>
        <div class="col-xs-5">
            <input  type="text" class="form_datetime touming" placeholder="请选择出发时间" name="starttime" id="starttime" value="" required>
        </div>
        <label class="col-xs-4 control-label lab lab3" for="starttime">请选择(必填)
        </label>
    </div>

    <div class="row content">
        <label class="col-xs-3 control-label lab lab2">结束时间</label>
        <div class="col-xs-5">
            <input  type="text" class="form_datetime touming" placeholder="请选择结束时间" name="endtime" id="endtime" value="" required>
        </div>
        <label class="col-xs-4 control-label lab lab3" for="endtime">请选择(必填)
        </label>
    </div>

    <div class="row content2">
        <label class="col-xs-3 control-label lab lab2">乘车人员</label>
        <div class="col-xs-9">
            <%--<input  type="text" class="touming" placeholder="请选择乘车人员" id="usernames" onclick="opendepartment()" value="" required>--%>
            <textarea class="touming" rows="5" name="usernames" id="usernames" onclick="opendepartment('usernames')" placeholder="请选择(必填)"></textarea>
        </div>
        <%--<label class="col-xs-4 control-label lab lab3">请选择(必填)--%>
        <%--</label>--%>
    </div>
    <div class="row content">
        <label class="col-xs-3 control-label lab lab2">等</label>
        <div class="col-xs-5">
            <input  type="text" class="touming" placeholder="请填写乘车人数"  value="" id="zrs" name="zrs" required>
        </div>
        <label class="col-xs-4 control-label lab">人
        </label>
    </div>


    <div class="row content">
        <label class="col-xs-3 control-label lab lab2">上车地点</label>
        <div class="col-xs-8">
            <input  type="text" class="touming" placeholder="请填写上车地点"  value=""  id="pickupaddress" name="pickupaddress" required>
        </div>
    </div>


    <div class="row" style="margin-top: 10px">
        <div class="col-xs-2">
        </div>
        <div class="col-xs-4">
            <input type="radio" name="Sfwd"  value="不是">市内
        </div>
        <div class="col-xs-6">
            <input type="radio" name="Sfwd"  value="是">市外
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-3 control-label lab lab2">目的地</label>
        <div class="col-xs-9">
            <input  type="text" class="touming" placeholder="请填写目的地"  value="" id="destination" name="destination" required>
        </div>
    </div>

    <div class="row content2">
        <label class="col-xs-3 control-label lab lab2">车辆用途</label>
        <div class="col-xs-9">
            <textarea class="touming" rows="5" name="clyt" id="clyt" placeholder="请填写车辆用途(必填)"></textarea>
        </div>
    </div>

    <div class="row content2" style="margin-bottom: 50px">
        <label class="col-xs-3 control-label lab lab2">备注</label>
        <div class="col-xs-9">
            <textarea class="touming" rows="5" name="remark" id="remark" placeholder="如 1.是否往返 2.是否多目的地"></textarea>
        </div>
    </div>
</form>
    <div  style="position:fixed;bottom:20px;margin:auto;left:0; right:0;text-align: center">
        <button type="button" class="btn btn-primary addbtn" onclick="add()">提交审批</button>
    </div>
    <div class="text-right">
        <div style="width: 300px;text-decoration:underline;color: black">
            <a href="${ctx}/approval/self-list/321">查看列表</a>
        </div>
    </div>
</div>
<jsp:include page="adddepartment.jsp" />
<script type="text/javascript" src='${ctx}/js/app/jquery.min.js'></script>
<%--<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>--%>
<script type="text/javascript" src='${ctx}/js/bootstrap.min.js'></script>
<%--<script type="text/javascript" src='${ctx}/js/bootstrap-datetimepicker.min.js'></script>--%>
<%--<script type="text/javascript" src='${ctx}/js/bootstrap-datetimepicker.zh-CN.js'></script>--%>
<script type="text/javascript"
        src="http://cache.shchengdian.com/plugins/mobiscroll.custom-3.0.0-beta2.min.js"
        charset="UTF-8"></script>
<script>

$('.form_datetime').mobiscroll().datetime({
    theme: 'Mobiscroll',
    lang: 'zh',
    display: 'center',
//    dateFormat: 'yyyy-MM-dd HH:mm',
    min: new Date(2000, 1, 1),
    disabled: false
});

    function add(){
        var ydrphone=$("#ydrphone").val();
        if(''==ydrphone){
            alert('请填写预定人电话');
            return
        }
        var a=$("#starttime").val();
        if(''==a){
            alert('请选择出发时间');
            return
        }
        var b=$("#endtime").val();
        if(''==b){
            alert('请选择结束时间');
            return
        }

        var clyt=$("#clyt").val();
        if(''==clyt){
            alert('请填写车辆用途');
            return
        }
        $(".btn").attr("disabled", true);
        $("#addform").submit();
    }
</script>
</body>
</html>