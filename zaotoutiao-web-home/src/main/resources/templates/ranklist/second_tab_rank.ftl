<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>二级标签排行</title>
    <link href="/static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/uimaker/easyui.css">
    <link rel="stylesheet" href="/static/uimaker/icon.css">
</head>
<body>
<div class="container">
    <table id="dg" class="easyui-datagrid" style="width:100%;height:554px" title="新闻/视频二级标签管理——页面默认加载为总数据排行" data-options="
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
            <th field="name" width="200">二级标签</th>
            <th field="readNum" width="260">阅读量</th>
            <th field="appType" width="200" data-options="formatter:function (value,row,index) {
                            var str = '';
                            if(value == 0){
                                str = '视频';
                            }else{
                                str = '文章';
                            }
                            return str;
                        }">类型</th>
            <th field="_operate" width="100" data-options="align:'center',formatter:doQuery">Top-30</th>
        </tr>
        </thead>
    </table>
    <div id="tb" style="padding:0 30px;">
        <div class="opt-buttons">
            <label for="status">时间段：</label>
            <a id="today" href="#" class="easyui-linkbutton" data-options="toggle:true,group:'g1',selected:true" onclick="getDateNum(1)">今天</a>
            <a id="yesterday" href="#" data-options="toggle:true,group:'g1'" class="easyui-linkbutton" onclick="getDateNum(-1)">昨天</a>
            <a id="day7" href="#" data-options="toggle:true,group:'g1'" class="easyui-linkbutton" onclick="getDateNum(7)">最近7天</a>
            <a id="day30" href="#" data-options="toggle:true,group:'g1'" class="easyui-linkbutton" onclick="getDateNum(30)">最近30天</a>
            <label for="status">日期：</label>
            <input id="getDate" type="date" style="">
            <label for="appType">类别：</label>
            <input id="appType" class="easyui-combobox"
                   name="appType"
                   data-options="
                    url:'/app/type/list',
					method:'get',
					valueField:'appType',
					textField:'name',
					panelHeight:'auto'
			">
            <label for="parentIdd">导航：</label>
            <input id="parentIdd" class="easyui-combobox"
                   name="parentIdd"
                   data-options="
					method:'get',
					valueField:'channelId',
					textField:'name',
					panelHeight:'auto'
			">
            <label for="parentId">一级标签：</label>
            <input id="parentId" class="easyui-combobox"
                   name="parentId"
                   data-options="
					method:'get',
					valueField:'channelId',
					textField:'name',
					panelHeight:'auto'
			">
            <input id="dateNumGet" value="1" style="display: none" />
            <a href="#" onclick="toSearch()" id="bt_search_btn" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
        </div>
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
                    };
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
        });
        $.preLoadImages = function(args) {
            console.log(args);
            var _html = "";
            $.each(args, function(index, val) {
                console.log(_html);
                _html += '<li><a href="javascript:void(0)"><img src=\"' + val + '\"/></a></li>';
                $("#showPic>ul").eq(0).html(_html);
            });
        };

    })(jQuery);


    //数据分页
    $(function () {
        $('#dg').datagrid({url:"/second/tab/rank/list"}).datagrid('clientPaging');

    });

    function getDateNum(num){
        $("#dateNumGet").val(num);
    }

    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };

    //查询
    toSearch = function (){
        var dateNum;
        var date;
        dateNum = $("#dateNumGet").val();
        date = $('#getDate').val();
        if($("#dateNumGet").val() == -1){
            dateNum = null;
            if($('#getDate').val() == null || $('#getDate').val() == '') {
                date = new Date(new Date() - 86400000);
                date = date.Format("yyyy-MM-dd");
            }
        }
        if($("#dateNumGet").val() == 1){
            dateNum = null;
            if($('#getDate').val() == null || $('#getDate').val() == '') {
                date = new Date();
                date = date.Format("yyyy-MM-dd");
            }
        }
//        if(dateNum == "" && date == ""){
//            date = new Date();
//            date = date.Format("yyyy-MM-dd");
//        }
        $('#dg').datagrid('load',{
            date: date,
            dateNum: dateNum,
            appType: $('#appType').combobox('getValue'),
            parentId: $('#parentId').combobox('getValue')
        })
    };


    doQuery = function(value,row,index){
        return  '<a href="#" onclick="doRead(\''+row.channelId+'\',\''+row.readNum+'\')">查看</a>';

    };

    //查看列表
    doRead = function (channelId,readNum){
        if(0 == readNum){
            $.messager.alert('获取列表失败', '该标签下阅读量为0', 'error');
        }else{
            var dateNum;
            var date;
            dateNum = $("#dateNumGet").val();
            date = $('#getDate').val();
            if($("#dateNumGet").val() == -1){
                dateNum = null;
                if($('#getDate').val() == null || $('#getDate').val() == '') {
                    date = new Date(new Date() - 86400000);
                    date = date.Format("yyyy-MM-dd");
                }
            }
            if($("#dateNumGet").val() == 1){
                dateNum = null;
                if($('#getDate').val() == null || $('#getDate').val() == '') {
                    date = new Date();
                    date = date.Format("yyyy-MM-dd");
                }
            }
            $('#ggg').datagrid('load',{
                date: date,
                dateNum: dateNum,
                navChannelId: channelId,
                theClass: 2
            });
            $('#dlg').dialog('open');
        }

    };

    //详情
    function doInfo(value,row,index){
        return  '<a href="#" onclick="doGetInfo('+row.id+',\''+row.infoType+'\')">查看</a>';
    }

    function doGetInfo(id,infoType) {
        $('#readInfo').dialog('open');
        $.post("/info/rank/content",{id:id,infoType:infoType},function (data) {
            if(infoType == "news"){
                $('#readArticle').html(data).css("display","block");
                $("#readVideo").attr('src','').css("display","none");
            }else if(infoType == "video"){
                $("#readVideo").attr('src',data);
                $("#readVideo").css("display","block");
                $('#readArticle').css("display","none");

            }
        })
    }
    $(function(){
        $("#readInfo").dialog({
            onClose:function(){
                $("#readVideo").attr('src','');
            }
        })

    });

    getPic = function(value,row,index){
        return '<a href="javascript:void(0)" onclick="doGetPic(\`'+row.thumbnails+'\`)">展示</a>';
    };

    doGetPic = function(thumbnails){
        var pic = new Array();
        if(thumbnails.indexOf(",") >= 0 ) {
            pic = thumbnails.split(",");
        }else{
            pic[0] = thumbnails;
        }
        $.preLoadImages(pic);
        $('#showPic').dialog('open');
    };

    //    $(document).on("change",'select#appType',function(){
    //             var str = ''+ $('#appType').val();
    //             console.log(str);
    //            $('#parentId').combobox("/channel/list?appType=" + str});
    //    })
    //类别联动
    $('#appType').combobox({
        onSelect: function (row) {
            if (row != null) {
                $('#parentIdd').combobox({
                    url: "/channel/list?appType=" + row.appType
                });
            }
        }
    });

    $('#parentIdd').combobox({
        onSelect: function (row) {
            if (row != null) {
                $('#parentId').combobox({
                    url: "/first/tab/list?parentId=" + row.channelId
                });
            }
        }
    });


</script>
<div id="dlg" class="easyui-dialog" title="Top 30" data-options="closed:true" style="width:1200px;height:620px;padding:10px;">
    <table id="ggg" class="easyui-datagrid" title="列表展示" style="width:1176px;height:556px"
           data-options="singleSelect:true,collapsible:true,rownumbers:true,url:'/info/rank/list'">
        <thead>
        <tr>
            <th data-options="field:'infoType',width:40,formatter:function (value,row,index) {
                            var str = '';
                            if(value == 'video'){
                                str = '视频';
                            }else{
                                str = '文章';
                            }
                            return str;
                        }">类型</th>
            <th data-options="field:'channelName',width:60">导航</th>
            <th data-options="field:'firstTabName',width:60">一级标签</th>
            <th data-options="field:'secondTabName',width:60">二级标签</th>
            <th data-options="field:'title',width:260">标题</th>
            <th data-options="field:'label',width:120">关键词</th>
            <th data-options="field:'thumbnails',width:50,formatter:getPic">缩略图</th>
            <th data-options="field:'source',width:70">来源</th>
            <th data-options="field:'updateTime',width:75">更新时间</th>
            <th data-options="field:'readNum',align:'right',width:70">阅读量</th>
            <th data-options="field:'shareNum',align:'right',width:70">转发量</th>
            <th data-options="field:'collectNum',align:'right',width:70">收藏数</th>
            <th data-options="field:'commentNum',align:'right',width:70">评论数</th>
            <th data-options="field:'aa',align:'center',formatter:doInfo">查看</th>
        </tr>
        </thead>
    </table>
</div>

<div id="readInfo" class="easyui-dialog" title="详情" data-options="closed:true,resizable:true" style="width:900px;height:500px;padding:10px">
    <div id="readArticle" style="width: 100%; height: 100%"></div>
    <video id="readVideo" controls="controls" src="" width="100%" height="100%" ></video>
</div>

<div id="showPic" class="easyui-dialog" title="缩略图" data-options="closed:true,resizable:true" style="width:900px;padding:10px">
    <style type="text/css">
        #showPic ul li{float: left; width: 275px; padding: 8px; overflow: hidden;list-style-type: none;}
        #showPic ul li img{max-width: 275px;vertical-align: top; }
    </style>
    <ul></ul>
</div>
</body>
</html>