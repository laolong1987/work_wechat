/**
 * Created by gaoyang on 17/12/20.
 */

$('.form_datetime').mobiscroll().datetime({
    theme: 'Mobiscroll',
    lang: 'zh',
    display: 'center',
    min: new Date(2000, 1, 1),
    disabled: false
});

$(function () {
    $('.rla').click(function () {
        var radioId = $(this).attr('name');
        $('.rla').removeClass('cr');
        $(this).addClass('cr');
        $('input[type="radio"]').removeAttr('checked') && $('#' + radioId).attr('checked', 'checked');
    });
    var p = 0, t = 0;
    $(window).scroll(function (e) {
        p = $(this).scrollTop();
        if (t <= p) {//下滚
        }
        else {//上滚
            $(".topbut").slideDown(300);

        }
        setTimeout(function () {
            t = p;
        }, 0);
    });
});

function add() {
    var ydrphone = $("#ydrphone").val();
    if ('' == ydrphone) {
        alert('请填写预定人电话');
        return
    }
    var a = $("#starttime").val();
    if ('' == a) {
        alert('请选择出发时间');
        return
    }
    var b = $("#endtime").val();
    if ('' == b) {
        alert('请选择结束时间');
        return
    }

    var clyt = $("#clyt").val();
    if ('' == clyt) {
        alert('请填写车辆用途');
        return
    }
    $(".btn").attr("disabled", true);
    $("#addform").submit();
}
function tolist() {
    window.location.href = ctx+'/approval/self-list/321';
}
function hidejt() {
    $(".topbut").slideUp(300);
}