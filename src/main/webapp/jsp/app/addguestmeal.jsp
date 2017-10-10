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
    <title>Title</title>
    <%@ include file="csscommon.jsp" %>
    <style>

        body{
            background-image: url(${ctx}/images/bg.jpg);
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
            background-color: #ffffff;
            margin-top: 10px;
        }
        .lab{
            margin-top: 8px;
        }
        .touming{
            border: 0;
        }
        .tip{
            font-family: PingFangSC;
            font-size: 12px;
            line-height: 1.67;
            text-align: center;
            color: #f40000;
            margin-top: 10px;
        }

        .touming:focus{outline:none !important;}
    </style>
</head>
<body>
<div class="container">
    <div class="row top">
        <div class="col-xs-6">
            <span class="ziti1">张欢欢</span>
        </div>
        <div class="col-xs-6">
            <span class="ziti2">申请部门:</span>
            <span class="ziti1">总经理工作部</span>
        </div>
    </div>


    <div class="row content">
        <label class="col-xs-4 control-label lab">来访人员/事由</label>
        <div class="col-xs-8">
            <textarea class="form-control touming" rows="5" name="desc" id="desc" placeholder=""  required></textarea>
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-4 control-label lab">用餐日期</label>
        <div class="col-xs-8">
            <input  type="text" class="form-control form_datetime touming" placeholder="请选择用餐日期(必填)" name="date1" id="date1" value="" required>
        </div>
    </div>


    <div class="row content">
        <label class="col-xs-4 control-label lab">用餐人数</label>
        <div class="col-xs-8">
            <input  type="text" class="form-control touming" placeholder="请填写人数"  value="" required>
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-4 control-label lab">备注</label>
        <div class="col-xs-8">
            <textarea class="form-control touming" rows="5" name="desc" id="desc" placeholder="请填写备注(必填)"  required></textarea>
        </div>
    </div>


    <div class="row" style="margin-top: 10px">
        <div class="col-xs-4">
            用餐种类
        </div>
        <div class="col-xs-4">
            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">工作用餐
        </div>
        <div class="col-xs-4">
            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">接待用餐
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-4 control-label lab">用餐时间</label>
        <div class="col-xs-8">
            <select class="form-control touming">
                <option value="" disabled selected>请选择用餐时间(必填)</option>
                <option>早餐</option>
                <option>中餐</option>
                <option>晚餐</option>
                <option>夜点</option>
            </select>
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-4 control-label lab">用餐标准</label>
        <div class="col-xs-8">
            <input  type="text" class="form-control touming" placeholder="请填写用餐标准"  value="" required>
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-4 control-label lab">用餐地点</label>
        <div class="col-xs-8">
            <input  type="text" class="form-control touming" placeholder="请填写用餐地点"  value="" required>
        </div>
    </div>

    <div class="text-center tip">注：中餐请于1点钱提交申请，晚餐请于16点钱提交申请</div>

    <div class="text-center">
        <button type="button" class="btn btn-primary" style="width: 300px;height: 35px;margin-top: 45px">提交审批</button>
    </div>
    <div class="text-center">
        <button type="button" class="btn btn-primary" style="width: 300px;height: 35px;margin-top: 45px">显示列表</button>
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