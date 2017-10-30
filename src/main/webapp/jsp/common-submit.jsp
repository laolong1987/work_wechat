<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/17
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="info-row mgt-20">
  <c:forEach var="item" items="${noticeList}" varStatus="status">
    <div class="process-${fn:length(noticeList)-status.index} clearfloat">
      <div class="flow"></div>
      <div
        class="process-text">${item.receiverName} ${item.noticeType} ${item.action}</div>
      <div class="process-date">${item.processTime}</div>
    </div>

  </c:forEach>
</div>
<c:if test="${approval}">
  <c:choose>

    <c:when test="${fn:length(noticeList)==2}">
      <div class="process-button ">
        <c:forEach var="item" items="${eventList}" varStatus="status">
          <c:if test="${item.event eq 'FormNext'}">
            <div class="agree approval"
                 data="${item.event}">${item.name}</div>
          </c:if>
          <c:if test="${item.event eq 'FormCanceled'}">
            <div class="refuse approval"
                 data="${item.event}">${item.name}</div>
          </c:if>
        </c:forEach>
      </div>
    </c:when>
    <c:otherwise>
      <div class="process-button ">
        <c:forEach var="item" items="${eventList}" varStatus="status">
          <c:if test="${status.index%2==0}">
            <div class="red-btn approval"
                 data="${item.event}">${item.name}</div>
          </c:if>
          <c:if test="${status.index%2==1}">
            <div class="blue-btn approval"
                 data="${item.event}">${item.name}</div>
          </c:if>

        </c:forEach>
      </div>
    </c:otherwise>

  </c:choose>
  <div class="approach-reason clearfloat f-dn" id="refuse-reason">
    <div class="tip">请填写审批意见：</div>
    <textarea class="reason" id="suggest"></textarea>

    <div class="close">取消</div>
    <div class="submit">提交</div>

  </div>
  <form id="approval-sbmt" action="" method="post">
    <input type="hidden" name="event" id="event" value="">
    <input type="hidden" name="content" id="content" value="">
    <input type="hidden" name="templateId" id="templateId"
           value="${templateId}">
    <input type="hidden" name="dataId" id="dataId" value="${dataId}">
    <input type="hidden" name="stateCaption" id="stateCaption"
           value="${stateCaption}">
    <input type="hidden" name="sendBy" id="sendBy" value="${sentBy}">
  </form>
</c:if>
</div>
<div class="up-loading">
  <img alt="loading" src="http://cache.shchengdian.com/uploading.gif">
</div>
<script type="text/javascript" src='../../../js/jquery.min.js'></script>
<script>
  $(function () {
    var editField='${editfields}';
    $(".approval").on("click", function () {
      $("#refuse-reason").removeClass("f-dn");
      var event = $(this).attr("data")
      $("#suggest").val($(this).text());
      $("#event").val(event);
    })

    $(".close").on("click", function () {
      $("#refuse-reason").addClass("f-dn");


    })
    $(".submit").on("click", function () {

      $("#content").val(suggest);
      var inputs = $(".edit");
      for (var i = 0; i < inputs.length; i++) {
        $("#approval-sbmt").append('<input type="hidden" name="' + inputs[i].name + '" value="' + inputs[i].value + '"/>')
      }
      $(".up-loading").css("display", "block")
      $.ajax({
        url: "<%=webRoot%>/approval/doApproval",
        type: "POST",
        data: $("#approval-sbmt").serializeJson(),
        success: function (data) {
          if (data == 'success') {
            $(".up-loading").css("display", "none")
            alert("审批成功")
            window.location.reload(true);
          }
        }
      })
    })

    $(":input").focus();
    $(".refuse").on('touchstart', function () {
      $(this).css("background", "linear-gradient(135deg, #ff0000, #d60000)");
    });
    $(".refuse").on('touchend', function () {
      $(this).attr("style", "");
    });
    $(".agree").on('touchstart', function () {
      $(this).css("background", "linear-gradient(135deg, #00d3ff, #0095d2)");
    });
    $(".agree").on('touchend', function () {
      $(this).attr("style", "");
    });
    $(".close").on('touchstart', function () {
      $(this).css("background", "linear-gradient(135deg, #484848, #151515)");
    });
    $(".close").on('touchend', function () {
      $(this).attr("style", "");
    });
    $(".submit").on('touchstart', function () {
      $(this).css("background", "linear-gradient(135deg, #00ffff, #00aad5)");
    });
    $(".submit").on('touchend', function () {
      $(this).attr("style", "");
    });

    $("[attr-name]").each(function () {
      if(editField!=null || editField!=''){
        var attrName = $(this).attr("attr-name");
        if (editField.match(attrName) != null) {
          if(attrName=='ZBRY' || attrName=='YCRY'|| attrName=='XZDYRY'){
             var node = '<input class="attr-value f-fl edit"  name="'+attrName+' id="usernames" type="text" name="' + attrName + '" value="" onclick="opendepartment()"  placeholder="请选择……"> ';
              $(this).parent().append(node);
              $(this).hide();
          }else{
            var node = '<input class="attr-value f-fl edit" type="text" name="' + attrName + '" value="" placeholder="请输入……"> ';
            $(this).parent().append(node);
            $(this).hide();
          }


        }
      }

    })

  })

  $.fn.serializeJson = function () {
    var serializeObj = {};
    var array = this.serializeArray();
    // var str=this.serialize();
    $(array).each(function () { // 遍历数组的每个元素
      if (serializeObj[this.name]) { // 判断对象中是否已经存在 name，如果存在name
        if ($.isArray(serializeObj[this.name])) {
          serializeObj[this.name].push(this.value); // 追加一个值 hobby : ['音乐','体育']
        } else {
          // 将元素变为 数组 ，hobby : ['音乐','体育']
          serializeObj[this.name] = [serializeObj[this.name], this.value];
        }
      } else {
        serializeObj[this.name] = this.value; // 如果元素name不存在，添加一个属性 name:value
      }
    });
    return serializeObj;
  };


</script>
