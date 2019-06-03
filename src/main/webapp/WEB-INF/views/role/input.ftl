<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色管理</title>
    <#include "../common/link.ftl"/>

    <script>

        $(function () {
            ///提交表单, 全选自己角色下拉框
            $("#btn_submit").click(function () {
                //全选自己角色下拉框
                $(".selfRoles option").prop("selected", true);

                //表单提交
                $("#editForm").submit();
            });


            //1: 页面加载完毕之后, 将自己的角色[id]全部获取出来, 放置到一个数组中
            //参数1:操作数组
            //参数2:回调方法, 目的是后去遍历option数组中每个元素的value值
            //结果: 自己角色id数组
            var ids = $.map($(".selfRoles option"), function (option) {
                return option.value;
            });
            //2:将系统的角色[id]也全部获取出来
            var allRoles = $(".allRoles option");

            //遍历数组
            //参数1:数组
            //参数2:回调方法
            $.each(allRoles, function (index, item) {
                //3:遍历系统角色[id]一个个去跟 第一步获取到数组对比
                ///判断元素是否在数组中
                //参数1:判断元素
                //参数2:数组
                //返回: 如果存在, 返回数组索引, 不存在返回-1
                if ($.inArray(item.value, ids) >= 0) {
                    //3.1: 如果存在, 删除当前比较系统角色
                    $(item).remove();
                } else {
                    //3.2: 如果不存在, 保留
                }
            });


        })
        //全部移动
        function moveAll(srcCls, targetCls) {
            $("." + srcCls + " option").appendTo($("." + targetCls));
        }
        //选中移动
        function moveSelected(srcCls, targetCls) {
            $("." + srcCls + " option:selected").appendTo($("." + targetCls));
        }

        //保存表单提交
        function saveAll() {
            $("#selfRoles option").prop("selected",true);
            $("#editForm").submit();
        }

    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl" />
    <!--菜单回显-->

    <#assign currentMenu="role" />
    <#include "../common/menu.ftl" />
    <div class="content-wrapper">
        <section class="content-header">
            <h1>用户编辑</h1>
        </section>
        <section class="content">
            <div class="box" style="padding: 10px;">
                <form class="form-horizontal" action="/role/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${(entity.id)!}" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">角色名：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" value="${(entity.name)!}"
                                   placeholder="请输入角色名">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sn" class="col-sm-2 control-label">角色编号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="sn" name="sn" value="${(entity.sn)!}"
                                   placeholder="请输入角色编号">
                        </div>
                    </div>

                    <div class="form-group " id="role">
                        <label for="role" class="col-sm-2 control-label">分配角色：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allRoles" size="15">

                                <#list allPermission as r >
                                        <option value="${(r.id)!}">${(r.name)!}</option>
                                </#list>

                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary  " style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <select multiple class="form-control selfRoles" size="15" name="ids" id="selfRoles">
                                <#list entity.permissions as r >
                                        <option value="${(r.id)!}">${(r.name)!}</option>
                                </#list>
                                </select>
                            </div>

                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="submitBtn" type="button" class="btn btn-primary" onclick="saveAll()">保存</button>
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