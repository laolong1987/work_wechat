<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/24
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="info-row subject ">

  <div class="clearfloat">
    <div class="attr-name f-fl vehicle-middle ">类型：</div>
    <div class="attr-value f-fl vehicle-middle ">${object.subject}</div>
  </div>
  <div class="clearfloat">
    <div class="attr-name f-fl vehicle-middle ">单号：</div>
    <div class="attr-value f-fl vehicle-middle ">${object.orderNum}</div>
  </div>
  <c:if test="${aprovalSeal == 1}">
    <div class="accept-seal"></div>
  </c:if>
  <c:if test="${aprovalSeal == 2}">
     <div class="refuse-seal"></div>
   </c:if>

</div>

