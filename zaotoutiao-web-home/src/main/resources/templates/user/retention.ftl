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
        <form id="form_ret" method="post">
            <div class="easyui-panel" style="padding:5px;">
                <a href="#" class="easyui-linkbutton" id="today_ret" data-options="toggle:true,group:'g2',selected:true" onclick="getListBehavior('today')">今天</a>
                <a href="#" class="easyui-linkbutton" id="yesterday_ret" data-options="toggle:true,group:'g2'" onclick="getListBehavior('yesterday')">昨天</a>
                <a href="#" class="easyui-linkbutton" id="sevenDays_ret" data-options="toggle:true,group:'g2'" onclick="getListBehavior('sevenDays')">最近7天</a>
                <a href="#" class="easyui-linkbutton" id="thirtyDays_ret" data-options="toggle:true,group:'g2'" onclick="getListBehavior('thirtyDays')">最近30天</a>
                <a href="#" class="easyui-linkbutton" id="days_ret" data-options="toggle:true,group:'g2'" onclick="getListBehavior('days')">按日</a>
                <a href="#" class="easyui-linkbutton" id="weeks_ret" data-options="toggle:true,group:'g2'" onclick="getListBehavior('weeks')">按周</a>
                <a href="#" class="easyui-linkbutton" id="months_ret" data-options="toggle:true,group:'g2'" onclick="getListBehavior('months')">按月</a>
                开始时间<input class="easyui-datetimebox" name="startDate" id="startDate_ret" data-options="required:true" />
                结束时间<input class="easyui-datetimebox" name="endDate" id="endDate_ret"  data-options="required:true"/></td>
                <a href="#" class="easyui-linkbutton" id="hours_ret" data-options="toggle:true,group:'g2'" onclick="getListBehavior('hours')">查询</a>
            </div>
            <table style="padding-top: 5px;">
                <tr>
                    <td>平台</td>
                    <td>
                        <input class="easyui-combobox" name="platformId" id="platformId_ret" style="height:35px;" data-options="url:'/listPlatform',
                              textField:'platform',valueField:'id',onSelect:function(params){
                               $('#channelId_ret').combobox({
                                  url:'/listPlatformChannel',
                                  textField:'channelName',
                                  valueField:'id',
                                  onBeforeLoad:function(param){
                                     param.platformId = params.id
                                  }
                               })
                               showbehaviorInfo();
                           },
                           onLoadSuccess:function(data){
                                  if(data != null && data.length > 0){
                                     $('#platformId_ret').combobox('select',data[0].id);
                                  }

                               },required:true,editable:false"/>
                    </td>
                    <td>渠道</td>
                    <td>
                        <input class="easyui-combobox" name="channelId" id="channelId_ret" style="height:35px;" data-options="onLoadSuccess:function(data){
                              if(data != null && data.length > 0){
                                  $('#channelId_ret').combobox('select',data[0].id);
                              }
                          },required:true,editable:false,onSelect:function(params){showbehaviorInfo();}"/>
                    </td>
                    <td>指标</td>
                    <td>
                        <select class="easyui-combobox" name="activeIndex" id="activeIndex" style="height:35px;">
                            <option value="active">活跃用户</option>
                            <option value="notLogin">未登录用户</option>
                        </select>
                    </td>
                </tr>
            </table>
        </form>
        <table id="dg_ret" style="width:100%;height:100px" data-options="
            rownumbers:true,
            autoRowHeight:false,
            fitColumns:true,
            striped:true,
            collapsible:true,
            toolbar:'#tb'">
            <thead>
            <tr>
                <th field="active" width="110">活跃用户</th>
                <th field="notLogin" width="226">未登录用户</th>
            </tr>
            </thead>
            <tr>
                <td id="countActive"></td>
                <td id="countNoLogin"></td>
            </tr>
            <tbody>
            </tbody>
        </table>
    </div>
    <div id="bd">
    <div class="center-part">
        <div class="center-items chart0 clearfix">
            <div class="chart0-item">
                <div class="item-inner">
                    <div class="item-content">
                        <div class="content-hd">指标</div>
                        <div class="chart-chart" id="chart_retention"></div>
                    </div>
                </div>
            </div>
            <div class="chart0-item">
                <div class="item-inner">
                    <div class="item-content">
                        <div class="content-hd">数据列表</div>
                        <table id="ret_info" style="width:100%;height:auto;" data-options="
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

$('#activeIndex').combobox({
    onSelect:function () {
        showbehaviorInfo();
    }
})

function showbehaviorInfo(){
    var type;
    if($('#today_ret').text() == '今天'){
        type = 'today';
    }else if($('#yesterday_ret').text() == '昨天'){
        type = 'yesterday';
    }else if($('#sevenDays_ret').text() == '最近7天'){
        type = 'sevenDays';
    }else if($('#thirtyDays_ret').text() == '最近30天'){
        type = 'thirtyDays';
    }else if($('#days_ret').text() == '按日'){
        type = 'days';
    }else if($('#weeks_ret').text() == '按周'){
        type = 'weeks';
    }else if($('#months_ret').text() == '按月'){
        type = 'months';
    }else{
        type = 'hours';
    }
    getListBehavior(type);
}

$(function(){
    getListBehavior('today');
})
function getListBehavior(type){
    var channelId = $('#channelId_ret').combobox('getValue');
    var platformId = $('#platformId_ret').combobox('getValue');
    var startDate = $('#startDate_ret').datetimebox("getValue");
    var endDate = $('#endDate_ret').datetimebox("getValue");
    var index = $('#activeIndex').combobox('getValue');
    var name = '';
    $.ajax({
        type:"POST",
        url:"/retention/list?platformId="+platformId+'&channelId='+channelId+'&type='+type+'&activeType='+index+'&startDate='+startDate+'&endDate='+endDate,
        dataType:"json",
        contentType:"application/json",
        success:function(data){
            console.log(data)
            //赋值
            $('#countActive').html(data.activeNum);
            $('#countNotLogin').html(data.notLoginNum);
            $('#ret_info').empty();
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('chart_retention'));
            var arr_x = new Array();
            var arr_y = new Array();

            var strHtml = '<thead>\n' +
'                            <tr>\n' +
'                                <th field="code" width="110">日期</th>\n' +
'                                <th field="name" width="226">'+$('#activeIndex').combobox('getText')+'</th>\n' +
'                            </tr>\n' +
'                            </thead><tbody>';
            $.each(data.list,function(i,behavior){
                arr_x.push(behavior.times);
                if(index == 'active'){
                    arr_y.push(behavior.isOpenApp);
                    strHtml += '<tr><td>'+behavior.times+'</td><td>'+behavior.isOpenApp+'</td></tr>';
                }else{
                    arr_y.push(behavior.notLoginNum);
                    strHtml += '<tr><td>'+behavior.times+'</td><td>'+behavior.notLoginNum+'</td></tr>';
                }
            })
            strHtml += '</tbody>'
            $('#ret_info').append(strHtml);
            if(index == 'active'){
                name = "活跃用户";
            }else{
                name = "未登录用户";
            }

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
</script>
</body>
</html>


