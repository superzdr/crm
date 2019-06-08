<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>部门管理</title>

    <#include "../common/link.ftl" />

    <script>

        $(function () {
            //排重
            var ids = $.map($(".selfPermissions option"), function (item) {
                return item.value;
            })
            $.each($(".allPermissions option"), function (index, item) {
                if ($.inArray(item.value, ids) >= 0) {
                    $(item).remove();
                    $(item).remove();
                }
            });

            //提交表单
            $("#btn_submit").click(function () {
                $(".selfPermissions option").prop("selected", true);
                $("#editForm").submit();
            })
        })

        //移动选中
        function moveSelected(srcCls, targetCls) {
            $("." + srcCls + " option:selected").appendTo($("." + targetCls));
        }
        //移动全部
        function moveAll(srcCls, targetCls) {
            $("." + srcCls + " option").appendTo($("." + targetCls))
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
            <h1>部门编辑</h1>
        </section>
        <section class="content">
            <div class="box">
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

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="btn_submit" type="button" class="btn btn-primary">保存</button>
                            <button type="reset" class="btn btn-danger">重置</button>
                        </div>
                    </div>

                </form>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl" />
</div>
</body>
</html>
