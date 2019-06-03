<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <#include "../common/link.ftl" />
    <#--<script>
        $(function () {
            $("<option>").text("Hello").val("8").appendTo($("#dept"));

        })

    </script>-->
    <script>
        $(function () {
            $(".all").change(function () {
                $(".cb").prop("checked",this.checked);
            })

            $(".btn-batchDelete").click(function () {
                if($(".cb:checked").length==0){
                    $.messager.alert("温馨提示","请选择需要删除的数据");
                    return;
                }
                var ids = [];
                $.each($(".cb:checked"),function (index,ele) {
                    ids.push($(ele).data("id"));
                });
                console.log(ids);
                $.get("/employee/batchDelete.do",{ids:ids},function (data) {
                    handleMsg(data);
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
        })

    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl" />
    <!--菜单回显-->
<#assign currentMenu="employee" />
    <#include "../common/menu.ftl" />
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <!--高级查询--->

                        <form class="form-inline" id="searchForm" action="/employee/list.do" method="post">
                            <input type="hidden" name="currentPage" id="currentPage">
                            <div class="form-group">
                                <label for="keyword">关键字:</label>
                                <input type="text" class="form-control" id="keyword" name="keyword"
                                       value="${(qo.keyword)!}" placeholder="请输入姓名/邮箱">
                            </div>
                            <div class="form-group">
                                <label for="dept"> 部门:</label>
                                <select class="form-control" id="dept" name="deptId" value="">
                                    <option value="-1">全部</option>

                                        <#list depts as dept >
                                        <option value="${dept.id}">${dept.name}</option>
                                        </#list>


                                </select>
                                <script>
                                    $("#dept option[value=${qo.deptId}]").prop("selected", true);
                                </script>
                            </div>
                            <button id="btn_query" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>
                                查询
                            </button>
                            <a href="javascript:;" class="btn btn-success btn_redirect" data-url="/employee/input.do">
                                <span class="glyphicon glyphicon-plus"></span> 添加
                            </a>
                            <a href="javascript:;" class="btn btn-danger btn-batchDelete" data-url="/employee/batchDelete.do">
                                <span class="glyphicon glyphicon-trash"></span> 批量删除
                            </a>

                        </form>

                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th><input type="checkbox" class="all"></th>
                            <th>编号</th>
                            <th>名称</th>
                            <th>邮箱</th>
                            <th>年龄</th>
                            <th>部门</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <#list result.data as entity>
                            <tr>
                                <td><input type="checkbox" class="cb" data-id="${entity.id}"></td>
                                <td>${entity_index + 1}</td>
                                <td>${entity.name}</td>
                                <td>${entity.email}</td>
                                <td>${entity.age}</td>
                                <td>${(entity.dept.name)!}</td>
                                <td>
                                    <a class="btn btn-info btn-xs btn_redirect" href="/employee/input.do?id=${entity.id}">
                                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                                    </a>
                                    <a href="JavaScript:;" data-url="/employee/delete.do?id=${entity.id}"
                                       class="btn btn-danger btn-xs btn_delete">
                                        <span class="glyphicon glyphicon-trash"></span> 删除
                                    </a>
                                </td>
                            </tr>
                        </#list>
                    </table>
                    <!--分页-->
                    <#include "../common/page.ftl" />
                </div>
            </div>
        </section>
    </div>
<#include "../common/footer.ftl" />
</div>

</body>
</html>
