<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户行为统计</title>
    <link href="/static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/uimaker/easyui.css">
    <link rel="stylesheet" href="/static/css/workbench.css">
    <link rel="stylesheet" href="/static/uimaker/icon.css">
</head>
<body>
<div class="container">
    <div id="hd">

    </div>
    <div class="easyui-panel" title="统计" style="width:auto;padding: 10px 5px 5px 10px;" >
        <form id="ff" method="post">
            <div class="easyui-panel" style="padding:5px;">
                <a href="#" class="easyui-linkbutton" id="today" data-options="toggle:true,group:'g1',selected:true" onclick="getListBehavior('today')">今天</a>
                <a href="#" class="easyui-linkbutton" id="yesterday" data-options="toggle:true,group:'g1'" onclick="getListBehavior('yesterday')">昨天</a>
                <a href="#" class="easyui-linkbutton" id="sevenDays" data-options="toggle:true,group:'g1'" onclick="getListBehavior('sevenDays')">最近7天</a>
                <a href="#" class="easyui-linkbutton" id="thirtyDays" data-options="toggle:true,group:'g1'" onclick="getListBehavior('thirtyDays')">最近30天</a>
                <a href="#" class="easyui-linkbutton" id="days" data-options="toggle:true,group:'g1'" onclick="getListBehavior('days')">按日</a>
                <a href="#" class="easyui-linkbutton" id="weeks" data-options="toggle:true,group:'g1'" onclick="getListBehavior('weeks')">按周</a>
                <a href="#" class="easyui-linkbutton" id="months" data-options="toggle:true,group:'g1'" onclick="getListBehavior('months')">按月</a>
                开始时间<input class="easyui-datebox" name="startDate" id="startDate" data-options="required:true" />
                结束时间<input class="easyui-datebox" name="endDate" id="endDate"  data-options="required:true"/></td>
                <a href="#" class="easyui-linkbutton" id="hours" data-options="toggle:true,group:'g1'" onclick="getListBehavior('hours')">查询</a>
            </div>
            <table style="padding-top: 5px;">
                <tr>
                    <td>平台</td>
                    <td>
                        <input class="easyui-combobox" name="platformId" id="platformId" style="height:35px;" data-options="url:'/listPlatform',
                              textField:'platform',valueField:'id',onSelect:function(params){
                               $('#channelId').combobox({
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
                                     $('#platformId').combobox('select',data[0].id);
                                  }

                               },required:true,editable:false"/>
                    </td>
                    <td>渠道</td>
                    <td>
                        <input class="easyui-combobox" name="channelId" id="channelId" style="height:35px;" data-options="onLoadSuccess:function(data){
                              if(data != null && data.length > 0){
                                  $('#channelId').combobox('select',data[0].id);
                              }
                          },required:true,editable:false,onSelect:function(params){showbehaviorInfo();}"/>
                    </td>
                    <td>指标</td>
                    <td>
                        <select class="easyui-combobox" name="index" id="index" style="height:35px;">
                            <option value="download">下载量</option>
                            <option value="activationRate">激活率</option>
                            <option value="activation">激活</option>
                            <option value="registerRate">注册率</option>
                            <option value="register">注册</option>
                            <option value="operation">操作次数</option>
                        </select>
                    </td>
                </tr>
            </table>
        </form>
        <table id="dg" style="width:100%;height:100px" title="用户行为统计列表" data-options="
            rownumbers:true,
            autoRowHeight:false,
            fitColumns:true,
            striped:true,
            collapsible:true,
            toolbar:'#tb'">
            <thead>
            <tr>
                <th field="downloadNum" width="110">下载量</th>
                <th field="activationRate" width="226">激活率</th>
                <th field="activationNum" width="112">激活</th>
                <th field="registerRate" width="170">注册率</th>
                <th field="registerNum" width="130">注册</th>
                <th field="operationNum" width="136">操作次数</th>
            </tr>
            </thead>
            <tr>
                <td id="count1"></td>
                <td id="count2"></td>
                <td id="count3"></td>
                <td id="count4"></td>
                <td id="count5"></td>
                <td id="count6"></td>
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
                        <div class="chart-chart" id="chart0"></div>
                    </div>
                </div>
            </div>
            <div class="chart0-item">
                <div class="item-inner">
                    <div class="item-content">
                        <div class="content-hd">数据列表</div>
                        <table id="beh_info" style="width:100%;height:auto;" data-options="
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

$('#index').combobox({
    onSelect:function () {
        showbehaviorInfo();
    }
})

function showbehaviorInfo(){
    var type;
    if($('#today').val() == '今天'){
        type = 'today';
    }else if($('#yesterday').val() == '昨天'){
        type = 'yesterday';
    }else if($('#sevenDays').val() == '最近7天'){
        type = 'sevenDays';
    }else if($('#thirtyDays').val() == '最近30天'){
        type = 'thirtyDays';
    }else if($('#days').val() == '按日'){
        type = 'days';
    }else if($('#weeks').val() == '按周'){
        type = 'weeks';
    }else if($('#months').val() == '按月'){
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
    var channelId = $('#channelId').combobox('getValue');
    var platformId = $('#platformId').combobox('getValue');
    var startDate = $('#startDate').val();
    var endDate = $('#endDate').val();
    var index = $('#index').combobox('getValue');
    var name = '';
    $.ajax({
        type:"POST",
        url:"/behavior/list?platformId="+platformId+'&channelId='+channelId+'&type='+type+'&startDate='+startDate+'&endDate='+endDate,
        dataType:"json",
        contentType:"application/json",
        success:function(data){
            console.log(data)
            //赋值
            $('#count1').html(data.countDownLoadNum);
            $('#count2').html(data.countActivationRateNum);
            $('#count3').html(data.countActivationNum);
            $('#count4').html(data.countRegisterRateNum);
            $('#count5').html(data.countRegisterNum);
            $('#count6').html(data.countOperationNum);
            $('#beh_info').empty();
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('chart0'));
            var arr_x = new Array();
            var arr_y = new Array();

            var strHtml = '<thead>\n' +
'                            <tr>\n' +
'                                <th field="code" width="110">日期</th>\n' +
'                                <th field="name" width="226">'+$('#index').combobox('getText')+'</th>\n' +
'                            </tr>\n' +
'                            </thead><tbody>';
            $.each(data.list,function(i,behavior){
                arr_x.push(behavior.times);
                if(index == 'download'){
                    arr_y.push(behavior.downloadNum);
                    strHtml += '<tr><td>'+behavior.times+'</td><td>'+behavior.downloadNum+'</td></tr>';
                }else if(index == 'activationRate'){
                    arr_y.push(behavior.activationRate);
                    strHtml += '<tr><td>'+behavior.times+'</td><td>'+behavior.activationRate+'</td></tr>';
                }else if(index == 'activation'){
                    arr_y.push(behavior.activationNum);
                    strHtml += '<tr><td>'+behavior.times+'</td><td>'+behavior.activationNum+'</td></tr>';
                }else if(index == 'registerRate'){
                    arr_y.push(behavior.registerRate);
                    strHtml += '<tr><td>'+behavior.times+'</td><td>'+behavior.registerRate+'</td></tr>';
                }else if(index == 'register'){
                    arr_y.push(behavior.registerNum);
                    strHtml += '<tr><td>'+behavior.times+'</td><td>'+behavior.registerNum+'</td></tr>';
                }else{
                    arr_y.push(behavior.operationNum);
                    strHtml += '<tr><td>'+behavior.times+'</td><td>'+behavior.operationNum+'</td></tr>';
                }
            })
            strHtml += '</tbody>'
            $('#beh_info').append(strHtml);
            if(index == 'download'){
                name = "下载量";
            }else if(index == 'activationRate'){
                name = "激活率";
            }else if(index == 'activation'){
                name = "激活";
            }else if(index == 'registerRate'){
                name = "注册率";
            }else if(index == 'register'){
                name = "注册";
            }else{
                name = "操作次数";
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


