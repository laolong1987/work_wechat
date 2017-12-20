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

function  btnCount_Click(obj){
    var a=$("#date1").val();
    var b=$("#date2").val();
    var days='';
    console.log(a);
    console.log(b);
    if(''!=a && ''!=b){
        a= a.substr(0, a.length-6);
        b= b.substr(0, b.length-6);
        var c=DateDiff(a,b);
        console.log(c);
        if(c>0){
            days=c;
        }else{
            obj.value='';
            alert('结束时间不能小于开始时间');
            return
        }
    }
    $("#day").val(days);
}
//计算天数差的函数，通用
function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式
    var  oDate1,  oDate2,  iDays;
    oDate1  =  new  Date(sDate1.replace(/-/g,"/"));    //转换为12-18-2006格式
    oDate2  =  new  Date(sDate2.replace(/-/g,"/"));
    iDays  =  oDate2.getTime()-oDate1.getTime();
    var time=parseInt(iDays/(1000*60*60*24))+1;
    return  time;
}

function add(){
    var type=$("#type").val();
    if(''==type){
        alert('请选择请假类型');
        return
    }
    var a=$("#date1").val();
    var b=$("#date2").val();

    if(''==a){
        alert('请选择开始时间');
        return
    }
    if(''==b){
        alert('请选择结束时间');
        return
    }
    if(''!=a && !''!=b){
        a= a.substr(0, a.length-6);
        b= b.substr(0, b.length-6);
        var c=DateDiff(a,b);
        if(c<=0){
            alert('日期选择错误,请重新选择');
            return
        }
    }

    var desc=$("#desc").val();
    if(''==desc){
        alert('请选择请假类型');
        return
    }

    $("#day").val(DateDiff(a,b));
    $(".btn").attr("disabled", true);
    $("#addform").submit();
}

function tolist(){
    window.location.href=ctx+'/approval/self-list/349';
}
