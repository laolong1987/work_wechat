$('.form_datetime').mobiscroll().datetime({
    theme: 'Mobiscroll',
    lang: 'zh',
    display: 'center',
//    dateFormat: 'yyyy-MM-dd HH:mm',
    min: new Date(2000, 1, 1),
    disabled: false
});


function  btnCount_Click(){
    //alert('aaaaa');
    var a=$("#date1").val();

    var b=$("#date2").val();
    var days='0';

    if(''!=a &&  ''!=b){
        a= a.substr(0, a.length-6);
        b= b.substr(0, b.length-6);
        var c=DateDiff(a,b);
        if(c>0){
            days=c;
        }
    }
//        $("#days").text(days+"天");
    $("#day").val(days);

}
//计算天数差的函数，通用
function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式
    var  aDate,  oDate1,  oDate2,  iDays;
    aDate  =  sDate1.split("/");
    oDate1  =  new  Date(aDate[0]  ,  aDate[1]-1  ,  aDate[2], 0, 0, 0);    //转换为12-18-2006格式
    aDate  =  sDate2.split("/");
    oDate2  =  new  Date(aDate[0]  , aDate[1]-1  ,  aDate[2],0,0,0);
    var date3=oDate2.getTime()-oDate1.getTime() ;
    iDays  =  parseInt(Math.floor(date3  / ( 24*3600*1000 )) ) //把相差的毫秒数转换为天数

    //  iDays  = Math.floor(date3/(24*3600*1000))；
    return  iDays
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
        alert('请填写备注');
        return
    }

    $("#day").val(DateDiff(a,b));
    $(".btn").attr("disabled", true);
    $("#addform").submit();
}

function tolist(){
    window.location.href=ctx+'/approval/self-list/349';
}
