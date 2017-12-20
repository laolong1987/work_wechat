/**
 * Created by gaoyang on 17/12/20.
 */
$('.form_datetime').mobiscroll().date({
    theme: 'Mobiscroll',
    lang: 'zh',
    display: 'center',
    min: new Date(2000, 1, 1),
    disabled: false
});

$(function() {
    $('.rla').click(function(){
        var radioId = $(this).attr('name');
        $('.rla').removeClass('cr');
        $(this).addClass('cr');
        $('input[type="radio"]').removeAttr('checked') && $('#' + radioId).attr('checked', 'checked');
    });

    var p=0,t=0;

    $(window).scroll(function(e){
        p = $(this).scrollTop();

        if(t<=p){//下滚
        }
        else{//上滚
            $(".topbut").slideDown(300);

        }
        setTimeout(function(){t = p;},0);
    });
});

settime();

function settime(){
    var myDate = new Date();
    var h= myDate.getHours();
    var yczl='中餐';
    if(h >= 10 && h < 16){
        yczl='';
    }else if(h>=16 && h < 20){
        yczl='晚餐';
    }else if(h>=20 && h <24){
        yczl='夜店';
    }else{
        yczl='早餐';
    }

    var m= myDate.getMonth();
    m++;
    if(m<10){
        m='0'+m;
    }

    var d= myDate.getDate();
    if(d<10){
        d='0'+d;
    }

    $("#ycrq").val(myDate.getFullYear()+'/'+m+'/'+d);
    addoptiondetail('接待用餐',yczl);
}


function addoption(){
    var yctype= $("input[name='yctype']:checked").val();
    var yczl= $("#yczl").val();
    addoptiondetail(yctype,yczl);

}

function addoptiondetail(yctype,yczl){
    if("工作用餐"==yctype && "早餐"==yczl){
        $("#ycbz").html("<option value='4'>4元</option>");
        $("#ycdd").html("<option value='综合楼辅楼'>综合楼辅楼</option>");
        $("#ycdd").append("<option value='综合楼职工厅'>综合楼职工厅</option>");
        $("#ycdd").append("<option value='其他'>其他</option>");
    }

    if(("工作用餐"==yctype && "中餐"==yczl) || ("工作用餐"==yctype && "晚餐"==yczl) ){
        $("#ycbz").html("<option value='10'>10元</option>");
        $("#ycdd").html("<option value='综合楼辅楼'>综合楼辅楼</option>");
        $("#ycdd").append("<option value='综合楼职工厅'>综合楼职工厅</option>");
        $("#ycdd").append("<option value='其他'>其他</option>");
    }

    if("工作用餐"==yctype && "夜点"==yczl ){
        $("#ycbz").html("<option value='4'>4元</option>");
        $("#ycbz").append("<option value='6'>6元</option>");
        $("#ycdd").html("<option value='综合楼辅楼'>综合楼辅楼</option>");
        $("#ycdd").append("<option value='综合楼职工厅'>综合楼职工厅</option>");
        $("#ycdd").append("<option value='其他'>其他</option>");
    }


    if("接待用餐"==yctype && "早餐"==yczl ){
        $("#ycbz").html("<option value='4'>4元</option>");
        $("#ycdd").html("<option value='综合楼辅楼'>综合楼辅楼</option>");
        $("#ycdd").append("<option value='其他'>其他</option>");
    }


    if("接待用餐"==yctype && "中餐"==yczl ){
        $("#ycbz").html("<option value='10'>10元</option>");
        $("#ycbz").append("<option value='15'>15元</option>");
        $("#ycdd").html("<option value='综合楼来宾厅'>综合楼来宾厅</option>");
        $("#ycdd").append("<option value='综合楼辅楼'>综合楼辅楼</option>");
        $("#ycdd").append("<option value='其他'>其他</option>");
    }

    if("接待用餐"==yctype && "晚餐"==yczl ){
        $("#ycbz").html("<option value='10'>10元</option>");
        $("#ycbz").append("<option value='15'>15元</option>");
        $("#ycdd").html("<option value='综合楼辅楼'>综合楼辅楼</option>");
        $("#ycdd").append("<option value='其他'>其他</option>");
    }
    if("接待用餐"==yctype && "夜点"==yczl ){
        $("#ycbz").html("<option value='4'>4元</option>");
        $("#ycbz").append("<option value='6'>6元</option>");
        $("#ycdd").html("<option value='综合楼辅楼'>综合楼辅楼</option>");
        $("#ycdd").append("<option value='其他'>其他</option>");
    }
}

function add(){
    var lfdwjry=$("#lfdwjry").val();
    if(''==lfdwjry){
        alert('请填写来访人员/事由');
        return
    }
    var a=$("#ycrq").val();
    if(''==a){
        alert('请选择用餐日期');
        return
    }
    var b=$("#ycsl").val();
    if(''==b){
        alert('请填写人数');
        return
    }

    var ycremark=$("#ycremark").val();
    if(''==ycremark){
        alert('请填写备注');
        return
    }

    var yczl=$("#yczl").val();
    if(''==yczl){
        alert('请选择用餐时间');
        return
    }

    var ycbz=$("#ycbz").val();
    if(''==ycbz){
        alert('请填写用餐标准');
        return
    }

    var ycdd=$("#ycdd").val();
    if(''==ycdd){
        alert('请填写用餐地点');
        return
    }
    $(".btn").attr("disabled", true);
    $("#addform").submit();
}
function tolist(){
    window.location.href=ctx+'/approval/self-list/323';
}
function hidejt(){
    $(".topbut").slideUp(300);
}