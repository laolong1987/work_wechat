<%--
  Created by IntelliJ IDEA.
  User: gaoyang
  Date: 17/8/30
  Time: 下午11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script>
    wx.config({
        beta: true,// 必须这么写，否则在微信插件有些jsapi会有问题
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '${appid}', // 必填，企业微信的corpID
        timestamp: '${timestamp}', // 必填，生成签名的时间戳
        nonceStr: '${noncestr}', // 必填，生成签名的随机串
        signature: '${sign}',// 必填，签名，见[附录1](#11974)
        jsApiList: ['hideAllNonBaseMenuItem'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });

    wx.ready(function(){
        wx.hideAllNonBaseMenuItem();
        handleFontSize();
    });

    function handleFontSize() { // 设置网页字体为默认大小
        WeixinJSBridge.invoke('setFontSizeCallback', { 'fontSize' : 0 }); // 重写设置网页字体大小的事件
        WeixinJSBridge.on('menu:setfont', function() {
            WeixinJSBridge.invoke('setFontSizeCallback', { 'fontSize' : 0 });
        });
    }
</script>