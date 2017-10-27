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
    <title></title>
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <iframe src="${ctx}/work/department" style="width: 100%;height: 600px">
                </iframe>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src='${ctx}/js/app/jquery.min.js'></script>
<script type="text/javascript" src='${ctx}/js/bootstrap.min.js'></script>
<script>
    function opendepartment(){
        $('#myModal').modal('show');
    }

    function closedepartment(){
        $('#myModal').modal('hide');
    }

    function adddepartment(dname){
        closedepartment();
        $("#usernames").val(dname);
    }

</script>
</body>
</html>