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
        .addbtn{
            width: 100%;height: 50px;;
            background-image: url(${ctx}/images/add/btn.png);
            background-size:100% 100%;
            color: white;
        }
        .topbut{
            position:fixed;top:0;margin:auto;left:0; right:0;
            background-color: #FFFFFF
            background-size:100% 100%;

            width: 100%;
            text-align: right;
            z-index: 999;
        }
      
        .topbut img{
            margin-right: 20px;
            width: 15px;
            margin-top: 4px;
        }

        input[type="radio"] {
            display: none;
        }
        .rla {
            padding-left: 40px;
            cursor: pointer;
            background: url(${ctx}/images/add/unchoose.png) no-repeat left top;
            background-size:28% 100%;
        }
        .rla.cr{
            background: url(${ctx}/images/add/choose.png) no-repeat left top;
            background-size:28% 100%;
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
    <form  id="addform" action="createcar" name="addform" method="post" >
    <div class="row content">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-phone"></i> 预定电话</label>
        <div class="col-xs-8">
            <input  type="text" class="touming" placeholder="请填写预定人电话(必填)" value="" id="ydrphone" name="ydrphone" required>
        </div>

    </div>

    <div class="row content">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-calendar"></i> 出发时间</label>
        <div class="col-xs-6">
            <input  type="text" class="form_datetime touming" placeholder="请选择出发时间" name="starttime" id="starttime" value="" required>
        </div>
        <label class="col-xs-2 control-label lab lab3" for="starttime">&nbsp;&nbsp;
        </label>
    </div>

    <div class="row content">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-calendar"></i> 结束时间</label>
        <div class="col-xs-6">
            <input  type="text" class="form_datetime touming" placeholder="请选择结束时间" name="endtime" id="endtime" value="" required>
        </div>
        <label class="col-xs-2 control-label lab lab3" for="endtime">&nbsp;&nbsp;
        </label>
    </div>

    <div class="row content2">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-users"></i> 乘车人员</label>
        <div class="col-xs-8">
            <%--<input  type="text" class="touming" placeholder="请选择乘车人员" id="usernames" onclick="opendepartment()" value="" required>--%>
            <textarea class="touming" rows="5" name="usernames" id="usernames" onclick="opendepartment('usernames')" placeholder="请选择(必填)"></textarea>
        </div>
        <%--<label class="col-xs-4 control-label lab lab3">请选择(必填)--%>
        <%--</label>--%>
    </div>
    	
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <iframe src="${ctx}/work/department" id="tree" name="tree" style="width: 100%;height: 85%;border: 0">
                </iframe>
            </div>
        </div>
    </div>
   </div>
		
    <div class="row content">
        <label class="col-xs-4 control-label lab lab2">等</label>
        <div class="col-xs-5">
            <input  type="text" class="touming" placeholder="请填写乘车人数"  value="" id="zrs" name="zrs" required>
        </div>
        <label class="col-xs-3 control-label lab">人
        </label>
    </div>


    <div class="row content">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-street-view"></i> 上车地点</label>
        <div class="col-xs-8">
            <input  type="text" class="touming" placeholder="请填写上车地点"  value=""  id="pickupaddress" name="pickupaddress" required>
        </div>
    </div>


    <div class="row" style="margin-top: 10px">
        <div class="col-xs-4">
        </div>
        <div class="col-xs-4">
            <input type="radio" class="radio" name="Sfwd" id="r1"  value="不是">
            <label class="rla" for="r1"> &nbsp;市内</label>
        </div>
        <div class="col-xs-4">
            <input type="radio" class="radio" name="Sfwd" id="r2"  value="是">
            <label class="rla" for="r2"> &nbsp;市外</label>

        </div>
    </div>

    <div class="row content">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-cab"></i> 目的地</label>
        <div class="col-xs-8">
            <input  type="text" class="touming" placeholder="请填写目的地"  value="" id="destination" name="destination" required>
        </div>
    </div>

    <div class="row content2">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-exclamation"></i> 车辆用途</label>
        <div class="col-xs-8">
            <textarea class="touming" rows="5" name="clyt" id="clyt" placeholder="请填写车辆用途(必填)"></textarea>
        </div>
    </div>

    <div class="row content2" style="margin-bottom: 10px">
        <label class="col-xs-4 control-label lab lab2"><i class="fa fa-comment-o"></i> 备注</label>
        <div class="col-xs-8">
            <textarea class="touming" rows="5" name="remark" id="remark" placeholder="如 1.是否往返 2.是否多目的地"></textarea>
        </div>
    </div>
</form>
  <div  class="span12" style="margin-bottom: 20px"> 	 
        <a href="#" class="btn  btn-primary btn-block  btn-lg" onclick="add()"><i class="fa fa-check"></i> 提交审批</a>
    </div>
 

</div>

<script type="text/javascript" src='${ctx}/js/app/jquery.min.js'></script>
<%--<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>--%>
<script type="text/javascript" src='${ctx}/js/bootstrap.min.js'></script>
<%--<script type="text/javascript" src='${ctx}/js/bootstrap-datetimepicker.min.js'></script>--%>
<%--<script type="text/javascript" src='${ctx}/js/bootstrap-datetimepicker.zh-CN.js'></script>--%>
<script type="text/javascript"
        src="http://cache.shchengdian.com/plugins/mobiscroll.custom-3.0.0-beta2.min.js"
        charset="UTF-8"></script>
<script>

 var ctx='${ctx}';
</script>
<script type="text/javascript" src="${ctx}/js/work/car.js"></script>
</body>
</html>