<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>部门管理</title>
    <#include "../common/link.ftl" />
    <script>
        $(function () {
            $(".btn-input").click(function () {
                $("#editModal").modal("show");
                $("#myModalLabel").html("部门")
                $("#editForm")[0].reset();/*重置表单*/

                var data = $(this).data("json");/*解析出标签中data-json中的数据*/
                /*console.log(data);*/
                if(data){  /*排除没有初始数据的情况*/
                    $("input[name=id]").val(data.id);
                    $("input[name=name]").val(data.name);
                    $("input[name=sn]").val(data.sn);
                }

            })

            $(".submitBtn").click(function () {
                /*$("#editForm").submit();*/
                $("#editForm").ajaxSubmit(function (data) {
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

    <#assign currentMenu="department" />
    <#include "../common/menu.ftl" />
    <div class="content-wrapper">
        <section class="content-header">
            <h1>部门管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="/department/list.do" method="post">
                        <input type="hidden" name="currentPage" id="currentPage">
                        <a href="JavaScript:;" class="btn btn-success btn-input" style="margin: 10px">
                            <span class="glyphicon glyphicon-plus"></span>  添加
                        </a>
                    </form>

                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>部门名称</th>
                            <th>部门编号</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <#list result.list as entity>
                            <tr>
                                <td>${entity_index + 1}</td>
                                <td>${entity.name}</td>
                                <td>${entity.sn}</td>
                                <td>
                                    <#--btn-redirect会导致这个class会导致强制跳转-->
                                    <a href="JavaScript:;" class="btn btn-info btn-xs btn-input"  data-json='${entity.json}'>
                                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                                    </a>
                                    <a href="JavaScript:;" data-url="/department/delete.do?id=${(entity.id)}"
                                       class="btn btn-danger btn-xs btn_delete">
                                        <span class="glyphicon glyphicon-trash"></span> 删除
                                    </a>
                                </td>
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

<!-- Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/department/saveOrUpdate.do" method="post" id="editForm">

                    <input type="hidden" value="${(entity.id)!}" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">部门名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" value="${(entity.name)!}"
                                   placeholder="请输入部门名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sn" class="col-sm-2 control-label">部门编号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="sn" name="sn" value="${(entity.sn)!}"
                                   placeholder="请输入部门编号">
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary submitBtn" type="submit">提交</button>
            </div>
        </div>
    </div>
</div>


</body>
</html>
