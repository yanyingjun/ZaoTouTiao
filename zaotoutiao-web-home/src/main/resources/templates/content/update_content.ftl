<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>更新文章</title>
    <#assign ctx="${springMacroRequestContext.contextPath}" />
</head>
<body>
<div class="container">
    <div id="tb" style="padding:0 30px;">
        <form id="content_update_form"  method="post">
            <input type="hidden" name="infoType" value="news"/>
            <input type="hidden" name="infoId" value="${infosVO.getInfoId()}" />
            <table cellpadding="5">
                <tr>
                    <td>文章标题:</td>
                    <td><input class="easyui-textbox" type="text" name="title" data-options="required:true" value="${infosVO.title}"/></td>
                </tr>
                <tr>
                    <td>文章封面：</td>
                    <td>
                        <div class="div1">
                            <div class="div2">上传图片</div>
                            <input name="pic" class="inputstyle" type="file" onClick="toUpLoad()" multiple="multiple" />
                        </div>
                        <div id="img_update_div"></div>
                        <div id="img_update_Dialog" closed="true" class="easyui-dialog" title="原图"
                             data-options="maximizable:true,resizable:true,modal:true" style="width: 800px; height: 600px; padding: 10px">
                            <img alt="" src="" id="showImg"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>导航名称:</td>
                    <td><input class="easyui-combobox" type="text" id="channleId_update_cont" name="channelId"  data-options="url:'/getChannels?appType=1',
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
                           $('#channleId_update_cont').combobox('select','${infosVO.channelId}');
                        }
                    },required:true,editable:false"/></td>
                </tr>
                <tr>
                    <td>一级标签:</td>
                    <td><input class="easyui-combobox" id="firstLevel_update_cont" name="firstLevel" data-options="onLoadSuccess:function(data){
                              if(data != null && data.length > 0){
                                  $('#firstLevel_update_cont').combobox('select','${infosVO.firstLevel}');
                              }
                          },editable:false,onSelect:function(params){$('#towLevel_cont').combobox({
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
                        <input class="easyui-combobox" id="towLevel_update_cont" name="twoLevel" data-options="onLoadSuccess:function(data){
                              if(data != null && data.length > 0){
                                  $('#towLevel_update_cont').combobox('select','${infosVO.twoLevel}');
                              }
                          },editable:false"/>
                    </td>
                </tr>
                <tr>
                    <td>关键词：</td>
                    <td><textarea name="catInfoName" type="text/plain">${infosVO.catInfoName}</textarea></td>
                </tr>
                <tr>
                    <td>文章详情：</td>
                    <td><textarea id="container_update_news" name="content" type="text/plain">${infosVO.content}</textarea></td>
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
    var ue = UE.getEditor('container_update_news',{
        zIndex : 9010,
        initialFrameWidth : 780,
        initialFrameHeight: 400
    });

    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage') {
            return '/upload/image';
        } else if(action == 'listimage'){
            return this._bkGetActionUrl.call(this, action);
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }

    var array = new Array();
    var obj = new Object();
    <#list infosVO.picList as file>
         var obj = new Object();
        obj.picUrl = '${file.picUrl}';
        obj.picName = '${file.picName}';
        array.push(obj);
    </#list>
    toUpLoad('pic', 'img_update_div', 'img_update_Dialog') ;
    initShowPic(array,'img_update_div', 'img_update_Dialog');
</script>
</body>
</html>
