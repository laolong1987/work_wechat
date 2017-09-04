/**
 * Created by gaoyang on 17/9/3.
 */
function uploadfile() {
    if($("#file").val()==''){
        return
    }
    $.ajaxFileUpload({
        url: "../../file/uploadfile",
        secureuri: false,//一般设置为false
        fileElementId: "file",
        dataType: "text",
        type: "POST",
        success: function (result) {
            $("#fileid").val(result);
            //同步更新到页面
            var li = '<li id="li_' + result + '"><a href="javascript:;"  onclick="removefile(\'' + result + '\')">' +
                '</a><img src="../../file/doDownload/'+result+'" onclick="openfile(\'' + result + '\')" ></li>';
            $("#fileui1").append(li);
            $(".last_li").hide();

        },
        error: function (data, status, e){

        }
    });
}

function removefile(id) {
    $.ajax({
        url: "../../file/removefile",
        data: {"id": id},
        type: "POST",
        success: function (result) {
            $("#li_" + id).remove();
            $(".last_li").show();
        }
    });
}
function openfile(obj) {
    window.open('../../file/doDownload/' + obj);
}