<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户存留</title>
    <link href="/static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/uimaker/easyui.css">
    <link rel="stylesheet" href="/static/css/workbench.css">
    <link rel="stylesheet" href="/static/uimaker/icon.css">
</head>
<body>
<div class="container">
    <div id="hd">

    </div>
    <div class="easyui-panel" title="存留" style="width:auto;padding: 10px 5px 5px 10px;" >
        <form id="form_um" method="post">
            <div class="easyui-panel" id="g3_div" style="padding:5px;">
                <a href="#" class="easyui-linkbutton" id="today_um" data-options="toggle:true,group:'g3',selected:true" onclick="getUserMoney('today')">今天</a>
                <a href="#" class="easyui-linkbutton" id="yesterday_um" data-options="toggle:true,group:'g3'" onclick="getUserMoney('yesterday')">昨天</a>
                <a href="#" class="easyui-linkbutton" id="sevenDays_um" data-options="toggle:true,group:'g3'" onclick="getUserMoney('sevenDays')">最近7天</a>
                <a href="#" class="easyui-linkbutton" id="thirtyDays_um" data-options="toggle:true,group:'g3'" onclick="getUserMoney('thirtyDays')">最近30天</a>
                开始时间<input class="easyui-datetimebox" name="startDate" id="startDate_um" data-options="required:true" />
                结束时间<input class="easyui-datetimebox" name="endDate" id="endDate_um"  data-options="required:true"/></td>
                <a href="#" class="easyui-linkbutton" id="hours_um" data-options="toggle:true,group:'g3'" onclick="getUserMoney('hours')">查询</a>
                <a href="#" class="easyui-linkbutton" id="days_um" data-options="toggle:true,group:'g5'" onclick="getShowMoney('days')">按日</a>
                <a href="#" class="easyui-linkbutton" id="weeks_um" data-options="toggle:true,group:'g5'" onclick="getShowMoney('weeks')">按周</a>
                <a href="#" class="easyui-linkbutton" id="months_um" data-options="toggle:true,group:'g5'" onclick="getShowMoney('months')">按月</a>
            </div>
            <div class="easyui-panel" style="padding:5px;">
                <a href="#" class="easyui-linkbutton" id="whole_user" data-options="toggle:true,group:'g4',selected:true" onclick="isNewOrOld('wholeUser')">全部</a>
                <a href="#" class="easyui-linkbutton" id="old_visitors " data-options="toggle:true,group:'g4'" onclick="isNewOrOld('newVisitors')">新访客</a>
                <a href="#" class="easyui-linkbutton" id="new_visitors " data-options="toggle:true,group:'g4'" onclick="isNewOrOld('oldVisitors')">老访客</a>
            </div>
            <table style="padding-top: 5px;">
                <tr>
                    <td>渠道</td>
                    <td>
                        <input class="easyui-combobox" name="channelId" id="channelId_um" style="height:35px;" data-options="url:'/listPlatformChannel?platformId=0',
                        textField:'channelName',valueField:'id',
                        onLoadSuccess:function(data){
                            if(data != null && data.length > 0){
                               $('#channelId_um').combobox('select',data[0].id);
                            }
                        },required:true,editable:false"/>
                    </td>
                    <td>指标</td>
                    <td>
                        <select class="easyui-combobox" name="moneyIndex" id="moneyIndex" style="height:35px;">
                            <option value="">全部</option>
                            <option value="0">注册登录(新人礼包)</option>
                            <option value="1">金币转换零钱</option>
                            <option value="3">首次收徒</option>
                        </select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div>可提现总额：<span id="userCount"></span> 满30人数：<span id="moneyNum"></span></div>
    <div id="bd">
        <div class="center-part">
            <div class="center-items chart0 clearfix">
                <div class="chart0-item">
                    <div class="item-inner">
                        <div class="item-content">
                            <div class="content-hd">零钱总量</div>
                            <div id="moneyAll"></div>
                            <div class="chart-chart" id="chart_moneyAll"></div>
                        </div>
                    </div>
                </div>
                <div class="chart0-item">
                    <div class="item-inner">
                        <div class="item-content">
                            <div class="content-hd">时间段内零钱产出总量</div>
                            <div id="moneyTimeAll"></div>
                            <div class="chart-chart" id="chart_time_money"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="center-part">
            <div class="center-items chart0 clearfix">
                <div class="chart0-item">
                    <div class="item-inner">
                        <div class="item-content">
                            <div class="content-hd">指标</div>
                            <div class="chart-chart" id="chart_money"></div>
                        </div>
                    </div>
                </div>
                <div class="chart0-item">
                    <div class="item-inner">
                        <div class="item-content">
                            <div class="content-hd">数据列表</div>
                            <table id="money_info" style="width:100%;height:auto;" data-options="
                    autoRowHeight:false,
                    fitColumns:true">
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="ft"></div>
</div>
</body>
</html>
<script type="text/javascript" src="/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/static/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/static/js/echarts-all.js"></script>




<script type="text/javascript">

//公开附件tab事件处理
$(".attached-tab").on("click","a",function(){
    $(this).closest(".attached-tab").find("a").removeClass("current");
    $(this).addClass("current");
    $(this).closest(".attached").find("ul").addClass("hide");
    $(this).closest(".attached").find("ul." + $(this).attr("attached")).removeClass("hide");
})
$('#channelId_um').combobox({
    onSelect:function () {
        showMoneyRecordInfo();
    }
})

$('#moneyIndex').combobox({
    onSelect:function () {
        var g3Text = $('a[group="g3"].l-btn-selected').text();
        var type;
        if(g3Text == '今天'){
            type = 'today';
        }else if(g3Text == '昨天'){
            type = 'yesterday';
        }else if(g3Text == '最近7天'){
            type = 'sevenDays';
        }else if(g3Text == '最近30天'){
            type = 'thirtyDays';
        }else if(g3Text == '按日'){
            type = 'days';
        }else if(g3Text == '按周'){
            type = 'weeks';
        }else if(g3Text == '按月'){
            type = 'months';
        }else{
            type = 'hours';
        }
        getShowMoney(type);
    }
})

function showMoneyRecordInfo(){
    var g2Text = $('a[group="g3"].l-btn-selected').text();
    var type;
    if(g2Text == '今天'){
        type = 'today';
    }else if(g2Text == '昨天'){
        type = 'yesterday';
    }else if(g2Text == '最近7天'){
        type = 'sevenDays';
    }else if(g2Text == '最近30天'){
        type = 'thirtyDays';
    }else if(g2Text == '按日'){
        type = 'days';
    }else if(g2Text == '按周'){
        type = 'weeks';
    }else if(g2Text == '按月'){
        type = 'months';
    }else{
        type = 'hours';
    }
    getListMoneyRecord(type,null);
}

function isNewOrOld(isNewOrOld) {
    var g3Text = $('a[group="g3"].l-btn-selected').text();
    var type;
    if(g3Text == '今天'){
        type = 'today';
    }else if(g3Text == '昨天'){
        type = 'yesterday';
    }else if(g3Text == '最近7天'){
        type = 'sevenDays';
    }else if(g3Text == '最近30天'){
        type = 'thirtyDays';
    }else if(g3Text == '按日'){
        type = 'days';
    }else if(g3Text == '按周'){
        type = 'weeks';
    }else if(g3Text == '按月'){
        type = 'months';
    }else{
        type = 'hours';
    }
    getListMoneyRecord(type,isNewOrOld);
}

function getShowMoney(type) {
    var g5Text = $('a[group="g5"].l-btn-selected').text();
    var newOrAnd;
    if(g5Text == '全部'){
        newOrAnd = 'wholeUser';
    }else if(g5Text == '新访客'){
        newOrAnd = 'newVisitors';
    }else if(g5Text == '老访客'){
        newOrAnd = 'oldVisitors';
    }
    var channelId = $('#channelId_um').combobox('getValue');
    var index = $('#moneyIndex').combobox('getValue');
    $.ajax({
        type:"POST",
        url:"/userMoney/listGroup?isNewOrOld="+newOrAnd+'&channelId='+channelId+'&type='+type+'&source='+index,
        dataType:"json",
        contentType:"application/json",
        success:function(data){
            $('#money_info').empty();
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('chart_money'));
            var arr_x = new Array();
            var arr_y = new Array();

            var strHtml = '<thead>\n' +
                    '                            <tr>\n' +
                    '                                <th field="code" width="110">日期</th>\n' +
                    '                                <th field="name" width="226">零钱数</th>\n' +
                    '                            </tr>\n' +
                    '                            </thead><tbody>';
            $.each(data.listUR,function(i,behavior){
                arr_x.push(behavior.times);
                arr_y.push(behavior.sumMoney);
                strHtml += '<tr><td>'+behavior.times+'</td><td>'+behavior.sumMoney+'</td></tr>';
            })
            strHtml += '</tbody>'
            $('#money_info').append(strHtml);
            var name = $('#moneyIndex').combobox('getText');

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: name+'统计'
                },
                legend: {
                    data:[name]
                },
                xAxis: {
                    data: arr_x
                },
                yAxis: {},
                series: [{
                    name: name,
                    type: 'line',
                    data: arr_y,
                    areaStyle: {}
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });
}

function getUserMoney(type) {
    var g4Text = $('a[group="g4"].l-btn-selected').text();
    var newOrAnd;
    if(g4Text == '全部'){
        newOrAnd = 'wholeUser';
    }else if(g4Text == '新访客'){
        newOrAnd = 'newVisitors';
    }else if(g4Text == '老访客'){
        newOrAnd = 'oldVisitors';
    }
    getListMoneyRecord(type, newOrAnd);
}

$(function(){

    getListMoneyRecord('today','wholeUser');
})
function getListMoneyRecord(type, isNewOrOld){
    var channelId = $('#channelId_um').combobox('getValue');
    var startDate = $('#startDate_um').datetimebox("getValue");
    var endDate = $('#endDate_um').datetimebox("getValue");
    var index = $('#moneyIndex').combobox('getValue');
    var name = '';
    $.ajax({
        type:"POST",
        url:"/userMoney/list?isNewOrOld="+isNewOrOld+'&channelId='+channelId+'&type='+type+'&source='+index+'&startDate='+startDate+'&endDate='+endDate,
        dataType:"json",
        contentType:"application/json",
        success:function(data){
            console.log(data)
            $('#userCount').text(data.userCount);
            $('#moneyNum').text(data.moneyNum);
            $('#moneyTimeAll').text(data.moneyTimeAll);
            $('#moneyAll').text(data.moneyAll);
            //金币产出总量
            moneyCoinStatistics (data.listAll, '零钱总量', 'chart_moneyAll');
            //时间段内总金币显示
            moneyCoinStatistics (data.list, '时间段内零钱产出总量', 'chart_time_money');

            $('#money_info').empty();
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('chart_money'));
            var arr_x = new Array();
            var arr_y = new Array();

            var strHtml = '<thead>\n' +
'                            <tr>\n' +
'                                <th field="code" width="110">日期</th>\n' +
'                                <th field="name" width="226">零钱数</th>\n' +
'                            </tr>\n' +
'                            </thead><tbody>';
            $.each(data.listUR,function(i,behavior){
                arr_x.push(behavior.times);
                arr_y.push(behavior.sumMoney);
                strHtml += '<tr><td>'+behavior.times+'</td><td>'+behavior.sumMoney+'</td></tr>';
            })
            strHtml += '</tbody>'
            $('#money_info').append(strHtml);
            var name = $('#moneyIndex').combobox('getText');

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: name+'统计'
                },
                legend: {
                    data:[name]
                },
                xAxis: {
                    data: arr_x
                },
                yAxis: {},
                series: [{
                    name: name,
                    type: 'line',
                    data: arr_y,
                    areaStyle: {}
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });


    function moneyCoinStatistics (list, name, id) {
        var arr_x_1 = [];
        var arr_y_1 = new Array();
        $.each(list,function(i,behavior){
            var opt = {};
            if(behavior.source == 0){
                opt.name = '注册登录(新人礼包)';
            }else if(behavior.source == 1){
                opt.name = '金币转换零钱';
            }else{
                opt.name = '首次收徒';
            }
            opt.value = behavior.sumMoney;
            arr_y_1.push(opt);
        })
        var moneyChart = echarts.init(document.getElementById(id));
        var option_money = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data:arr_x_1
            },
            series: [
                {
                    name:name,
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:arr_y_1
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        moneyChart.setOption(option_money);
    }
}
</script>
</body>
</html>


