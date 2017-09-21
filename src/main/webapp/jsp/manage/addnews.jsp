<%--
  Created by IntelliJ IDEA.
  User: gaoyang
  Date: 17/8/27
  Time: 下午11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head.jsp"%>
<html>
<head>
    <title>编辑新闻</title>
    <%@ include file="csscommon.jsp"%>
    <link rel="stylesheet" href="${ctx}/css/default.css" />
    <link rel="stylesheet" href="${ctx}/css/prettify.css" />
    <link rel="stylesheet" href="${ctx}/css/myupload.css" />
</head>
<body style="padding: 5px;">
<form action="addnews" method="post" id="addform" name="addform">
    <input type="hidden" id="fileid" name="fileid" value="${news.topimg}" >
    <input type="hidden" id="status" name="status" >
    <input type="hidden" id="id" name="id" value="${news.id}">
<table class="table_css" width="100%">
    <tr>
        <td class="hd">
            标题
            <em>*</em>
        </td>
        <td class="bd">
            <input name="title" type="text" id="title" style="width: 99%" maxlength="20" value="${news.title}"/>
        </td>
    </tr>
    <tr>
        <td class="hd">
            来源
            <em>*</em>
        </td>
        <td class="bd">
            <input name="source" type="text" id="source" style="width: 99%" maxlength="20" value="${news.source}"/>
        </td>
    </tr>

    <tr>
        <td class="hd">
            发布时间
            <em>*</em>
        </td>
        <td class="bd">
            <input type="text" id="puttime" name="puttime" value="${puttime}"  readonly>
        </td>
    </tr>

    <tr>
        <td class="hd">
            顶部图片
        </td>
        <td class="bd">
            <div class="up_box">
                <div class="add_pic_up">
                    <ul>
                        <li class="last_li"
                        <c:if test="${news.topimg ne null && news.topimg ne ''}">
                            style="display:none"
                            </c:if>
                        >
                            <div class="ap_r">+
                                <input type="file" onchange="uploadfile()" id="file" name="file" >
                            </div>
                        </li>
                        <div id="fileui1">
                            <c:if test="${news.topimg ne null && news.topimg ne ''}">
                                <li id="li_${news.topimg}">
                                    <a href="javascript:;"  onclick="removefile('${news.topimg}')"></a>
                                    <img src="../../file/doDownload/${news.topimg}" onclick="openfile('${news.topimg}')" >
                                </li>
                            </c:if>
                        </div>
                    </ul>

                </div>
            </div>
        </td>
    </tr>

    <tr>
        <td class="hd">
            内容
        </td>
        <td class="bd" width="auto" colspan="3">
            <%--<textarea rows="5" style="width: 99%"></textarea>--%>
            <textarea name="content" id="content" cols="100" rows="8" style="width:99%;height:200px;visibility:hidden;"></textarea>
        </td>
    </tr>
    <tr>
        <td class="hd"></td>
        <td class="bd">
            <input type="button" value="保存"
                   class="l-button l-button-submit" onclick="addnews('0')" />
            <input type="button" value="提交"
                   class="l-button l-button-submit" onclick="addnews('1')" />
        </td>
    </tr>
</table>
</form>
</body>
<jsp:include page="jscommon.jsp" />
<script src="${ctx}/js/kindeditor/kindeditor-all.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor/zh-CN.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor/prettify.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/myuploadfile.js"></script>

<script type="text/javascript">
    var editor1;
    var content='${news.content}';



    KindEditor.ready(function(K) {
            editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/css/prettify.css',
            <%--uploadJson : '${ctx}/file/kindeditoruploadfile',--%>
            <%--fileManagerJson : '${ctx}/file/kindeditoruploadfilemanager',--%>
            uploadJson : '${ctx}/jsp/manage/upload_json.jsp',
            fileManagerJson : '${ctx}/jsp/manage/upload_json.jsp',
            allowFileManager : true,
            afterCreate : function() {
                var self = this;
                K.ctrl(document, 13, function() {
                    self.sync();
                    document.forms['example'].submit();
                });
                K.ctrl(self.edit.doc, 13, function() {
                    self.sync();
                    document.forms['example'].submit();
                });
            }
        });
        prettyPrint();
        editor1.html(content);
    });

    $(function () {
        $("#puttime").ligerDateEditor({ showTime: true});
        var type='${type}';
        if(1==type){
            $.ligerDialog.success('提交成功');
        }
    });

    function addnews(status){
        $("#status").val(status);
        editor1.sync();
        $("#addform").submit();
    }
</script>

</html>
