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
    <title>客饭申请单</title>
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
    <form  id="addform" action="createguestmeal" name="addform" method="post" >

    <div class="row content">
        <label class="col-xs-4 control-label lab">来访人员/事由</label>
        <div class="col-xs-8">
            <textarea class="form-control touming" rows="5" name="lfdwjry" id="lfdwjry" placeholder=""  required></textarea>
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-4 control-label lab">用餐日期</label>
        <div class="col-xs-8">
            <input  type="text" class="form-control form_datetime touming" placeholder="请选择用餐日期(必填)" name="ycrq" id="ycrq" value="" required>
        </div>
    </div>


    <div class="row content">
        <label class="col-xs-4 control-label lab">用餐人数</label>
        <div class="col-xs-8">
            <input  type="number" class="form-control touming" placeholder="请填写人数"  value="" id="ycsl" name="ycsl" required>
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-4 control-label lab">备注</label>
        <div class="col-xs-8">
            <textarea class="form-control touming" rows="5" name="ycremark" id="ycremark" placeholder="请填写备注(必填)"  required></textarea>
        </div>
    </div>


    <div class="row" style="margin-top: 10px">
        <div class="col-xs-4">
            用餐种类
        </div>
        <div class="col-xs-4">
            <input type="radio" name="yctype" id="optionsRadios1" onclick="addoption()" value="工作用餐" checked>工作用餐
        </div>
        <div class="col-xs-4">
            <input type="radio" name="yctype" id="optionsRadios2" onclick="addoption()" value="接待用餐">接待用餐
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-4 control-label lab">用餐时间</label>
        <div class="col-xs-8">
            <select class="form-control touming" id="yczl" name="yczl" onchange="addoption()" >
            <option value="" disabled selected>请选择用餐时间(必填)</option>
            <option value="早餐">早餐</option>
            <option value="中餐">中餐</option>
            <option value="晚餐">晚餐</option>
            <option value="夜点">夜点</option>
        </select>
        </div>
    </div>

    <div class="row content">
            <label class="col-xs-4 control-label lab">用餐标准</label>
            <div class="col-xs-8">
            <select class="form-control touming" name="ycbz" id="ycbz">
                <option value="" disabled selected>请填写用餐标准(必填)</option>
            </select>
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-4 control-label lab">用餐地点</label>
        <div class="col-xs-8">
            <select class="form-control touming" name="ycdd" id="ycdd">
                <option value="" disabled selected>请填写用餐地点(必填)</option>
            </select>
        </div>
    </div>
</form>
    <div class="text-center tip">注：中餐请于1点钱提交申请，晚餐请于16点钱提交申请</div>

    <div class="text-center">
        <button type="button" class="btn btn-primary" onclick="add()" style="width: 300px;height: 35px;margin-top: 45px">提交审批</button>
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

    function addoption(){
        var yctype= $("input[name='yctype']:checked").val();
        var yczl= $("#yczl").val();

        if("工作用餐"==yctype && "早餐"==yczl){
            $("#ycbz").html("<option value='' disabled selected>请选择用餐时间(必填)</option>");
            $("#ycbz").append("<option value='4'>4元</option>");

            $("#ycdd").html("<option value='' disabled selected>请填写用餐地点(必填)</option>");
            $("#ycdd").append("<option value='综合楼辅楼'>综合楼辅楼</option>");
            $("#ycdd").append("<option value='综合楼职工厅'>综合楼职工厅</option>");
            $("#ycdd").append("<option value='其他'>其他</option>");
        }

        if(("工作用餐"==yctype && "中餐"==yczl) || ("工作用餐"==yctype && "晚餐"==yczl) ){
            $("#ycbz").html("<option value='' disabled selected>请选择用餐时间(必填)</option>");
            $("#ycbz").append("<option value='10'>10元</option>");

            $("#ycdd").html("<option value='' disabled selected>请填写用餐地点(必填)</option>");
            $("#ycdd").append("<option value='综合楼辅楼'>综合楼辅楼</option>");
            $("#ycdd").append("<option value='综合楼职工厅'>综合楼职工厅</option>");
            $("#ycdd").append("<option value='其他'>其他</option>");
        }

        if("工作用餐"==yctype && "夜点"==yczl ){
            $("#ycbz").html("<option value='' disabled selected>请选择用餐时间(必填)</option>");
            $("#ycbz").append("<option value='4'>4元</option>");
            $("#ycbz").append("<option value='6'>6元</option>");

            $("#ycdd").html("<option value='' disabled selected>请填写用餐地点(必填)</option>");
            $("#ycdd").append("<option value='综合楼辅楼'>综合楼辅楼</option>");
            $("#ycdd").append("<option value='综合楼职工厅'>综合楼职工厅</option>");
            $("#ycdd").append("<option value='其他'>其他</option>");
        }


        if("接待用餐"==yctype && "早餐"==yczl ){
            $("#ycbz").html("<option value='' disabled selected>请选择用餐时间(必填)</option>");
            $("#ycbz").append("<option value='4'>4元</option>");

            $("#ycdd").html("<option value='' disabled selected>请填写用餐地点(必填)</option>");
            $("#ycdd").append("<option value='综合楼辅楼'>综合楼辅楼</option>");
            $("#ycdd").append("<option value='其他'>其他</option>");
        }


        if("接待用餐"==yctype && "中餐"==yczl ){
            $("#ycbz").html("<option value='' disabled selected>请选择用餐时间(必填)</option>");
            $("#ycbz").append("<option value='10'>10元</option>");
            $("#ycbz").append("<option value='15'>15元</option>");

            $("#ycdd").html("<option value='' disabled selected>请填写用餐地点(必填)</option>");
            $("#ycdd").append("<option value='综合楼来宾厅'>综合楼来宾厅</option>");
            $("#ycdd").append("<option value='综合楼辅楼'>综合楼辅楼</option>");
            $("#ycdd").append("<option value='其他'>其他</option>");
        }

        if("接待用餐"==yctype && "晚餐"==yczl ){
            $("#ycbz").html("<option value='' disabled selected>请选择用餐时间(必填)</option>");
            $("#ycbz").append("<option value='10'>10元</option>");
            $("#ycbz").append("<option value='15'>15元</option>");

            $("#ycdd").html("<option value='' disabled selected>请填写用餐地点(必填)</option>");
            $("#ycdd").append("<option value='综合楼辅楼'>综合楼辅楼</option>");
            $("#ycdd").append("<option value='其他'>其他</option>");
        }
        if("接待用餐"==yctype && "夜点"==yczl ){
            $("#ycbz").html("<option value='' disabled selected>请选择用餐时间(必填)</option>");
            $("#ycbz").append("<option value='4'>4元</option>");
            $("#ycbz").append("<option value='6'>6元</option>");

            $("#ycdd").html("<option value='' disabled selected>请填写用餐地点(必填)</option>");
            $("#ycdd").append("<option value='综合楼辅楼'>综合楼辅楼</option>");
            $("#ycdd").append("<option value='其他'>其他</option>");
        }
    }


    function add(){
        var lfdwjry=$("#lfdwjry").val();
        if(''==lfdwjry){
            alert('请填写来访人员/事由');
            return
        }
        var a=$("#ycrq").val();
        if(''==a){
            alert('请选择用餐日期');
            return
        }
        var b=$("#ycsl").val();
        if(''==b){
            alert('请填写人数');
            return
        }

        var ycremark=$("#ycremark").val();
        if(''==ycremark){
            alert('请填写备注');
            return
        }

        var yczl=$("#yczl").val();
        if(''==yczl){
            alert('请选择用餐时间');
            return
        }

        var ycbz=$("#ycbz").val();
        if(''==ycbz){
            alert('请填写用餐标准');
            return
        }

        var ycdd=$("#ycdd").val();
        if(''==ycdd){
            alert('请填写用餐地点');
            return
        }

        $("#addform").submit();
    }

</script>
</body>
</html>