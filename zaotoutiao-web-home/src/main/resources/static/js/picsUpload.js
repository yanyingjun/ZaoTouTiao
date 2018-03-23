
// 1.1)图片上传
/**
 * 
 * @param basepath 上传图片base路径 
 * @param pic 上传图片名字（目前只能是pic）
 * @param imgsDiv 显示图片的divId
 * @param showImgDialog 显示原图的弹窗id
 */
function toUpLoad(pic, imgsDiv, showImgDialog) {
	$("input[name='"+pic+"']").fileupload({
		url : "/upload/listPic",// 上传地址
		acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
		dataType : 'json',
		done : function(e, data) {
			var count = $("#"+imgsDiv).find(".picDivS").length;
			reloadPic(imgsDiv,count, data.data.picUrl, data.data.picUrl, showImgDialog,false);
			//count++;
		}
	}).on('fileuploadprocessalways', function (e, data) {
		if(data.files.error){
		   $(this).parent().children("p").remove();
		   $(this).removeAttr("disabled");
		   if(data.files[0].error){
			   $.alert("图片类型错误,仅限gif,jpg,jpeg,png格式");
		   }
		} 
	});
}

// 1.2)加载图片
function reloadPic(imgsDiv, index, url, path, imgDialog,view) {
	var html = '<div class="picDivS" id = "pic_' + index + '"  >';
	html += '<img title="" src="' + url + '" alt="" width="100px" height="100px" onclick="showPic(\''+imgDialog+'\',\''+url+'\');" style="cursor: pointer;">';
	if(!view){
		html += '<i class="delfilebtn ifont" OnClick="delPic(this)">×</i>';
		//html += '<input type="button" name="delBtn" value="删除" OnClick="delPic(this)">';
		html += '<input name="picList[' + index + '].picUrl" type="hidden" value="' + path + '">';
		//html += '<input name="picInfo[' + index + '].picName" type="hidden" value="' + path + '">';
	}
	html += '</div>';
	$("#"+imgsDiv).append(html);
}

// 1.3)删除图片
function delPic(obj) {
	var firstdiv = $(obj).parent("div").parent();
	// 置空
	$(obj).parent("div").remove();
	firstdiv.find("div[id^='pic']").each(function(i,v){
		$(v).attr("id","pic_"+i);
		$(v).find("input").each(function(k,inp){
			var nm = $(inp).attr("name");
			nm = nm.replace(/\[\d+\]/,"["+i+"]");
			$(inp).attr("name",nm);
		});
	})
}

// 1.4)查看大图
function showPic(imgDialog, url) {
	if (url != null && url != "") {
		$("#"+imgDialog).dialog('open');
		var imgObj = $("#"+imgDialog).find("img");
		if(imgObj==null || imgObj.length == 0 ){
			imgObj = $("<img>");
			$("#"+imgDialog).append(imgObj);
		}
		imgObj.attr("src", url);
	}
}

/**
 * 
 * @param imgServer 图片访问服务地址
 * @param picPaths 图片相对路径（可以用，隔开）
 * @param imgDiv 显示图片的divId
 * @param showImgDialog 显示原图的弹窗id
 */
function initShowPic(imgServer,picPaths,imgsDiv, showImgDialog,view){
	view = (view==undefined)? false:view;
	if(picPaths.length>0){
		if(!imgServer.endWith("/")){
			imgServer = imgServer+"/";
		}
		var picStrs = picPaths.split(",");
		for(var i = 0 ; i<picStrs.length;i++){
			if(picStrs[i]!=null && picStrs[i]!=''){
				var url = imgServer+picStrs[i];
				reloadPic(imgsDiv, i, url, picStrs[i], showImgDialog,view) 
			}
		}
	}
}

String.prototype.endWith=function(s){
	  if(s==null||s==""||this.length==0||s.length>this.length)
	     return false;
	  if(this.substring(this.length-s.length)==s)
	     return true;
	  else
	     return false;
	  return true;
	 }
String.prototype.startWith=function(s){
	  if(s==null||s==""||this.length==0||s.length>this.length)
	   return false;
	  if(this.substr(0,s.length)==s)
	     return true;
	  else
	     return false;
	  return true;
	 }