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
    <title>新闻管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<script type="text/javascript">
    var gridManager = null;
    var saveForm = null;
    var saveWindow = null;
    var typeData = null;
    var icon = '${ctx}/ligerUI/skins/icons/pager.gif';

    $(function() {
        $("#searchbtn").ligerButton({
            click : function() {
                search();
            }
        });

        setGrid();
        $("#pageloading").hide();
    });

    function addnews()
    {
        var url='${ctx}/admin/news/showaddnews';
        top.f_addTab('addnews_0','编辑新闻',url);
    }
    function xiugai(id)
    {
        var url='${ctx}/admin/news/showaddnews?id='+id;
        top.f_addTab('addnews_'+id,'编辑新闻',url);
    }
    function yulan(id)
    {
        window.open("${ctx}/news/detailnews?id="+id);
    }
    function setGrid(){
        //表格
        gridManager = $("#maingrid").ligerGrid({
            columns : [
                {
                    display: '操作',
                    isSort: false,
                    isExport: false,
                    width: 80,
                    align : 'center',
                    render: function (rowdata, rowindex, value)
                    {
                        var xiugai="<a href='' onclick='xiugai("+rowdata.id+");return false'>修改</a>";
                        var yulan="<a href='' onclick='yulan("+rowdata.id+");return false'>预览</a>";
//                        var shanchu="<a href=''>删除</a>";
                        return xiugai+"&nbsp;&nbsp;&nbsp;"+yulan;
                    }
                },
                {
                    display : '标题',
                    name : 'title',
                    align : 'left'
                },{
                    display : '来源',
                    name : 'source',
                    align : 'left',
                    width : 150,
                    minWidth : 60
                }, {
                    display : '发布时间',
                    name : 'puttime1',
                    width : 150,
                    minWidth : 30,
                    align : 'center'
                },
                {
                    display : '状态',
                    name : 'status',
                    align : 'center',
                    width : 100,
                    render : function(rowdata, rowindex, value) {
                        if(rowdata.status==0){
                            return "草稿"
                        }else if(rowdata.status==1){
                            return "发布";
                        }else if(rowdata.status==2){
                            return "删除";
                        }else{
                            return ""
                        }
                    }
                }
            ],
            pageSize : 50,
            url : "${ctx}/admin/news/searchlist",
            rownumbers : true,
            checkbox : true,
            selectRowButtonOnly : true,
            isScroll : true,
            toolbar : {
                items : [ {
                    id : 'add',
                    text : '增加',
                    click : addnews,
                    img : '${ctx}/ligerUI/skins/icons/addpage.png'
                }, {
                    id : 'delete',
                    text : '删除',
                    click : itemclick,
                    img : '${ctx}/ligerUI/skins/icons/busy.gif'
                }]
            }
        });
    }


    function search(){
        var parms = $("#queryForm").serializeJson();
        gridManager.set("parms", parms);
        gridManager.loadData();
    }


    function itemclick(item) {
        if (item.id) {
            switch (item.id) {
                case "add":
                    var url='${ctx}/admin/news/list';
                    f_addTab('bj','编辑',url);
                    return;
                case "delete":
                    var data = gridManager.getCheckedRows();
                    if (data.length == 0) {
                        $.ligerDialog.waitting('请选择行');
                        setTimeout(function() {
                            $.ligerDialog.closeWaitting();
                        }, 500);
                    } else {
                        $.ligerDialog.confirm('确认要删除?', function(yes) {
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
                    $.ligerDialog.waitting('删除成功');
                    setTimeout(function() {
                        $.ligerDialog.closeWaitting();
                    }, 500);
                } else {
                    $.ligerDialog.warn(result);
                }
            }
        });
    }
</script>
</head>
<body style="padding: 5px;">
<div class="l-loading" style="display:block" id="pageloading"></div>
<form id="queryForm">
    <div class="l-panel-search">
        <div class="l-panel-search-item">标题</div>
        <div class="l-panel-search-item">
            <input type="text" id="title" name="title"  class="liger-textbox" />
        </div>
        <div class="l-panel-search-item">
            <input type="button" id="searchbtn" value="查询" />
        </div>
    </div>
</form>
<!-- 表格 -->
<div id="maingrid" style="margin:0; padding:0"></div>
<div style="display:none;"></div>
</body>
</html>