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
<!DOCTYPE html>
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
            margin-top: 14px;
        }
        .addbtn{
            width: 300px;height: 35px;margin-top: 45px;
            background-image: url(${ctx}/images/add/btn.png);
            background-size:100% 100%;
            color: white;
        }
        .touming{
            border: 0;
            width: 100%;
            height: auto;
            font-size: 14px;
            background-color: #fff;
            background-image: none;
            line-height: 1.42857143;
            margin-top: 15px;
        }
        .touming:focus{outline:none !important;}
    </style>
</head>
<body>
<div class="container">
    <div class="row top">
        <div class="col-xs-4">
            <span class="ziti1">张欢欢</span>
        </div>
        <div class="col-xs-8 text-right">
            <span class="ziti2">申请部门:</span>
            <span class="ziti1">总经理工作部</span>
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-3 control-label lab">类型</label>
        <div class="col-xs-9">
            <select class="touming">
                <option value="" disabled selected>请选择请假类型(必填)</option>
                <option>调休假</option>
                <option>年假</option>
            </select>
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-3 control-label lab">开始</label>
        <div class="col-xs-9">
            <input  type="text" class="form_datetime touming" placeholder="请选择开始时间(必填)" name="date1" id="date1" value="" required>
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-3 control-label lab">结束</label>
        <div class="col-xs-9">
            <input  type="text" class="form_datetime touming" placeholder="请选择结束时间(必填)" name="date1" id="date1" value="" required>
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-3 control-label lab">计</label>
        <div class="col-xs-9">
            <p class="form-control-static" style="margin-top: 7px">2天</p>
        </div>
    </div>

    <div class="row content2">
        <label class="col-xs-3 control-label lab">理由</label>
        <div class="col-xs-9">
            <textarea class="touming" rows="5" name="desc" id="desc" placeholder="请填写备注(必填)"  required></textarea>
        </div>
    </div>

    <div class="text-center">
        <button type="button" class="btn btn-primary addbtn">提交审批</button>
    </div>
    <div class="text-right">
        <div style="width: 300px;">
            <a href="#">显示列表</a>
        </div>
    </div>
</div>
<script type="text/javascript" src='${ctx}/js/jquery.min.js'></script>
<script type="text/javascript" src='${ctx}/js/bootstrap.min.js'></script>
<script type="text/javascript" src='${ctx}/js/bootstrap-datetimepicker.min.js'></script>
<script type="text/javascript" src='${ctx}/js/bootstrap-datetimepicker.zh-CN.js'></script>
<script>
    $(".form_datetime").datetimepicker({
        format: "yyyy-mm-dd hh:ii",
        autoclose: true,
        todayBtn: true,
        language:'zh-CN'
    });


    function  btnCount_Click(){
        s1  =  "2007-01-04"
        s2  =  "2007-01-05"
        alert("第一个日期；"+s1+"/n第二个日期："+s2+"/n相差"+DateDiff(s1,s2)+"天")
    }
    //计算天数差的函数，通用
    function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式
        var  aDate,  oDate1,  oDate2,  iDays
        aDate  =  sDate1.split("-")
        oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式
        aDate  =  sDate2.split("-")
        oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])
        iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)+1  //把相差的毫秒数转换为天数
        return  iDays
    }


</script>
</body>
</html>