<%--
  Created by IntelliJ IDEA.
  User: gaoyang
  Date: 16/3/6
  Time: 下午12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head.jsp"%>
<html>
<head>
    <title>患者管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<script type="text/javascript">
    var gridManager = null;
    var saveForm = null;
    var saveWindow = null;
    var assignWindow = null;
    var noteWindow = null;
    var typeData = null;
    var icon = '${ctx}/ligerUI/skins/icons/pager.gif';
    var condition = { fields: [{ name: 'name', label: 'name',width:90,type:'text' }] };
    var role=null;
    $(function() {
        $("#searchbtn").ligerButton({
            click : function() {
                search();
            }
        });

        setGrid();

        $("#pageloading").hide();
    });

    function setGrid(){
        //表格
        gridManager = $("#maingrid").ligerGrid({
            columns : [
                {
                    display : '员工编号',
                    name : 'Ygbh',
                    align : 'center',
                    width : 100,
                    minWidth : 30
                },{
                    display : '员工姓名',
                    name : 'Ygxm',
                    align : 'center',
                    width : 100
                }, {
                    display : '组织单位名称',
                    name : 'Zzdwmc',
                    align : 'center',
                    width : 200
                }, {
                    display : '人员权限',
                    name : 'status',
                    minWidth : 10,
                    align : 'center',
                    width : 100,
                    render : function(rowdata, rowindex, value) {
                        if(rowdata.status==0){
                            return "在职(有OA权限)"
                        }else if(rowdata.status==1){
                            return "离职";
                        }else if(rowdata.status==2){
                            return "在职(无OA权限)";
                        }else{
                            return ""
                        }
                }

                },{
                    display : '新闻编辑权限',
                    name : 'status',
                    minWidth : 10,
                    align : 'center',
                    width : 100,
                    render : function(rowdata, rowindex, value) {
                        if(null!=rowdata.aid){
                            return "有权限"
                        }else{
                            return "无权限"
                        }
                }
                }
            ],
            pageSize : 20,
            pageSizeOptions:[10,20,30,40,50],
            url : "${ctx}/admin/employee/searchlist",
            rownumbers : true,
            checkbox : true,
            selectRowButtonOnly : true,
            isScroll : true
            ,toolbar: {
                items: [{
                    id: 'add',
                    text: '授权编辑新闻',
                    click: itemclick,
                    img : '${ctx}/ligerUI/skins/icons/add.gif'
                }, {
                    id: 'delete',
                    text: '取消授权编辑新闻',
                    click: itemclick,
                    img : '${ctx}/ligerUI/skins/icons/close.png'
                }]
            }
        });
    }



    function search(){
        var parms = $("#queryForm").serializeJson();
        gridManager.set("parms", parms);
        gridManager.loadData();
    }

    function renderDescription(rowdata, index, value) {
        if(value != null) {
            return "<div title='" + value + "'>" + value + "<div>";
        }
    }

    function showWindow(){
        if (saveWindow == null) {
            saveWindow = $.ligerDialog.open({
                target : $("#addWindow"),
                width : 600,
                height : 'auto',
                isResize : true
            });
        }
        $("#saveForm")[0].reset();
        saveForm.hideAllFieldError();
        //$("#saveForm").resetStyle();
        saveWindow.show();
    }

    function itemclick(item) {
        if (item.id) {
            switch (item.id) {
                case "add":
                    var data = gridManager.getCheckedRows();
                    if (data.length == 0) {
                        $.ligerDialog.waitting('请选择行');
                        setTimeout(function() {
                            $.ligerDialog.closeWaitting();
                        }, 500);
                    } else {
                        $.ligerDialog.confirm('确认要授权?', function(yes) {
                            if (yes) {
                                addData(data);
                            }
                        });
                    }
                    return;
                case "delete":
                    var data = gridManager.getCheckedRows();
                    if (data.length == 0) {
                        $.ligerDialog.waitting('请选择行');
                        setTimeout(function() {
                            $.ligerDialog.closeWaitting();
                        }, 500);
                    } else {
                        $.ligerDialog.confirm('确认要取消授权?', function(yes) {
                            if (yes) {
                                removeData(data);
                            }
                        });
                    }
                    return;
                case "export":
                    exportExcel(gridManager, $("#queryForm"), '${ctx}');
                    return ;
            }
        }
    }

    function closeWindow() {
        saveWindow.hide();
    }

    function removeData(data) {
        $.ajax({
            type : "POST",
            url : "remove",
            data : JSON.stringify(data),
            contentType : "application/json; charset=utf-8",
            dataType : "text",
            success : function(result) {
                if (result == 'success') {
                    search();
                    $.ligerDialog.waitting('操作成功');
                    setTimeout(function() {
                        $.ligerDialog.closeWaitting();
                    }, 500);
                } else {
                    $.ligerDialog.warn(result);
                }
            }
        });
    }
    function addData(data) {
        $.ajax({
            type : "POST",
            url : "add",
            data : JSON.stringify(data),
            contentType : "application/json; charset=utf-8",
            dataType : "text",
            success : function(result) {
                if (result == 'success') {
                    search();
                    $.ligerDialog.waitting('操作成功');
                    setTimeout(function() {
                        $.ligerDialog.closeWaitting();
                    }, 500);
                } else {
                    $.ligerDialog.warn(result);
                }
            }
        });
    }

    function save() {
        if (saveForm.valid()) {
            $("#pageloading").show();
            var params = saveForm.getData();
            $.ajax({
                type : "POST",
                url : "savepatient",
//                data : JSON.stringify(params),
                data : params,
                dataType : "text",
                success : function(result) {
                    if (result == 'success') {
                        search();
                        saveWindow.hide();
                        $.ligerDialog.waitting('操作成功');
                        setTimeout(function() {
                            $.ligerDialog.closeWaitting();
                        }, 500);
                    } else {
                        $.ligerDialog.warn(result);
                    }
                    $("#pageloading").hide();
                }
            });
        }
    }

    function updatestatus(id,status) {
        $.ajax({
            type : "POST",
            url : "updatestatus",
            data : {'id':id,'status':status},
            dataType : "text",
            success : function(result) {
                if (result == 'success') {
                    search();
                    $.ligerDialog.waitting('success');
                    setTimeout(function() {
                        $.ligerDialog.closeWaitting();
                    }, 500);
                } else {
                    $.ligerDialog.warn(result);
                }
            }
        });
    }

    function getGridOptions()
    {
        var options = {
            columns: [
//                { display: 'id', name: 'id', width: 100 },
                { display: '名称', name: 'name', width: 100 }
            ], switchPageSizeApplyComboBox: false,
            url: "${ctx}/doctor/searchlist",
            pageSize: 10
        };
        return options;
    }

    function showassign(id){
        $("#txt2").ligerComboBox({
            width: 250,
            slide: false,
            selectBoxWidth: 375,
            selectBoxHeight: 365,
            valueField: 'id',
            textField: 'name',
            grid: getGridOptions(),
            condition: condition
        });

        if (assignWindow == null) {
            assignWindow = $.ligerDialog.open({
                title : "assign",
                target : $("#assignWindow"),
                width : 400,
                height : 'auto',
                isResize : true
            });
        }
        $("#upassignid").val(id);
        assignWindow.show();
    }

    function assign(){
        var upid = $("#upassignid").val();
        var userid = $("#txt2_val").val();

        $.ajax({
            type : "POST",
            url : "updateuserid",
            data : {'id':upid,'userid':userid},
            dataType : "text",
            success : function(result) {
                if (result == 'success') {
                    search();
                    $.ligerDialog.waitting('success');
                    setTimeout(function() {
                        $.ligerDialog.closeWaitting();
                    }, 500);
                } else {
                    $.ligerDialog.warn(result);
                }
            }
        });
        closeassignWindow();
    }


    function closeassignWindow() {
        assignWindow.hide();
    }
    function closenoteWindow() {
        noteWindow.hide();
    }

    function shownote(data){
        if (noteWindow == null) {
            noteWindow = $.ligerDialog.open({
                title : "note",
                target : $("#noteWindow"),
                width : 600,
                height : 'auto',
                isResize : true
            });
        }
        $("#upnoteid").val(data.id);
        $("#note").val(data.note);
        noteWindow.show();
    }

    function addnote(){
        var upid = $("#upnoteid").val();
        var note = $("#note").val();

        $.ajax({
            type : "POST",
            url : "updatenote",
            data : {'id':upid,'note':note},
            dataType : "text",
            success : function(result) {
                if (result == 'success') {
                    search();
                    $.ligerDialog.waitting('success');
                    setTimeout(function() {
                        $.ligerDialog.closeWaitting();
                    }, 500);
                } else {
                    $.ligerDialog.warn(result);
                }
            }
        });
        closenoteWindow();
    }
</script>
</head>
<body style="padding: 5px;">
<div class="l-loading" style="display:block" id="pageloading"></div>
<form id="queryForm">
    <div class="l-panel-search">
        <div class="l-panel-search-item">员工编号:</div>
        <div class="l-panel-search-item">
            <input type="text" id="queryname" name="queryname"  class="liger-textbox" />
        </div>
        <div class="l-panel-search-item">员工姓名:</div>
        <div class="l-panel-search-item">
            <input type="text" id="querycountry" name="querycountry"  class="liger-textbox" />
        </div>
        <div class="l-panel-search-item">员工权限:</div>
        <div class="l-panel-search-item">
            <select name="queryservicetype" id="queryservicetype" >
                <option value="0">All</option>
                <option value="1">在职</option>
                <option value="2">离职</option>
            </select>

        </div>
        <div class="l-panel-search-item">新闻权限:</div>
        <div class="l-panel-search-item">
            <select name="querystatus" id="querystatus" >
                <option value="0">All</option>
                <option value="1">有</option>
                <option value="2">无</option>

            </select>
        </div>
        <div class="l-panel-search-item">
            <input type="button" id="searchbtn" value="search" />
        </div>
    </div>
</form>



<!-- 表格 -->
<div id="maingrid" style="margin:0; padding:0"></div>
<div style="display:none;"></div>
<div id="addWindow" style="width:99%; margin:3px; display:none;">
    <div class="l-dialog-body" style="width: 100%;">
        <form id="saveForm"></form>
        <div class="l-dialog-buttons">
            <div class="l-dialog-buttons-inner">
                <div class="l-dialog-btn">
                    <div class="l-dialog-btn-l"></div>
                    <div class="l-dialog-btn-r"></div>
                    <div class="l-dialog-btn-inner" onclick="closeWindow();">关闭</div>
                </div>
                <div class="l-clear"></div>
            </div>
        </div>
    </div>
</div>


<div id="assignWindow" style="width:99%; margin:3px; display:none;">
    <div class="l-dialog-body" style="width: 100%;">
        <input type="text" id="txt2" />
        <input type="hidden" id="upassignid" name="upassignid" />
        <div class="l-dialog-buttons">
            <div class="l-dialog-buttons-inner">
                <div class="l-dialog-btn">
                    <div class="l-dialog-btn-l"></div>
                    <div class="l-dialog-btn-r"></div>
                    <div class="l-dialog-btn-inner" onclick="closeassignWindow();">取消</div>
                </div>
                <div class="l-dialog-btn">
                    <div class="l-dialog-btn-l"></div>
                    <div class="l-dialog-btn-r"></div>
                    <div class="l-dialog-btn-inner" onclick="assign();">确定</div>
                </div>
                <div class="l-clear"></div>
             </div>
        </div>
    </div>
</div>



<div id="noteWindow" style="width:99%; margin:3px; display:none;">
    <div class="l-dialog-body" style="width: 100%;">
        <textarea cols="100" rows="15" class="l-textarea"  style="width: 99%" id="note" name="note"></textarea>
        <input type="hidden" id="upnoteid" name="upnoteid" />
        <div class="l-dialog-buttons">
            <div class="l-dialog-buttons-inner">
                <div class="l-dialog-btn">
                    <div class="l-dialog-btn-l"></div>
                    <div class="l-dialog-btn-r"></div>
                    <div class="l-dialog-btn-inner" onclick="closenoteWindow();">取消</div>
                </div>
                <div class="l-dialog-btn">
                    <div class="l-dialog-btn-l"></div>
                    <div class="l-dialog-btn-r"></div>
                    <div class="l-dialog-btn-inner" onclick="addnote();">确定</div>
                </div>
                <div class="l-clear"></div>
            </div>
        </div>
    </div>
</div>

</body>
</html>