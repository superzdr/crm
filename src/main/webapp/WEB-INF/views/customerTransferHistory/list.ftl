<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>移交历史管理</title>
    <#include "../common/link.ftl" />
    <script>
        $(function () {
            $(".btn-input").click(function () {
                $("#transferHistoryModal").modal("show");
                $("#myModalLabel").html("移交历史")
                $("#traceHistoryForm")[0].reset();/*重置表单*/

                var data = $(this).data("json");/*解析出标签中data-json中的数据*/
                /*console.log(data);*/
                if(data){  /*排除没有初始数据的情况*/
                    $("#traceHistoryForm input[name='id']").val(data.id);
                    $("#traceHistoryForm input[name='customer.id']").val(data.customerId);
                    $("#traceHistoryForm input[name='customer.name']").val(data.customerName);
                    $("#traceHistoryForm input[name='traceTime']").val(data.traceTime);
                    $("#traceHistoryForm input[name='traceDetails']").val(data.traceDetails);
                    $("#traceHistoryForm select[name='type.id']").val(data.typeId);
                    $("#traceHistoryForm select[name='traceResult']").val(data.traceResult);
                    $("#traceHistoryForm input[name='remark']").val(data.remark);
                }

            })

            $(".traceHistorySubmit").click(function () {
                /*$("#editForm").submit();*/
                $("#traceHistoryForm").ajaxSubmit(function (data) {
                    handleMsg(data);
                })
            })


        })
        
        function handleMsg(data) {
            if(data.success){
                $.messager.alert("温馨提示","执行成功2秒后自动刷新");
                setTimeout(function () {
                    window.location.reload();
                },2000);
            }else{
                $.messager.alert(data.errorMsg);
            }
        }
    </script>


</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl" />
    <!--菜单回显-->

    <#assign currentMenu="customerTransferHistory" />
    <#include "../common/menu.ftl" />
    <div class="content-wrapper">
        <section class="content-header">
            <h1>移交历史管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <div style="margin: 10px;">
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="/customerTransferHistory/list.do" method="post">
                        <input type="hidden" name="currentPage" id="currentPage">
                        <div class="form-group">
                            <label for="keyword">关键字:</label>
                            <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)!""}" placeholder="请输入姓名或电话">
                        </div>
                        <#--<div class="form-group">
                            <label for="type">移交类型:</label>
                            <select id="typeQuery" class="form-control" name="type">
                                <option value="-1">全部</option>
                                <option value="0">潜在客户</option>
                                <option value="1">正式客户</option>
                            </select>
                            <script>
                                $("#typeQuery option[value='${(qo.type)!}']").prop("selected", true);
                            </script>
                        </div>-->
                        <button id="btn_query" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>查询</button>

                    </form>
                </div>

                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>客户姓名</th>
                            <th>操作日期</th>
                            <th>操作人</th>
                            <th>旧营销人员</th>
                            <th>新营销人员</th>
                            <th>移交原因</th>
                           <#-- <th>移交类型</th>-->

                        </tr>
                        </thead>
                        <#list result.list as entity>
                            <tr>
                                <td>${entity_index+1}</td>
                                <td>${entity.customer.name}</td>
                                <td>${entity.operateTime?string('yyyy-MM-dd')}</td>
                                <td>${entity.operator.name}</td>
                                <td>${entity.oldseller.name}</td>
                                <td>${entity.newseller.name} </td>
                                <td>${entity.reason}</td>
                               <#-- <td>${(entity.displayType)!}</td>-->

                            </tr>
                        </#list>
                    </table>
                    <!--分页-->
                    <#include "../common/page_withplugin.ftl" />
                </div>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl" />
</div>

</body>
</html>
