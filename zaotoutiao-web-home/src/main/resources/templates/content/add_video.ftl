<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>新增视频</title>
    <#assign ctx="${springMacroRequestContext.contextPath}" />
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
        <form id="video_add_form"  method="post">
            <input type="hidden" name="infoType" value="video"/>
            <table cellpadding="5">
                <tr>
                    <td>视频标题:</td>
                    <td><input class="easyui-textbox" type="text" name="title" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>视频封面：</td>
                    <td>
                        <div class="div1">
                            <div class="div2">上传图片</div>
                            <input name="pic" class="inputstyle" type="file" onClick="toUpLoad()" multiple="multiple" />
                        </div>
                        <div id="videoImgDiv"></div>
                        <div id="imgDialog" closed="true" class="easyui-dialog" title="原图"
                             data-options="maximizable:true,resizable:true,modal:true" style="width: 800px; height: 600px; padding: 10px">
                            <img alt="" src="" id="showImg"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>导航名称:</td>
                    <td><input class="easyui-combobox" type="text" id="channleId_add_video" name="channelId"  data-options="url:'/getChannels?appType=1',
                    textField:'name',valueField:'channelId',onSelect:function(params){
                               $('#firstLevel_add_video').combobox({
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
                           $('#channleId_add_video').combobox('select',data[0].channelId);
                        }
                    },required:true,editable:false"/></td>
                </tr>
                <tr>
                    <td>一级标签:</td>
                    <td><input class="easyui-combobox" id="firstLevel_add_video" name="firstLevel" data-options="onLoadSuccess:function(data){
                              if(data != null && data.length > 0){
                                  $('#firstLevel_add_video').combobox('select',data[0].channelId);
                              }
                          },editable:false,onSelect:function(params){$('#towLevel_add_video').combobox({
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
                        <input class="easyui-combobox" id="towLevel_add_video" name="twoLevel" data-options="onLoadSuccess:function(data){
                              if(data != null && data.length > 0){
                                  $('#towLevel_add_video').combobox('select',data[0].channelId);
                              }
                          },editable:false,"/>
                    </td>
                </tr>
                <tr>
                    <td>关键词：</td>
                    <td><textarea name="catInfoName" type="text/plain"></textarea></td>
                </tr>
                <tr>
                    <td>视频上传：</td>
                    <td><input name="video" type="file" onClick="toUpLoadVideo()" />
                        <div id="progress">
                            <div class="bar" style="width: 0%;">0%</div>
                        </div>
                        <div id="videoDiv" style="display: none"></div>
                    </td>
                </tr>
                <tr>
                    <td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" onclick="saveVideo();">确定</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="${ctx}/static/ueditor/lang/zh-cn/zh-cn.js"></script>"
<script type="text/javascript" src="${ctx}/static/fileupload/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="${ctx}/static/fileupload/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${ctx}/static/fileupload/jquery.fileupload.js"></script>
<script type="text/javascript" src="${ctx}/static/js/picsUpload.js"></script>

<script type="text/javascript">

    toUpLoad('pic', 'videoImgDiv', 'imgDialog') ;
    toUpLoadVideo('video', 'videoDiv');
    //initShowPic(null,null,'imgdiv', 'imgDialog');
</script>
</body>
</html>
