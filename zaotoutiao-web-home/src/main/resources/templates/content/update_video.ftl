<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>更新视频</title>
    <#assign ctx="${springMacroRequestContext.contextPath}" />
    <link href="${ctx}/static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/static/uimaker/easyui.css">
    <link rel="stylesheet" href="${ctx}/static/uimaker/icon.css">
    <style>
        .bar {
            height: 18px;
            background: lightcoral;
        }
    </style>
</head>
<body>
<div class="container">
    <div id="tb" style="padding:0 30px;">
        <form id="video_update_form"  method="post">
            <input type="hidden" name="infoType" value="video"/>
            <input type="hidden" name="infoId" value="${infosVO.getInfoId()}" />
            <table cellpadding="5">
                <tr>
                    <td>视频标题:</td>
                    <td><input class="easyui-textbox" type="text" name="title" data-options="required:true" value="${infosVO.title}"/></td>
                </tr>
                <tr>
                    <td>视频封面：</td>
                    <td>
                        <div class="div1">
                            <div class="div2">上传图片</div>
                            <input name="pic" class="inputstyle" type="file" onClick="toUpLoad()" multiple="multiple" />
                        </div>
                        <div id="video_update_ImgDiv"></div>
                        <div id="video_update_imgDialog" closed="true" class="easyui-dialog" title="原图"
                             data-options="maximizable:true,resizable:true,modal:true" style="width: 800px; height: 600px; padding: 10px">
                            <img alt="" src="" id="showImg"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>导航名称:</td>
                    <td><input class="easyui-combobox" type="text" id="channleId_update_video" name="channelId"  data-options="url:'/getChannels?appType=1',
                    textField:'name',valueField:'channelId',onSelect:function(params){
                               $('#firstLevel_update_video').combobox({
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
                           $('#channleId_update_video').combobox('select','${infosVO.channelId}');
                        }
                    },required:true,editable:false"/></td>
                </tr>
                <tr>
                    <td>一级标签:</td>
                    <td><input class="easyui-combobox" id="firstLevel_update_video" name="firstLevel" data-options="onLoadSuccess:function(data){
                              if(data != null && data.length > 0){
                                  $('#firstLevel_update_video').combobox('select','${infosVO.channelId}');
                              }
                          },editable:false,onSelect:function(params){$('#towLevel_update_video').combobox({
                                  url:'/subnavigation',
                                  textField:'name',
                                  valueField:'channelId',
                                  onBeforeLoad:function(param){
                                     param.parentId = params.channelId;
                                     param.appType = params.appType
                                  }
                               })}"/></td>
                </tr>
                <tr>
                    <td>二级标签:</td>
                    <td>
                        <input class="easyui-combobox" id="towLevel_update_video" name="twoLevel" data-options="onLoadSuccess:function(data){
                              if(data != null && data.length > 0){
                                  $('#towLevel_update_video').combobox('select','${infosVO.channelId}');
                              }
                          },editable:false,"/>
                    </td>
                </tr>
                <tr>
                    <td>关键词：</td>
                    <td><textarea name="catInfoName" type="text/plain">${infosVO.catInfoName}</textarea></td>
                </tr>
                <tr>
                    <td>视频上传：</td>
                    <td><input name="video" type="file" onClick="toUpLoadVideo()" />
                        <div id="progress">
                            <div class="bar" style="width: 0%;">0%</div>
                        </div>
                        <div id="update_videoDiv" style="display: none"></div>
                        <div id="update_videoDialog" closed="true" class="easyui-dialog" title="原图"
                             data-options="maximizable:true,resizable:true,modal:true" style="width: 800px; height: 600px; padding: 10px">
                            <img alt="" src="" id="showImg"/>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="${ctx}/static/ueditor/lang/zh-cn/zh-cn.js"></script>"
<script type="text/javascript" src="${ctx}/static/fileupload/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="${ctx}/static/fileupload/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${ctx}/static/fileupload/jquery.fileupload.js"></script>
<script type="text/javascript" src="${ctx}/static/js/picsUpload.js"></script>

<script type="text/javascript">

    function saveVideo(){
        if ($('#video_add_form').form('validate')) {
            var jsondata=$('#video_add_form').serializeObject();
            $.post('/infos/add', jsondata, function(data) {
                if (data.state == 'success') {
                    $.messager.alert('提示信息', data.msg);
                    $('#video_add_form').form('clear');
                } else {
                    $.messager.alert('提示信息', data.msg);
                }
            }, "JSON");
        }
    }

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

    var array_news = new Array();
    <#list infosVO.picList as file>
        var obj_news = new Object();
        obj_news.picUrl = '${file.picUrl}';
        obj_news.picName = '${file.picName}';
        array_news.push(obj_news);
    </#list>

    var array = new Array();
    <#list infosVO.videoList as video>
         var obj = new Object();
        obj.picUrl = '${video.picUrl}';
        obj.picName = '${video.picName}';
        array.push(obj);
    </#list>
    console.debug(array);

    toUpLoad('pic', 'video_update_ImgDiv', 'video_update_imgDialog') ;
    initShowPic(array_news,'video_update_ImgDiv', 'video_update_imgDialog');
    toUpLoadVideo('video', 'update_videoDiv');
    initShowVideo(array,'update_videoDiv', 'update_videoDialog');
</script>
</body>
</html>
