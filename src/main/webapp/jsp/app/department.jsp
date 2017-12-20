<%--
  Created by IntelliJ IDEA.
  User: gaoyang
  Date: 17/10/24
  Time: 下午11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>通讯录</title>
    <link href="${ctx}/css/style.css?v=4.1.0" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/css/weui.min.css">
    <link rel="stylesheet" href="${ctx}/css/jquery-weui.min.css">
    <link rel="stylesheet" href="${ctx}/css/mailList2.css">
</head>

<body class="gray-bg">
<input type="hidden" id="dname" name="dname">
<div id="indexPage">
    <div class="bar bar-footer pull-right">
        <span class="sel-btn">已选择: <strong>0</strong>人</span>
        <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary"  style="width: 90px;background-image: url(${ctx}/images/add/btn.png);
                background-size:100% 100%;
                color: white;" onclick='add();' />确认</a>
        <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_default"  style="width: 90px;" onclick='parent.window.closedepartment();'>取消</a>
    </div>
    <div class="content sel-con">
        <div class="weui-cells weui-cells_checkbox">
            <h3 class="list-item-tit">部门</h3>
            <div class="list-item1">

            </div>
        </div>
    </div>
</div>
<div id="selectPage" class="weui-popup__container">
    <div class="weui-popup__overlay"></div>
    <div class="weui-popup__modal">
        <header class="bar bar-nav">
            <a href="javascript:void(0)" class="back-btn close-popup"><返回</a>
            <h3 class="show-peo-num">已选择<strong></strong>人</h3>
        </header>
        <div class="content">
        </div>
    </div>
</div>
<div id="seachPage" class="weui-popup__container">
    <div class="weui-popup__overlay"></div>
    <div class="weui-popup__modal">
        <div class="bar bar-nav weui-search-bar" id="searchBar">
            <form class="weui-search-bar__form">
                <div class="weui-search-bar__box">
                    <i class="weui-icon-search weui-search-btn"></i>
                    <input type="seach" class="weui-search-bar__input" peoId="searchInputs" placeholder="搜索" >
                    <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
                </div>
            </form>
            <!-- <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a> -->
        </div>
        <div class="content seach-con">
            <div class="weui-cells weui-cells_checkbox list-con" style="padding-left: 0;">
            </div>
        </div>
    </div>
</div>
<script id="list_tpl" type="text/x-dot-template">
    {{for(var i = 0; i < it.childList.length; i ++){ }}
    <div class="list-item1-box">
        <label class="weui-cell weui-check__label items-input" z-indexs="1">
            <div class="weui-cell__hd">
                <input type="checkbox" name="checkbox" class="weui-check" peoId='{{=it.childList[i].id}}'>
                <i class="weui-icon-checked"></i>
            </div>
            <div class="weui-cell__bd">
                <p>{{=it.childList[i].name}}</p>
            </div>
            <i class="icon-arrow-l icons-btn rotate"></i>
        </label>
        <div class="list-con animete none">
            {{for(var j = 0; j < it.childList[i].childList.length; j ++){ }}
            <div class="list-item1-box">
                <label class="weui-cell weui-check__label items-input" z-indexs="2">
                    <div class="weui-cell__hd">
                        <input type="checkbox" name="checkbox" class="weui-check" peoId='{{=it.childList[i].childList[j].id}}'>
                        <i class="weui-icon-checked"></i>
                    </div>
                    <div class="weui-cell__bd">
                        <p>{{=it.childList[i].childList[j].name}}</p>
                    </div>
                    <i class="icon-arrow-l icons-btn rotate"></i>
                </label>
                <div class="list-con animete none">
                    {{for(var h = 0; h < it.childList[i].childList[j].childList.length; h ++){ }}
                    <div class="list-item1-box">
                        <label class="weui-cell weui-check__label items-input" z-indexs="3">
                            <div class="weui-cell__hd">
                                <input type="checkbox" name="checkbox" class="weui-check" peoId='{{=it.childList[i].childList[j].childList[h].id}}'>
                                <i class="weui-icon-checked"></i>
                            </div>
                            <div class="weui-cell__bd">
                                <p>{{=it.childList[i].childList[j].childList[h].name}}</p>
                            </div>
                            <i class="icon-arrow-l icons-btn rotate"></i>
                        </label>
                        <div class="list-con animete none">
                            {{for(var e = 0; e < it.childList[i].childList[j].childList[h].userList.length; e ++){ }}
                            <label class="weui-cell weui-check__label item-input">
                                <div class="weui-cell__hd">
                                    <input type="checkbox" name="checkbox" class="weui-check" peoId='{{=it.childList[i].childList[j].childList[h].userList[e].id}}'>
                                    <i class="weui-icon-checked"></i>
                                </div>
                                <div class="weui-cell__bd touxiang">
                                    <img src="${ctx}/images/add/user.png">
                                </div>
                                <div class="weui-cell__bd">
                                    <p>{{=it.childList[i].childList[j].childList[h].userList[e].realName}}</p>
                                </div>
                                <div class="weui-cell__ft">{{=it.childList[i].childList[j].childList[h].name}}</div>
                            </label>
                            {{ } }}
                        </div>
                    </div>
                    {{ } }}
                    {{for(var h = 0; h < it.childList[i].childList[j].userList.length; h ++){ }}
                    <label class="weui-cell weui-check__label item-input">
                        <div class="weui-cell__hd">
                            <input type="checkbox" name="checkbox" class="weui-check" peoId='{{=it.childList[i].childList[j].userList[h].id}}'>
                            <i class="weui-icon-checked"></i>
                        </div>
                        <div class="weui-cell__bd touxiang">
                            <img src="${ctx}/images/add/user.png">
                        </div>
                        <div class="weui-cell__bd">
                            <p>{{=it.childList[i].childList[j].userList[h].realName}}</p>
                        </div>
                        <div class="weui-cell__ft">{{=it.childList[i].childList[j].name}}</div>
                    </label>
                    {{ } }}
                </div>
            </div>
            {{ } }}
            {{for(var j = 0; j < it.childList[i].userList.length; j ++){ }}
            <label class="weui-cell weui-check__label item-input">
                <div class="weui-cell__hd">
                    <input type="checkbox" name="checkbox" class="weui-check" peoId='{{=it.childList[i].userList[j].id}}'>
                    <i class="weui-icon-checked"></i>
                </div>
                <div class="weui-cell__bd touxiang">
                    <img src="${ctx}/images/add/user.png">
                </div>
                <div class="weui-cell__bd">
                    <p>{{=it.childList[i].userList[j].realName}}</p>
                </div>
                <div class="weui-cell__ft">{{=it.childList[i].name}}</div>
            </label>
            {{ } }}
        </div>
    </div>
    {{ } }}

    {{for(var i = 0; i < it.userList.length; i ++){ }}
    <label class="weui-cell weui-check__label item-input">
        <div class="weui-cell__hd">
            <input type="checkbox" name="checkbox" class="weui-check" peoId='{{=it.userList[i].id}}'>
            <i class="weui-icon-checked"></i>
        </div>
        <div class="weui-cell__bd touxiang">
            <img src="${ctx}/images/add/user.png">
        </div>
        <div class="weui-cell__bd">
            <p>{{=it.userList[i].realName}}</p>
        </div>
        <div class="weui-cell__ft">{{=it.name}}</div>
    </label>
    {{ } }}
</script>
<script id="list_tpl1" type="text/x-dot-template">
    {{~it:val:key}}
    <div class="list-con yxzr" style="padding-left: 0;background: #fff;">
        <label class="weui-cell weui-check__label" peoId="{{=val.id}}">
            <div class="weui-cell__bd touxiang">
                <img src="${ctx}/images/add/user.png">
            </div>
            <div class="weui-cell__bd">
                <p>{{=val.fullname}}</p>
            </div>
            <div class="weui-cell__ft">教学部
                <i class="weui-icon-cancel"  peoId="{{=val.id}}" style="margin-top: -3px; color:#b3b3b3"></i>
            </div>
        </label>
    </div>

    {{~}}
</script>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script>
    // 判断是否为pc还是移动端。true为移动端
    function detectmob() {
        if( navigator.userAgent.match(/Android/i)
                || navigator.userAgent.match(/webOS/i)
                || navigator.userAgent.match(/iPhone/i)
                || navigator.userAgent.match(/iPad/i)
                || navigator.userAgent.match(/iPod/i)
                || navigator.userAgent.match(/BlackBerry/i)
                || navigator.userAgent.match(/Windows Phone/i)
        ){
            return true;
        }
        else {
            return false;
        }
    }
    if(!detectmob()) {
        $("#selectPage").removeClass('weui-popup__container');
        $("#selectPage .weui-popup__modal").css({
            "-webkit-transform": "none",
            "-moz-transform": "none",
            "-ms-transform": "none",
            "transform": "none"
        })
        $("#seachPage").css({
            "z-index": 120
        })
        $("#selectPage .weui-popup__overlay").addClass('hide');
        $(".sel-btn,.back-btn").addClass('hide');
        $(".gray-bg").css({
            "position":"relative",
            "width": 740,
            "height": 600,
            "margin": "auto"
        })
        $("#indexPage").css({
            "width": 370
        })
        $("#selectPage").css({
            "width": 370,
            "position": "absolute",
            "height": "100%",
            "top":0,
            "right":0
        })
    }

    function add(){
        parent.window.adddepartment($("#dname").val());
    }
</script>
<script src="${ctx}/js/app/icheck.min.js"></script>
<script src="${ctx}/js/app/jquery-weui.js"></script>
<script src="${ctx}/js/app/doT.min.js"></script>
<script src="${ctx}/js/app/mailList2.js"></script>
</body>

</html>
