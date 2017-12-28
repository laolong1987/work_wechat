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
    <title>客饭申请单</title>
    <%@ include file="csscommon.jsp" %>
    <style>

        body{
                  /*  background-image: url(${ctx}/images/add/bg.jpg);*/
          	  background-color: #efefef
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
        .tip{
            font-family: PingFangSC;
            font-size: 12px;
            line-height: 1.67;
            text-align: center;
            color: #f40000;
            margin-top: 10px;
        }
        .addbtn{
            width: 100%;height: 50px;;
            background-image: url(${ctx}/images/add/btn.png);
            background-size:100% 100%;
            color: white;
        }

        .moren{
            color:  #aab2bd;
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
        input[type="radio"] {
            display: none;
        }
        .rla {
            padding-left: 20px;
            cursor: pointer;
            background: url(${ctx}/images/add/unchoose.png) no-repeat left top;
            background-size:27% 100%;
        }
        .rla.cr{
            background: url(${ctx}/images/add/choose.png) no-repeat left top;
            background-size:27% 100%;
        }
        .touming:focus{outline:none !important;}
    </style>
</head>
<body>

<div class="container">
  <div class="row content "  style="margin-top: 8px">
	 <button type="button" class="btn btn-default btn-primary pull-right"  style="margin-top: 10px;margin-right: 20px;" onclick="tolist()"><i class="fa fa-list"></i>  查看列表</button>
 </div>
    <div class="row top" style="margin-top: 20px">
        <div class="col-xs-4">
            <span class="ziti1"><i class="fa fa-user"></i> ${name}</span>
        </div>
        <div class="col-xs-8 text-right">
            <span class="ziti2"><i class="fa fa-users"></i> 部门:</span>
            <span class="ziti1">${dept}</span>
        </div>
    </div>
    <form  id="addform" action="createguestmeal" name="addform" method="post" >

    <div class="row content2">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-user-circle-o"></i> 人员/事由</label>
        <div class="col-xs-8">
            <textarea class="touming" rows="5" name="lfdwjry" id="lfdwjry" placeholder="请输入来访人员"  required></textarea>
        </div>
    </div>
 <div class="row content">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-calendar"></i> 用餐日期</label>
        <div class="col-xs-6">
            <input  type="text" class="form_datetime touming" placeholder="请选择用餐日期" name="ycrq" id="ycrq" value="" required>
        </div>
        <label class="col-xs-2 control-label lab lab3" for="ycrq">&nbsp;&nbsp;
        </label>
    </div>


    <div class="row content">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-users"></i> 用餐人数</label>
        <div class="col-xs-6">
            <input  type="number" class="touming" placeholder="请填写人数"  value="1" id="ycsl" name="ycsl" required>
        </div>
        <label class="col-xs-2 control-label lab">客
        </label>
    </div>

    <div class="row content2">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-comment-o"></i> 备注</label>
        <div class="col-xs-8">
            <textarea class="touming" rows="5" name="ycremark" id="ycremark" placeholder=""  required></textarea>
        </div>
    </div>


    <div class="row" style="margin-top: 10px">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-check-circle-o"></i> 用餐种类</label>
        <div class="col-xs-8 lab">
            <input type="radio" name="yctype" id="optionsRadios1"  class="radio"  onclick="addoption()" value="工作用餐">
            <label for="optionsRadios1" class="rla">工作用餐</label>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="yctype" id="optionsRadios2" class="radio"  onclick="addoption()" style="margin-left: 20px" value="接待用餐" checked>
            <label for="optionsRadios2" class="cr rla">接待用餐</label>
        </div>
    </div>

    <div class="row content">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-clock-o"></i> 用餐时间</label>
        <div class="col-xs-6">
            <select class="touming" id="yczl" name="yczl" onchange="addoption()" >
            <%--<option value="" class="moren" disabled selected>请选择用餐时间</option>--%>
            <option value="早餐">早餐</option>
            <option value="中餐">中餐</option>
            <option value="晚餐">晚餐</option>
            <option value="夜点">夜点</option>
        </select>
        </div>
        <label class="col-xs-2 control-label lab lab3" for="yczl">&nbsp;&nbsp;
        </label>
    </div>

    <div class="row content">
            <label class="col-xs-4 control-label lab lab2"><i class="fa fa-cny"></i> 用餐标准</label>
            <div class="col-xs-6">
            <select class="touming" name="ycbz" id="ycbz">
                <%--<option value="" class="moren" disabled selected>请选择用餐标准</option>--%>
            </select>
        </div>
        <label class="col-xs-2 control-label lab lab3" for="ycbz">&nbsp;&nbsp;
        </label>
    </div>

    <div class="row content">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-bank"></i> 用餐地点</label>
        <div class="col-xs-6">
            <select class="touming" name="ycdd" id="ycdd">
                <%--<option value="" class="moren" disabled selected>请选择用餐地点</option>--%>
                <option value='综合楼辅楼'>综合楼辅楼</option>
            </select>
        </div>
        <label class="col-xs-2 control-label lab lab3" for="ycdd">&nbsp;&nbsp;
        </label>
    </div>
   
</form>
    <div class="text-center tip" style="margin-bottom: 20px">注：中餐请于1点前提交申请，晚餐请于16点前提交申请</div>
  
  
<div  class="span12" style="margin-bottom: 20px"> 	 
     <a href="#" class="btn  btn-primary btn-block  btn-lg" onclick="add()"><i class="fa fa-check"></i> 提交审批</a>
</div>

</div>
<script type="text/javascript" src='${ctx}/js/app/jquery.min.js'></script>
<script type="text/javascript" src='${ctx}/js/bootstrap.min.js'></script>
<script type="text/javascript"
        src="http://cache.shchengdian.com/plugins/mobiscroll.custom-3.0.0-beta2.min.js"
        charset="UTF-8"></script>
<script>
    var ctx='${ctx}';
</script>
<script type="text/javascript" src="${ctx}/js/work/addguestmeal.js"></script>
</body>
<jsp:include page="wxjs.jsp" />
</html>