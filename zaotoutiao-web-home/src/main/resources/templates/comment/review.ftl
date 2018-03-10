<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>评论管理</title>
    <link href="/static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/uimaker/easyui.css">
    <link rel="stylesheet" href="/static/uimaker/icon.css">
</head>
<body>
<#--<h2>评论管理</h2>-->
<div class="container">
    <table id="dg" class="easyui-datagrid" style="width:100%;height:554px" url="/comments/list" title="全部评论列表" data-options="
                rownumbers:true,
                singleSelect:false,
                autoRowHeight:false,
                pagination:true,
                fitColumns:true,
                striped:true,
                checkOnSelect:false,
                selectOnCheck:false,
                collapsible:true,
                toolbar:'#tb',
                pageSize:10">
        <thead>
        <tr>
            <th field="nickName" width="200">用户昵称</th>
            <th field="content" width="350">评论内容</th>
            <th field="title" width="170">文章/视频标题</th>
            <th field="createDate" width="112">评论时间</th>
            <th field="_operate" width="80" data-options="align:'center',formatter:formatOperate">操作</th>
        </tr>
        </thead>
    </table>
    <div id="tb" style="padding:0 30px;">
        关键字: <input id="search" class="easyui-textbox" type="text" name="code" style="width:166px;height:35px;line-height:35px;"/>
        <a href="#" onclick="toSearch()" id="bt_search_btn" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
    </div>
</div>


<script type="text/javascript" src="/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/static/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
    (function($){
        function pagerFilter(data){
            if ($.isArray(data)){   // is array
                data = {
                    total: data.length,
                    rows: data
                }
            }
            var target = this;
            var dg = $(target);
            var state = dg.data('datagrid');
            var opts = dg.datagrid('options');
            if (!state.allRows){
                state.allRows = (data.rows);
            }
            if (!opts.remoteSort && opts.sortName){
                var names = opts.sortName.split(',');
                var orders = opts.sortOrder.split(',');
                state.allRows.sort(function(r1,r2){
                    var r = 0;
                    for(var i=0; i<names.length; i++){
                        var sn = names[i];
                        var so = orders[i];
                        var col = $(target).datagrid('getColumnOption', sn);
                        var sortFunc = col.sorter || function(a,b){
                                    return a==b ? 0 : (a>b?1:-1);
                                };
                        r = sortFunc(r1[sn], r2[sn]) * (so=='asc'?1:-1);
                        if (r != 0){
                            return r;
                        }
                    }
                    return r;
                });
            }
            var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
            var end = start + parseInt(opts.pageSize);
            data.rows = state.allRows.slice(start, end);
            return data;
        }

        var loadDataMethod = $.fn.datagrid.methods.loadData;
        var deleteRowMethod = $.fn.datagrid.methods.deleteRow;
        $.extend($.fn.datagrid.methods, {
            clientPaging: function(jq){
                return jq.each(function(){
                    var dg = $(this);
                    var state = dg.data('datagrid');
                    var opts = state.options;
                    opts.loadFilter = pagerFilter;
                    var onBeforeLoad = opts.onBeforeLoad;
                    opts.onBeforeLoad = function(param){
                        state.allRows = null;
                        return onBeforeLoad.call(this, param);
                    }
                    var pager = dg.datagrid('getPager');
                    pager.pagination({
                        onSelectPage:function(pageNum, pageSize){
                            opts.pageNumber = pageNum;
                            opts.pageSize = pageSize;
                            pager.pagination('refresh',{
                                pageNumber:pageNum,
                                pageSize:pageSize
                            });
                            dg.datagrid('loadData',state.allRows);
                        }
                    });
                    $(this).datagrid('loadData', state.data);
                    if (opts.url){
                        $(this).datagrid('reload');
                    }
                });
            },
            loadData: function(jq, data){
                jq.each(function(){
                    $(this).data('datagrid').allRows = null;
                });
                return loadDataMethod.call($.fn.datagrid.methods, jq, data);
            },
            deleteRow: function(jq, index){
                return jq.each(function(){
                    var row = $(this).datagrid('getRows')[index];
                    deleteRowMethod.call($.fn.datagrid.methods, $(this), index);
                    var state = $(this).data('datagrid');
                    if (state.options.loadFilter == pagerFilter){
                        for(var i=0; i<state.allRows.length; i++){
                            if (state.allRows[i] == row){
                                state.allRows.splice(i,1);
                                break;
                            }
                        }
                        $(this).datagrid('loadData', state.allRows);
                    }
                });
            },
            getAllRows: function(jq){
                return jq.data('datagrid').allRows;
            }
        })
    })(jQuery);

    //数据分页
    $(function () {
        $('#dg').datagrid({url:"/comments/list"}).datagrid('clientPaging');

    });

    function formatOperate(val,row,index){
        return '<a href="#" onclick="del('+row.id+')">删除</a>';
    }
    function del(id) {
        $.post("/delete/comments",{id:id},function (data) {
            if (data === 1) {
                $.messager.alert('请求删除评论', '删除成功！', 'info');
            } else {
                $.messager.alert('请求删除评论', '删除失败！', 'error');
            }
            $('#dg').datagrid('reload');
        },"json")
    }

    //function toSearch() {
        //var url = "/comments/list?keyword="+$('#search').val();
        //$('#dg').datagrid({url:"/comments/list?keyword="+$('#search').val()});
        //$('#dg').datagrid('reload');
    //}
    function toSearch(){
            //$('#dg').datagrid({data:data}).datagrid('reload');

        $('#dg').datagrid('load',{
            keyword: $('#search').val()
        })
    }

//    function getData(){
//        var rows = [];
//        $.post("/comments/list", function(data) {
//            for(var i=1; i<=800; i++){
//                rows.push({
//                    nickName: '10695',
//                    content: '南京天泽星网股份有限公司',
//                    createDate: '正式',
//                    tltle: '光纤通信设备配件',
//                    full: '√',
//                    id: '√'
//                });
//            }
//        });
//        return rows;
//    }

//    $(function(){
//        $('#dg').datagrid({data:getData()}).datagrid('clientPaging');
//    });

</script>

</body>
</html>