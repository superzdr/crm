<aside class="main-sidebar">
    <section class="sidebar">
        <!-- search form -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form>
        <ul class="sidebar-menu" data-widget="tree">
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>系统管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li name="department"><a href="/department/list.do"><i class="fa fa-circle-o"></i> 部门管理</a></li>
                    <li name="employee"><a href="/employee/list.do"><i class="fa fa-circle-o"></i> 员工管理</a></li>
                    <li name="permission"><a href="/permission/list.do"><i class="fa fa-circle-o"></i> 权限管理</a></li>
                    <li name="role"><a href="/role/list.do"><i class="fa fa-circle-o"></i> 角色管理</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span>数据管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li name="systemDictionary"><a href="/systemDictionary/list.do"><i class="fa fa-circle-o"></i> 字典目录</a>
                    </li>
                    <li name="systemDictionaryItem"><a href="/systemDictionaryItem/list.do"><i
                            class="fa fa-circle-o"></i> 字典明细</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-pie-chart"></i>
                    <span>客户管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li name="customer"><a href="/customer/list.do"><i class="fa fa-circle-o"></i> 客户列表</a></li>
                    <li name="customer_potential"><a href="/customer/potentialList.do"><i class="fa fa-circle-o"></i>
                        潜在客户</a></li>
                    <li name="customer_pool"><a href="/customer/poolList.do"><i class="fa fa-circle-o"></i> 客户池</a></li>
                    <li name="customer_fail"><a href="/customer/failList.do"><i class="fa fa-circle-o"></i> 失败客户</a>
                    </li>
                    <li><a href="#"><i class="fa fa-circle-o"></i> 正式客户</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i> 流失客户</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-edit"></i>
                    <span>客户历史</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">

                    <li name="customerTraceHistory"><a href="/customerTraceHistory/list.do"><i
                            class="fa fa-circle-o"></i> 跟进历史</a></li>
                    <li name="customerTransfer"><a href="/customerTransfer/list.do"><i class="fa fa-circle-o"></i> 移交历史</a>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i>
                    <span>报表统计</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li name="customerReport"><a href="/customerReport/list.do"><i class="fa fa-circle-o"></i>潜在客户报表</a>
                    </li>
                </ul>
            </li>

        </ul>
    </section>
</aside>

<script>
    $(function () {
        //菜单初始
        $('.sidebar-menu').tree();
        //菜单激活
        var cuLi = $(".treeview-menu li[name='${currentMenu!}']");
        cuLi.addClass("active");
        cuLi.closest(".treeview").addClass("active")
    })
</script>