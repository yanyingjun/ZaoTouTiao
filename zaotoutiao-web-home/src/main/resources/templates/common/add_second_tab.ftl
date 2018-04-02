<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>新增导航</title>
    <link rel="stylesheet" href="/static/uimaker/easyui.css">
    <link rel="stylesheet" href="/static/uimaker/icon.css">
</head>
<body>
<div style="margin:20px 0;"></div>
<div class="easyui-panel" title="请输入标签信息" style="width:100%px">
    <div style="padding:10px 60px 20px 60px">
        <form id="ff" method="post">
            <table cellpadding="5">
                <tr>
                    <td>名称:</td>
                    <td><input type="text" id="name" name="name" style="height:35px;"><br />至少2字节</td>
                    <td>渠道ID:</td>
                    <td><input type="number" id="channelId" name="channelId" style="height:35px;"><br />数字格式</td>
                    <td>渠道类型:</td>
                    <td><input type="number" id="channelType" name="channelType" style="height:35px;"><br />数字格式</td>
                    <td>类别:</td>
                    <td><input id="appType" class="easyui-combobox"
                               name="appType"
                               data-options="
                    url:'/app/type/list',
					method:'get',
					valueField:'appType',
					textField:'name',
					panelHeight:'auto'
			"></td>
                    <td>导航:</td>
                    <td><input id="parentIdDh" class="easyui-combobox" name="parentIdDh" data-options="
					method:'get',
					valueField:'channelId',
					textField:'name',
					panelHeight:'auto'"></td>
                    <td>一级标签:</td>
                    <td><input id="parentId" class="easyui-combobox" name="parentId" data-options="
					method:'get',
					valueField:'channelId',
					textField:'name',
					panelHeight:'auto'"></td>
                <#--<td>状态：</td>-->
                <#--<td><select id="status" name="status" style="height:35px; width: 100px; text-align: center">-->
                <#--<option value="1">激活</option>-->
                <#--<option value="0">下架</option>-->
                <#--</select></td>-->

                <#--<td>类型：</td>-->
                <#--<td><select id="appType" name="appType" style="height:35px; width: 100px; text-align: center">-->
                <#--<option value="1">新闻</option>-->
                <#--<option value="0">视频</option>-->
                <#--</select></td>-->
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">确认</a>
        </div>
    </div>
</div>
<script type="text/javascript" src="/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/static/js/jquery.easyui.min.js"></script>

<script type="text/javascript">
    function submitForm(){
        if($('#name').val().length<2){
            $.messager.alert('提示','名称至少2字节','error');
        }else if($('#parentId').combobox('getValue') =='' || $('#parentId').combobox('getValue') == null){
            $.messager.alert('提示','请选择您标签的上一级','error')
        }else{
            $('#ff').form('submit',{
                url:"/add/channel/do",
                onSubmit: function(){

                },
                success:function(data){
                    if(data==1){
                        $.messager.alert('新增标签','新增成功！','info');
                        //window.location.reload();
                    }else if(data=='0'){
                        $.messager.alert('新增标签','新增失败！','error');
                    }
                }
            });
        }
    }

    $('#parentIdDh').combobox({
        onSelect: function (row) {
            if (row != null) {
                $('#parentId').combobox({
                    url: "/first/tab/list?parentId=" + row.channelId
                });
            }
        }
    });
    $('#appType').combobox({
        onSelect: function (row) {
            if (row != null) {
                $('#parentIdDh').combobox({
                    url: "/channel/list?appType=" + row.appType
                });
            }
        }
    });

</script>

</body>
</html>