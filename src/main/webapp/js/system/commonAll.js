//公共组件
//数组传值时，不添加[]
$.ajaxSettings.traditional = true;


//查询
$(function () {
    $("#btn_query").click(function () {
        $("#currentPage").val(1);
        $("#searchForm").submit();
    });
});
//删除
$(function () {
    $(".btn_delete").click(function () {
        var url = $(this).data("url");
        $.messager.confirm("温馨提示","确实要删除该条数据?",function () {
            $.get(url,function (data) {
                if(data.success){
                    $.messager.alert("温馨提示","执行成功2秒后自动刷新");
                    setTimeout(function () {
                        window.location.reload();
                    },2000);
                }else{
                    $.messager.alert(data.errorMsg);
                }
            })
        })
    })
})


//跳转
$(function () {
    //编辑跳转
    $(function () {
        $(".btn_redirect").click(function () {
            var url = $(this).data("url");
            window.location.href = url;
        })
    })
})
