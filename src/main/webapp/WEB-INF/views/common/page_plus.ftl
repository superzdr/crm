<div style="text-align: center;">
    <div>
        <ul id="pagination" class="pagination"></ul>

        <div style="display: inline; padding-bottom: 100px;">
        <select >
            <option value='5' <#if pageInfo.pageSize == 5>selected</#if>}>5条/页</option>
            <option value='10'<#if pageInfo.pageSize == 10>selected</#if>>10条/页</option>
            <option value='20'<#if pageInfo.pageSize == 20>selected</#if>>20条/页</option>
        </select>
        跳至<input type='text' id='pageNum' style='width: 30px;' value='${pageInfo.pageNum}' >页
        </div>
    </div>
    </div>
</div>

<script>
//分页
$(function(){
    $("#pagination").twbsPagination({
            totalPages: ${pageInfo.pages}||1,
            startPage: ${pageInfo.pageNum} || 1,
            visiblePages:5,
            first:"首页",
            prev:"上页",
            next:"下页",
            last:"尾页",
            initiateStartPageClick:false,
            onPageClick:function(event,page){
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
    });
})
</script>