<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>汇率设置</title>
    <link rel="stylesheet" href="/static/uimaker/easyui.css">
    <link rel="stylesheet" href="/static/uimaker/icon.css">
</head>
<body>
<h2>金币与零钱汇率</h2>
<p>当前汇率为：<b id="ex">${goldToMoney}</b></p>
<div style="margin:20px 0;"></div>
<div class="easyui-panel" title="请输入汇率" style="width:400px">
    <div style="padding:10px 60px 20px 60px">
        <form id="ff" method="post">
            <table cellpadding="5">
                <tr>
                    <td>汇率:</td>
                    <td><input class="easyui-textbox" type="number" id="exchange" name="exchange" data-options="required:true" style="height:35px;"><br />请输入正确的数字格式</td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">确认</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
        </div>
    </div>
</div>
<script type="text/javascript" src="/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/static/js/jquery.easyui.min.js"></script>

<script type="text/javascript">
    function submitForm(){
        if($('#exchange').val()>0.025 || $('#exchange').val()<0){
            $.messager.alert('提示','输入的值在0~0.025之间','error');
        }
        $('#ff').form('submit',{
            url:"/change/exchange",
            onSubmit: function(){
                // 传参数
                // do some check
                // return false to prevent submit;
            },
            success:function(data){
                if(data==1){
                    $.messager.alert('修改汇率请求','修改成功！请刷新查看当年汇率~','info');
                    //window.location.reload();
                }else if(data=='0'){
                    $.messager.alert('修改汇率请求','修改失败！','error');
                }
            }
        });
        //window.location.reload();
    }
    function clearForm(){
        $('#ff').form('clear');
    }
    
</script>

</body>
</html>