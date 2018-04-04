<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>关键词排行</title>
    <link href="/static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/uimaker/easyui.css">
    <link rel="stylesheet" href="/static/uimaker/icon.css">
</head>
<body>
<div class="container">
    <table id="dg" class="easyui-datagrid" title="列表展示" style="width:100%;height:554px"
           data-options="rownumbers:true,
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
            <th data-options="field:'infoType',width:40">类型</th>
            <th data-options="field:'label',width:60">关键词</th>
            <th data-options="field:'readNum',width:60">浏览量</th>
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
            <select id="appType" name="appType" style="height:35px; width: 100px; text-align: center">
                <option  value="1">新闻</option>
                <option value="0">视频</option>
            </select>
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
        });
    })(jQuery);


    //数据分页
    $(function () {
        $('#dg').datagrid({url:"/keyword/rank/list"}).datagrid('clientPaging');

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
    function toSearch(){
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
        $('#dg').datagrid('load',{
            date: date,
            dateNum: dateNum,
            appType: $('#appType').val()
        })
    }
</script>

</body>
</html>