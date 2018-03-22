//1.1)图片上传
/**
 *
 * @param basepath 上传图片base路径
 * @param pic 上传图片名字（目前只能是pic）
 * @param imgsDiv 显示图片的divId
 * @param showImgDialog 显示原图的弹窗id
 */
function toUpLoadByType(basepath ,pic, imgsDiv,type,typeName, showImgDialog) {
		$("input[name='"+pic+"']").fileupload({
			url : basepath+"/upload/uploadPic",// 上传地址
			acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
			dataType : 'json',
			done : function(e, data) {
				var count = $("#"+imgsDiv).find(".picDivS").length;
				reloadPicByType(imgsDiv,count, data.result.url, data.result.path, showImgDialog,type,typeName,false);
				//count++;
			}
		}).on('fileuploadprocessalways', function (e, data) {
			debugger;
			type = $('#busType').val();
			typeName = $('#busTypeName').text();
			if(data.files.error){
			   $(this).parent().children("p").remove();
			   $(this).removeAttr("disabled");
			   if(data.files[0].error){
				   $.alert("图片类型错误,仅限gif,jpg,jpeg,png格式");
			   }
			}
			/*if(type == 0){
				if()
			}*/
		});
}

// 1.2)加载图片
function reloadPicByType(imgsDiv, index, url, path, imgDialog,type,typeName,view) {
	var html = '<div class="picDivS" style="height:120px;width:110px;" id = "pic_' + index + '"  ><span id="busTypeName">'+typeName+'</span> ';
	html += '<img title="" src="' + url + '" alt="" width="110px" height="100px" onclick="showPic(\''+imgDialog+'\',\''+url+'\');" style="cursor: pointer;">';
	if(!view){
		html += '<i class="delfilebtn ifont" OnClick="delPic(this)">×</i>';
		html += '<input name="picList[' + index + '].picUrl" type="hidden" value="' + path + '">';
		html += '<input name="picList[' + index + '].businessType" type="hidden" value="' + type + '">';
	}
	html += '</div>';
	$("#"+imgsDiv).append(html);
}

/**
 *
 * @param imgServer 图片访问服务地址
 * @param picPaths 图片相对路径（可以用，隔开）
 * @param imgDiv 显示图片的divId
 * @param showImgDialog 显示原图的弹窗id
 */
function initShowPicByType(imgServer,picInfo,imgsDiv,showImgDialog,type,typeName,view){
	view = (view==undefined)? false:view;
	var obj = jQuery.parseJSON(picInfo);
	debugger;
	if(obj.length>0){
		if(!imgServer.endWith("/")){
			imgServer = imgServer+"/";
		}
		for(var i = 0 ; i<obj.length;i++){
			if(obj[i].picUrl != null && obj[i].picUrl != '' && obj[i].businessType != '' && obj[i].businessType != null ){
				var url = imgServer + obj[i].picUrl;
				reloadPicByType(imgsDiv, i, url, obj[i].picUrl,showImgDialog,obj[i].businessType,obj[i].businessTypeName,view)
			}
		}
	}
}
/***************************  文章图片上传  *************************************/
/**
 *
 * @param basepath 上传图片base路径
 * @param pic 上传图片名字（目前只能是pic）
 * @param imgsDiv 显示图片的divId
 * @param showImgDialog 显示原图的弹窗id
 */
function toUpLoadCms(basepath ,pic, imgsDiv, showImgDialog) {
	debugger;
	$("input[name='"+pic+"']").fileupload({
		url : basepath+"/upload/uploadPic",// 上传地址
		acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
		dataType : 'json',
		done : function(e, data) {
			debugger;
			var count = $("#"+imgsDiv).find(".picDivS").length;
			reloadPicCms(imgsDiv,count, data.result.url, data.result.path, showImgDialog,false);
			//count++;
		}
	}).on('fileuploadprocessalways', function (e, data) {
		if(data.files.error){
		   $(this).parent().children("p").remove();
		   $(this).removeAttr("disabled");
		   if(data.files[0].error){
			   $.alert("图片类型错误,仅限gif,jpg,jpeg,png格式");
		   }
		   if(data.files.length > 1){
			   $.alert("只能上传一张封面");
		   }
		}
	});
}

// 1.2)加载图片
function reloadPicCms(imgsDiv, index, url, path, imgDialog,view) {
	var html = '<div class="picDivS" id = "pic_' + index + '"  >';
	html += '<img title="" src="' + url + '" alt="" width="100px" height="100px" onclick="showPic(\''+imgDialog+'\',\''+url+'\');" style="cursor: pointer;">';
	if(!view){
		html += '<i class="delfilebtn ifont" OnClick="delPicCms(this)">×</i>';
		//html += '<input type="button" name="delBtn" value="删除" OnClick="delPic(this)">';
		html += '<input name="cover" type="hidden" value="' + path + '">';
		//html += '<input name="picInfo[' + index + '].picName" type="hidden" value="' + path + '">';
	}
	html += '</div>';
	$("#"+imgsDiv).append(html);
}

// 1.3)删除图片
function delPicCms(obj) {
	var firstdiv = $(obj).parent("div").parent();
	// 置空
	$(obj).parent("div").remove();
	firstdiv.find("div[id^='cover']").each(function(i,v){
		$(v).find("input").each(function(k,inp){
			var nm = $(inp).attr("name");
			nm = nm.replace(/\[\d+\]/,"["+i+"]");
			$(inp).attr("name",nm);
		});
	})
}

