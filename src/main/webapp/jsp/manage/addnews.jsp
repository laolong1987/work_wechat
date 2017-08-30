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
</head>
<body style="padding: 5px;">
<table class="table_css" width="100%">
    <tr>
        <td class="hd">
            标题
            <em>*</em>
        </td>
        <td class="bd">
            <input name="title" type="text" id="title" style="width: 99%" maxlength="20"/>
        </td>
    </tr>
    <tr>
        <td class="hd">
            来源
            <em>*</em>
        </td>
        <td class="bd">
            <input name="source" type="text" id="source" style="width: 99%" maxlength="20"/>
        </td>
    </tr>

    <tr>
        <td class="hd">
            发布时间
            <em>*</em>
        </td>
        <td class="bd">
            <input type="text" id="txt1" readonly>
        </td>
    </tr>

    <tr>
        <td class="hd">
            顶部图片
        </td>
        <td class="bd">
            <input name="file" type="file" id="file" />
        </td>
    </tr>

    <tr>
        <td class="hd">
            描述
        </td>
        <td class="bd" width="auto" colspan="3">
            <textarea rows="5" style="width: 99%"></textarea>
        </td>
    </tr>
    <tr>
        <td class="hd"></td>
        <td class="bd">
            <input type="button" value="保存"
                   class="l-button l-button-submit" />
            <input type="button" value="提交"
                   class="l-button l-button-submit" />
        </td>
    </tr>
</table>
</body>
<jsp:include page="jscommon.jsp" />
<script type="text/javascript">
    $(function () {
        $("#txt1").ligerDateEditor({ showTime: true});

    });
</script>

</html>
