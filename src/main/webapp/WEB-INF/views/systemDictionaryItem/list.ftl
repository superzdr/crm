<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据字典明细管理</title>
<#include "../common/link.ftl" />
    <script>
        $(function () {
            $(".btn-input").click(function () {
                $("#editModal").modal("show");
                $("#myModalLabel").html("数据字典");
                $("#editForm")[0].reset();
                /*重置表单*/

                var data = $(this).data("json");

                var parentName = $(".dicDir[data-pid='${(qo.parentId)!}']").text();

                /*解析出标签中data-json中的数据*/
                /*console.log(data);*/
                if (data) {  /*排除没有初始数据的情况*/
                    $("input[name=id]").val(data.id);
                    $("input[name=title]").val(data.title);
                    $("input[name=sequence]").val(data.sequence);
                }
                $("input[name=parentName]").val(parentName);
                $("input[name=parentId]").val(${(qo.parentId)!});

            })

            $(".submitBtn").click(function () {
                /*$("#editForm").submit();*/
                $("#editForm").ajaxSubmit(function (data) {
                    handleMsg(data);
                })
            })

            //处理不同目录被点击后在页面显示的表格
           /* $(".dicDir").click(function () {
                console.log($(this).data("pid"));
                //发送请求并处理相应结果,需要带过去的数据是目录的id,处理的回显结果是目录表格
                //需要定义controller来接收数据
                $("#parentId").val($(this).data("pid"));
                $("#searchForm").ajaxSubmit();
            })*/

        })

        function handleMsg(data) {
            if (data.success) {
                $.messager.alert("温馨提示", "执行成功2秒后自动刷新");
                setTimeout(function () {
                    window.location.reload();
                }, 2000);
            } else {
                $.messager.alert(data.errorMsg);
            }
        }
    </script>


</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<#include "../common/navbar.ftl" />
    <!--菜单回显-->

<#assign currentMenu="systemDictionaryItem" />
<#include "../common/menu.ftl" />
    <div class="content-wrapper">
        <section class="content-header">
            <h1>数据字典明细</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="/systemDictionaryItem/list.do" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="${(qo.currentPage)!}">
                        <input type="hidden" name="parentId" id="parentId" value="${(qo.parentId)!}"> <#--在进行查询的时候需要往其中设置父类id以便一并提交-->
                        <#--<a href="JavaScript:;" class="btn btn-success btn-input" style="margin: 10px">
                            <span class="glyphicon glyphicon-plus"></span> 添加
                        </a>-->
                    </form>

                    <br>
                    <div class="row">

                        <div class="  col-sm-3">
                            <div class="panel panel-info">
                                <div class="panel-heading"><strong>字典明细</strong></div>
                                <ul class="list-group" id="dicDirUl">
                                <#list dictionaryList as dictionary>
                                    <li class="list-group-item"><a class="dicDir" data-pid="${dictionary.id}"
                                                                   href="/systemDictionaryItem/list.do?parentId=${dictionary.id}">${dictionary.title}</a></li>
                                </#list>

                                    <#--<script>
                                        $(".dicDir[data-pid='1']").closest("li").addClass("active");
                                        $(".dicDir[data-pid='1']").css("color", "white")
                                    </script>-->
                                </ul>
                            </div>
                        </div>

                        <div class="col-sm-9">
                            <a href="JavaScript:;" class="btn btn-success inputBtn btn-input"><span
                                    class="glyphicon glyphicon-plus"></span> 添加明细</a>
                            <table class="table table-striped table-hover table-bordered">
                                <thead>

                                <tr>
                                    <th>编号</th>
                                    <th>标题</th>
                                    <th>序号</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                            <#if result??>
                                <#list result.list as entity>

                                    <tr>
                                        <td>${entity_index + 1}</td>
                                        <td>${entity.title}</td>
                                        <td>${entity.sequence}</td>
                                        <td>
                                        <#--btn-redirect会导致这个class会导致强制跳转-->
                                            <a href="JavaScript:;" class="btn btn-info btn-xs btn-input" data-json='{"id":"${entity.id}","parentId":"${entity.parentId}","title":"${entity.title}","sequence":"${entity.sequence}"}'>
                                                <span class="glyphicon glyphicon-pencil"></span> 编辑
                                            </a>
                                            <a href="JavaScript:;"
                                               data-url="/systemDictionaryItem/delete.do?id=${(entity.id)!}"
                                               class="btn btn-danger btn-xs btn_delete">
                                                <span class="glyphicon glyphicon-trash"></span> 删除
                                            </a>
                                        </td>
                                    </tr>
                                </#list>
                            </#if>
                            </table>
                        </div>

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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/systemDictionaryItem/saveOrUpdate.do" method="post"
                      id="editForm">

                    <input type="hidden" value="" name="id">
                    <input type="hidden" value="" name="parentId">

                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">字典目录：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="parentName" name="parentName" value="" disabled="disabled"
                                  >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sn" class="col-sm-3 control-label">明细标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="title" name="title" value=""
                                   placeholder="请输入明细标题">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sn" class="col-sm-3 control-label">明细序号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="sequence" name="sequence" value=""
                                   placeholder="请输入明细序号">
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
