<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>一级标签</title>
    <link href="/static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/uimaker/easyui.css">
    <link rel="stylesheet" href="/static/uimaker/icon.css">
</head>
<body>
<div class="container">
    <table id="dg" class="easyui-datagrid" style="width:100%;height:554px" url="/first/tab/list" title="一级标签管理" data-options="
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
            <th field="parentTab" width="200">导航</th>
            <th field="name" width="200">一级标签</th>
            <th field="typeName" width="200">类别</th>
            <th field="updateDate" width="200">操作时间</th>
            <th field="_operate" width="100" data-options="align:'center',formatter:formatOperate">操作</th>
        </tr>
        </thead>
    </table>
    <div id="tb" style="padding:0 30px;">
        <label for="search">关键字: </label>
        <input id="search" class="easyui-textbox" type="text" name="code" style="width:166px;height:35px;line-height:35px;"/>
        <label for="parentId">导航：</label>
        <input id="parentId" class="easyui-combobox"
               name="parentId"
               data-options="
					url:'/channel/list',
					method:'get',
					valueField:'id',
					textField:'name',
					panelHeight:'auto'
			">
        <label for="appType">类别：</label>
        <select id="appType" name="appType" style="height:35px; width: 100px; text-align: center">
            <option value="">全部</option>
            <option value="1">新闻</option>
            <option value="0">视频</option>
        </select>
        <a href="#" onclick="toSearch()" id="bt_search_btn" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
        <a href="javascript:void(0);" onclick="parent.Open('新增一级标签', '/add/first/tab')" class="easyui-linkbutton">新增</a>
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
        $('#dg').datagrid({url:"/first/tab/list"}).datagrid('clientPaging');

    });
    function formatOperate(val,row,index){

        return  '<a href="#" onclick="doEdit('+row.id+',\''+row.name+'\')">编辑</a>&nbsp;&nbsp;' +
                '<a href="#" onclick="del('+row.id+')">删除</a>';
    }
    //删除
    function del(id) {
        $.post("/delete/channel",{id:id},function (data) {
            if (data === 1) {
                $.messager.alert('请求删除导航', '删除成功！', 'info');
            } else {
                $.messager.alert('请求删除导航', '删除失败！', 'error');
            }
            $('#dg').datagrid('reload');
        },"json")
    }
    function doOrder (){
        $('#dlg').dialog('open');
    }

    //查询
    function toSearch(){
        $('#dg').datagrid('load',{
            name: $('#search').val(),
            parentId: $('#parentId').combobox('getValue'),
            appType: $('#appType').val()
        })
    }

    //编辑
    doEdit = function (id,name){
        $('#editId').val(id);
        $('#editChannelName').val(name);
        $('#edit').dialog('open');
    };
    //编辑提交
    function submitForm(){
        if($('#editChannelName').val().length < 2){
            $.messager.alert('提示','您的名称太短了','error');
        }else if($('#parentIdd').combobox('getValue') =='' || $('#parentIdd').combobox('getValue') == null){
            $.messager.alert('提示','请输入您标签的导航','error')
        }else{
            $('#ff').form('submit',{
                url:"/update/tab",
                onSubmit: function(){

                },
                success:function(data){
                    if(data == 1){
                        $.messager.alert('更新标签请求','更新成功！','info');
                        $('#edit').dialog('close');
                        $('#dg').datagrid('reload');
                        //window.location.reload();
                    }else if(data =='0'){
                        $.messager.alert('更新标签请求','更新失败！','error');
                    }
                }
            });
        }
        //window.location.reload();
    }

</script>

<div id="edit" class="easyui-dialog" title="导航编辑" data-options="closed:true" style="width:422px;height:270px;padding:10px;">
        <div style="padding:10px 60px 20px 60px">
            <form id="ff" method="post">
                <table cellpadding="5">
                    <tr>
                        <td>标签名称:</td>
                        <td><input type="text" id="editChannelName" name="editChannelName"  style="height:35px;"><br /><input style="display: none" name="editId" id="editId" /></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>导航</td>
                        <td><input id="parentIdd" class="easyui-combobox" name="parentIdd" data-options="
					url:'/channel/list',
					method:'get',
					valueField:'id',
					textField:'name',
					panelHeight:'auto'"></td>
                    </tr>
                </table>
            </form>
            <div style="text-align:center;padding:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">确认</a>
            </div>
        </div>
</div>
</body>
</html>