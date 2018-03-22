<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>新增新闻</title>
    <link rel="stylesheet" href="/static/uimaker/easyui.css">
    <link rel="stylesheet" href="/static/uimaker/icon.css">
    <style>
        .upload_box{width:100%;}
        .upload_main{border-width:1px 1px 2px; border-style:solid; border-color:#ccc #ccc #ddd; background-color:#fbfbfb;}
        .upload_choose{padding:1em;}
        .upload_drag_area{display:inline-block; width:60%; padding:4em 0; margin-left:.5em; border:1px dashed #ddd; background: #f3f3f3; color:#999; text-align:center; vertical-align:middle;}
        .upload_drag_hover{border-color:#069; box-shadow:inset 2px 2px 4px rgba(0, 0, 0, .5); color:#333;}
        .upload_preview{border-top:1px solid #bbb; border-bottom:1px solid #bbb; background-color:#fff; overflow:hidden; _zoom:1;}
        .upload_append_list{height:300px; padding:0 1em; float:left; position:relative;}
        .upload_delete{margin-left:2em;}
        .upload_image{max-height:250px; padding:5px;}
        .upload_submit{padding-top:1em; padding-left:1em;}
        .upload_submit_btn{display:none; height:32px; font-size:14px;}
        .upload_loading{height:250px; background: #E0ECFF}
        .upload_progress{display:none; padding:5px; border-radius:10px; color:#fff; background-color:rgba(0,0,0,.6); position:absolute; left:25px; top:45px;}
    </style>
</head>
<body>
<div style="margin:20px 0;"></div>
<div class="" title="请输入新闻内容" style="width:100%px;border: 1px solid #ccc">
    <div style="padding:10px 60px 20px 60px">
        <#--<form id="ff" method="post" enctype="multipart/form-data">-->
            <#--<input type="file" name="upload_file" />-->
        <#--</form>-->
        <form id="ff" method="post">
            <div>
                <div style="float: left; margin: 20px; font-size: 14px;height: 25px; line-height: 25px; font-weight: 700">
                    文章标题:&nbsp;&nbsp;&nbsp;<input style="width: 50%; min-width: 420px; height: 25px; line-height: 25px; text-indent: 10px" type="text"/>
                </div>
                <div style="clear: both"></div>
            </div>
            <div>
                <div style="float: left; margin: 20px; font-size: 14px;height: 25px; line-height: 25px; font-weight: 700">
                    导航:&nbsp;&nbsp;&nbsp;<input style="height: 25px; line-height: 25px" id="parentIdDh" class="easyui-combobox" name="parentIdDh"
                                data-options="
                                                url:'/channel/list',
                                                method:'get',
                                                valueField:'id',
                                                textField:'name',
                                                panelHeight:'auto'">
                    一级标签：&nbsp;&nbsp;&nbsp;<input style="height: 25px; line-height: 25px" id="parentIdd" class="easyui-combobox" name="parentIdd"
                                data-options="
                                                method:'get',
                                                valueField:'id',
                                                textField:'name',
                                                panelHeight:'auto'">
                    二级标签：&nbsp;&nbsp;&nbsp;<input style="height: 25px; line-height: 25px" id="parentId" class="easyui-combobox" name="parentId"
                                data-options="
                                                method:'get',
                                                valueField:'id',
                                                textField:'name',
                                                panelHeight:'auto'">
                </div>
                <div style="clear: both"></div>
            </div>
            <div class="demo">
                <form id="uploadForm" action="/212" method="post" enctype="multipart/form-data">
                    <div class="upload_box">
                        <div class="upload_main">
                            <div class="upload_choose">
                                <input id="fileImage" type="file" size="30" name="fileselect[]" multiple accept="image/jpeg,image/png,image/gif" />
                                <span id="fileDragArea" class="upload_drag_area">缩略图，可将图片拖到此处</span>
                            </div>
                            <div id="preview" class="upload_preview"></div>
                        </div>
                        <div class="upload_submit">
                            <button type="button" id="fileSubmit" class="upload_submit_btn">确认上传图片</button>
                        </div>
                        <div id="uploadInf" class="upload_inf"></div>
                    </div>
                </form>
            </div>
            <div>
                <div style="float: left; margin: 20px; font-size: 14px;height: 25px; line-height: 25px; font-weight: 700">
                    关键词:&nbsp;&nbsp;&nbsp;<input style="width: 50%; min-width: 520px; line-height: 25px; text-indent: 10px" type="text" />
                </div>
                <div style="clear: both"></div>
            </div>
            <div>
                <div style="float: left; margin: 20px; font-size: 14px;height: 25px; line-height: 25px; font-weight: 700">
                    内容类别:&nbsp;&nbsp;&nbsp;
                    新闻：<input type="radio" name="infoType" value="article"/>
                    视频：<input type="radio" name="infoType" value="video"/>
                </div>
                <div style="clear: both"></div>
            </div>
                <hr/>
            <div>
                <div style=" font-size: 14px; font-weight: 700">
                    文章详情:&nbsp;&nbsp;&nbsp;
                    <script id="container" name="content" type="text/plain"></script>
                    <div style="clear: both"></div>
                </div>
            </div>
            <div style="clear: both"></div>
            <div style="text-align: center">=================   其他属性   ================</div>
            <div>
                <div style="float: left; margin: 20px; font-size: 14px;height: 25px; line-height: 25px; font-weight: 700">
                    关键词:&nbsp;&nbsp;&nbsp;<input style="width: 50%; min-width: 520px; line-height: 25px; text-indent: 10px" type="text" />
                </div>
                <div style="clear: both"></div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/static/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/static/js/img-upload.js"></script>
<script type="text/javascript" src="/static/UEditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="/static/UEditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    $(function(){
        var ue = UE.getEditor('container',{
            initialFrameHeight:300,
            autoHeightEnabled:false
        });
        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
        UE.Editor.prototype.getActionUrl = function(action) {
            if (action == 'uploadimage' || action == 'uploadimage') {
                return "/ueditor/uploadImg";
            }else if(action == 'uploadscrawl' ){
                return "/ueditor/uploadscrawl";
            } else if (action == 'uploadvideo') {
                return '/ueditor/uploadVideo';
            }else if (action == 'uploadfile') {
                return '/ueditor/uploadfile';
            } else {
                return this._bkGetActionUrl.call(this, action);
            }
        }
    })
</script>

<script>
    //联动
    $('#parentIdDh').combobox({
        onSelect: function (row) {
            if (row != null) {
                $('#parentIdd').combobox({
                    url: "/first/tab/list?parentId=" + row.id
                });
            }
        }
    });

    $('#parentIdd').combobox({
        onSelect: function (row) {
            if (row != null) {
                $('#parentId').combobox({
                    url: "/second/tab/list?parentId=" + row.id
                });
            }
        }
    });



    var params = {
        fileInput: $("#fileImage").get(0),
        dragDrop: $("#fileDragArea").get(0),
        upButton: $("#fileSubmit").get(0),
        url: $("#uploadForm").attr("action"),
        filter: function(files) {
            var arrFiles = [];
            for (var i = 0, file; file = files[i]; i++) {
                if (file.type.indexOf("image") == 0) {
                    if (file.size >= 512000) {
                        alert('您这张"'+ file.name +'"图片大小过大，应小于500k');
                    } else {
                        arrFiles.push(file);
                    }
                } else {
                    alert('文件"' + file.name + '"不是图片。');
                }
            }

            return arrFiles;
        },
        onSelect: function(files) {
            var html = '', i = 0;
            $("#preview").html('<div class="upload_loading"></div>');
            var funAppendImage = function() {
                file = files[i];
                if (file) {
                    var reader = new FileReader();
                    reader.onload = function(e) {
                        html = html + '<div id="uploadList_'+ i +'" class="upload_append_list"><p><strong>' + file.name + '</strong>'+
                                '<a href="javascript:" class="upload_delete" title="删除" data-index="'+ i +'">删除</a><br />' +
                                '<img id="uploadImage_' + i + '" src="' + e.target.result + '" class="upload_image" /></p>'+
                                '<span id="uploadProgress_' + i + '" class="upload_progress"></span>' +
                                '</div>';

                        i++;
                        funAppendImage();
                    }
                    reader.readAsDataURL(file);
                } else {
                    $("#preview").html(html);
                    if (html) {
                        //删除方法
                        $(".upload_delete").click(function() {
                            ZXXFILE.funDeleteFile(files[parseInt($(this).attr("data-index"))]);
                            return false;
                        });
                        //提交按钮显示
                        $("#fileSubmit").show();
                    } else {
                        //提交按钮隐藏
                        $("#fileSubmit").hide();
                    }
                }
            };
            funAppendImage();
        },
        onDelete: function(file) {
            $("#uploadList_" + file.index).fadeOut();
            $('#uploadForm')[0].reset();
        },
        onDragOver: function() {
            $(this).addClass("upload_drag_hover");
        },
        onDragLeave: function() {
            $(this).removeClass("upload_drag_hover");
        },
        onProgress: function(file, loaded, total) {
            var eleProgress = $("#uploadProgress_" + file.index), percent = (loaded / total * 100).toFixed(2) + '%';
            eleProgress.show().html(percent);
        },
        onSuccess: function(file, response) {
            $("#uploadInf").append("<p>上传成功，图片地址是：" + response + "</p>");
        },
        onFailure: function(file) {
            $("#uploadInf").append("<p>图片" + file.name + "上传失败！</p>");
            $("#uploadImage_" + file.index).css("opacity", 0.2);
        },
        onComplete: function() {
            //提交按钮隐藏
            $("#fileSubmit").hide();
            //file控件value置空
            $('#uploadForm')[0].reset();
            // 成功提示
            $("#uploadInf").append("<p>当前图片全部上传完毕，可继续添加上传。</p>");
        }
    };
    ZXXFILE = $.extend(ZXXFILE, params);
    ZXXFILE.init();
</script>

</body>
</html>