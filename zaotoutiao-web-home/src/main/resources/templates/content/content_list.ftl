<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>内容列表</title>
    <#assign ctx="${springMacroRequestContext.contextPath}" />
    <link href="${ctx}/static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/static/uimaker/easyui.css">
    <link rel="stylesheet" href="${ctx}/static/uimaker/icon.css">
</head>
<body>
<div class="container">
    <div id="tb" style="padding:0 30px;">
        <form id="content_form">
            关键词：<input class="easyui-textbox" type="text" name="keyWord" style="width:166px;height:35px;line-height:35px;"/>
            类型：<input class="easyui-combobox" name="infoType" id="infoType" style="height:35px;" data-options="url:'/listPlatformChannel?platformId=0',
                    textField:'channelName',valueField:'id',
                    onLoadSuccess:function(data){
                        if(data != null && data.length > 0){
                           $('#channelId_pres').combobox('select',data[0].id);
                        }
                    },required:true,editable:false"/>
           导航名称:<input class="easyui-combobox" type="text" id="channleId_cont" name="channelId"  data-options="url:'/getChannels?appType=1',
                    textField:'name',valueField:'channelId',onSelect:function(params){
                               $('#firstLevel_cont').combobox({
                                  url:'/subnavigation',
                                  textField:'name',
                                  valueField:'channelId',
                                  onBeforeLoad:function(param){
                                     param.parentId = params.channelId;
                                     param.appType = params.appType
                                  }
                               })

                           },
                    onLoadSuccess:function(data){
                        if(data != null && data.length > 0){
                           $('#channleId_cont').combobox('select',data[0].channelId);
                        }
                    },required:true,editable:false"/>
            一级标签:<input class="easyui-combobox" id="firstLevel_cont" name="firstLevel" data-options="onLoadSuccess:function(data){
                              if(data != null && data.length > 0){
                                  $('#firstLevel_cont').combobox('select',data[0].channelId);
                              }
                          },editable:false,onSelect:function(params){$('#towLevel_cont').combobox({
                                  url:'/subnavigation',
                                  textField:'name',
                                  valueField:'channelId',
                                  onBeforeLoad:function(param){
                                     param.parentId = params.channelId;
                                     param.appType = params.appType
                                  }
                               })}"/>
            二级标签:<input class="easyui-combobox" id="towLevel_cont" name="twoLevel" data-options="onLoadSuccess:function(data){
                              if(data != null && data.length > 0){
                                  $('#towLevel_cont').combobox('select',data[0].channelId);
                              }
                          },editable:false,"/>
            来源：<input class="easyui-combobox" name="source" id="source" style="height:35px;" data-options="url:'/listPlatformChannel?platformId=0',
                    textField:'channelName',valueField:'id',
                    onLoadSuccess:function(data){
                        if(data != null && data.length > 0){
                           $('#channelId_pres').combobox('select',data[0].id);
                        }
                    },required:true,editable:false"/>
            浏览量：<input class="easyui-textbox" type="text" name="browsingVolumeMin" style="width:80px;height:35px;line-height:35px;"/>-
            <input class="easyui-textbox" type="text" name="browsingVolumeMax" style="width:80px;height:35px;line-height:35px;"/>
            转发量：<input class="easyui-textbox" type="text" name="forwardingAmountMin" style="width:80px;height:35px;line-height:35px;"/>-
            <input class="easyui-textbox" type="text" name="forwardingAmountMax" style="width:80px;height:35px;line-height:35px;"/>
            收藏：<input class="easyui-textbox" type="text" name="collentAmountMin" style="width:80px;height:35px;line-height:35px;"/>-
            <input class="easyui-textbox" type="text" name="collentAmountMax" style="width:80px;height:35px;line-height:35px;"/>
            评论：<input class="easyui-textbox" type="text" name="commentsNumberMin" style="width:80px;height:35px;line-height:35px;"/>-
            <input class="easyui-textbox" type="text" name="commentsNumberMax" style="width:80px;height:35px;line-height:35px;"/>
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true" id="content_search_btn">查询</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearForm()">重置</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addNews()">新增新闻</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addVideo()">新增视频</a>
        </form>
    </div>
    <table id="dg_content" style="width:100%;height:auto" title="内容列表" data-options="
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
                <th field="infoType" width="110">类型</th>
                <th field="channelName" width="126">导航</th>
                <th field="firstLevelName" width="112">一级标签</th>
                <th field="twoLevelName" width="170">二级标签</th>
                <th field="title" width="130">标题</th>
                <th field="catInfoName" width="136">关键词</th>
                <th field="thumbnails" width="120" data-options="formatter:imgShow">缩略图</th>
                <th field="source" width="205">来源</th>
                <th field="createDate" width="100">更新时间</th>
                <th field="browsingVolume" width="100">浏览量</th>
                <th field="forwardingAmount" width="105">转发量</th>
                <th field="collentAmount" width="金币">收藏数</th>
                <th field="commentsNumber" width="105">评论数</th>
                <th field="userId" width="200" data-options="formatter:optFormatter">操作</th>
            </tr>
        </thead>
    </table>
</div>
<div id="add_news" class="easyui-dialog" data-options="closed:true,iconCls: 'icon-save',buttons: '#add_news_buttons'" style="padding:10px;"></div>
<div id="add_news_buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveNews()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#add_news').dialog('close')">取消</a>
</div>
<div id="add_video" class="easyui-dialog" data-options="closed:true,iconCls: 'icon-save',buttons: '#add_video_buttons'" style="padding:10px;"></div>
<div id="add_video_buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveVideo()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#edit_video').dialog('close')">取消</a>
</div>
<div id="edit_news" class="easyui-dialog" data-options="closed:true,iconCls: 'icon-save',buttons: '#edit_news_buttons'" style="padding:10px;"></div>
<div id="edit_news_buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="upSaveNews()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#edit_news').dialog('close')">取消</a>
</div>
<div id="edit_video" class="easyui-dialog" data-options="closed:true,iconCls: 'icon-save',buttons: '#edit_video_buttons'" style="padding:10px;"></div>
<div id="edit_video_buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="upSaveVideo()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#edit_video').dialog('close')">取消</a>
</div>
<script type="text/javascript" src="${ctx}/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/easyui-lang-zh_CN.js"></script>

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

    $(function(){
        $('#dg_content').datagrid({url:'/infos/list'}).datagrid('clientPaging');
        $('#content_search_btn').click(function() {
            $('#dg_content').datagrid('load', $('#content_form').toJsonNotEmpty());
        });
    });

    function optFormatter(value,row,index){
        var str = '';
        if(row.infoType == 'news'){
            str += '<a title="编辑"  onclick="editNews('+ row.id +')">编辑</a>&nbsp;&nbsp';
        }
        if(row.infoType == 'video'){
            str += '<a title="编辑" onclick="editVideo('+ row.id +')">编辑</a>&nbsp;&nbsp';
        }
        str += '<a title="删除" onclick="delContent('+ row.id +',\'确定删除吗?\')">删除</a>&nbsp;&nbsp';
        return str;
    }

    function imgShow(value, row, index) {
        var str = '';
        if(value != null && value != ''){
            var array = value.split(",");
            for(var i= 0; i< array.length;i++){
                str += '<img title="" src="' + array[i] + '" alt="" width="100px" height="100px"/>';
            }
        }
        return str;
    }

    function addNews(id) {
        $('#add_news').dialog({
            title:'新闻添加',
            href: '/content/news?id='+id,
            width: 1000,
            height: 600,
            maximizable:true,
            resizable: true,
            modal:true,
            closed:false
        })
    }

    function addVideo(id) {
        $('#add_video').dialog({
            title:'视频添加',
            href: '/content/video?id='+id,
            width: 1000,
            height: 600,
            maximizable:true,
            resizable: true,
            modal:true,
            closed:false
        })
    }

    function editNews(id) {
        $('#edit_news').dialog({
            title:'新闻编辑',
            href: '/news/updateView?id='+id,
            width: 1000,
            height: 600,
            maximizable:true,
            resizable: true,
            modal:true,
            closed:false
        })
    }

    function editVideo(id) {
        $('#edit_video').dialog({
            title:'新闻编辑',
            href: '/video/updateView?id='+id,
            width: 1000,
            height: 600,
            maximizable:true,
            resizable: true,
            modal:true,
            closed:false
        })
    }

    function saveNews(){
        //if ($('#content_add_form').form('validate')) {
        var jsondata=$('#content_add_form').serializeObject();
        $.post('/infos/add', jsondata, function(data) {
            if (data.state == 'success') {
                $.messager.alert('提示信息', data.msg);
                $('#content_add_form').form('clear');
                $('#add_news').dialog('close');
                $('#dg_content').datagrid('load', $('#content_form').toJsonNotEmpty());
            } else {
                $.messager.alert('提示信息', data.msg);
            }
        }, "JSON");
        //}
    }

    function saveVideo(){
        if ($('#video_add_form').form('validate')) {
            var jsondata=$('#video_add_form').serializeObject();
            $.post('/infos/add', jsondata, function(data) {
                if (data.state == 'success') {
                    $.messager.alert('提示信息', data.msg);
                    $('#video_add_form').form('clear');
                    $('#add_video').dialog('close');
                    $('#dg_content').datagrid('load', $('#content_form').toJsonNotEmpty());
                } else {
                    $.messager.alert('提示信息', data.msg);
                }
            }, "JSON");
        }
    }

    function upSaveNews() {
        var jsondata = $('#content_update_form').serializeObject();
        $.post('/infos/update', jsondata, function(data) {
            if (data.state == 'success') {
                $.messager.alert('提示信息', data.msg);
                $('#content_update_form').form('clear');
                $('#edit_news').dialog('close');
                $('#dg_content').datagrid('load', $('#content_form').toJsonNotEmpty());
            } else {
                $.messager.alert('提示信息', data.msg);
            }
        }, "JSON");
    }

    function upSaveVideo() {
        var jsondata = $('#video_update_form').serializeObject();
        $.post('/infos/update', jsondata, function(data) {
            if (data.state == 'success') {
                $.messager.alert('提示信息', data.msg);
                $('#video_update_form').form('clear');
                $('#edit_video').dialog('close');
                $('#dg_content').datagrid('load', $('#content_form').toJsonNotEmpty());
            } else {
                $.messager.alert('提示信息', data.msg);
            }
        }, "JSON");
    }

    function delContent(id, str){

        $.messager.confirm('提示信息', str, function(r){
            if (r){
                $.post("/infos/del?id="+id, function(data) {
                    if (data.result == 'success') {
                        $.messager.alert("提示信息",data.msg);
                        $('#dg_content').datagrid('reload');
                    } else {
                        $.messager.alert("提示信息",data.msg);
                        $('#dg_content').datagrid('reload');
                    }
                }, "JSON");
            }
        });
    }

    function clearForm(){
        $('#content_form').form('clear');
        //$("#channelId_pres").combobox('setText', '全部');
    }

    $.fn.toJsonNotEmpty = function() {
        var arrayValue = $(this).serializeArray();
        var json = {};
        $.each(arrayValue, function() {
            var item = this;
            if(item["value"] && item["value"].trim()!=""){
                if (json[item["name"]]) {
                    json[item["name"]] += "," + item["value"];
                } else {
                    json[item["name"]] = item["value"];
                }
            }
        });
        return json;
    };

    $.fn.extend({
        serializeObject : function() {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function() {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [ o[this.name] ];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        }
    });

</script>
</body>
</html>
