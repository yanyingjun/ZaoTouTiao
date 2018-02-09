<!-- 字典类型配置信息添加 start -->
<div id="dictTypeCreateContent" class="hidden">
	<form class="form-horizontal" id="dictTypeCreateForm" action="../dict_type/insert.json" class="form-horizontal" method="post">
		<div class="row">
	  		<div class="control-group span25">
	    		<label class="control-label"><s>*</s>类型名称：</label>
	    		<div class="controls">
	      			<input name="name" type="text" data-rules="{required:true,maxlength:30}" 
							data-messages="{required:'请输入类型名称'}" class="input-large control-text">
	    		</div>
	  		</div>
		</div>
		<div class="row">
	  		<div class="control-group span25">
	    		<label class="control-label"><s>*</s>类型编码：</label>
	    		<div class="controls setTip">
	      			<input name="code" type="text" data-rules="{required:true,maxlength:30,regexp:/^[A-Za-z_]+$/}"
							data-messages="{required:'请输入类型编码',regexp:'类型编码不合法（组成：A-Za-z_）'}" class="input-large control-text">
					<a class="badge badge-warning" title="类型编码组成格式：A-Za-z_">?</a>
	    		</div>
	  		</div>
		</div>
		<div class="row">
	  		<div class="control-group span25">
	    		<label class="control-label">描述：</label>
	    		<div class="controls">
	    			<textarea name="remark" class="input-large control-text" data-rules="{maxlength:300}"></textarea>
	    		</div>
	  		</div>
		</div>
	</form>
</div>
<!-- 字典类型配置信息添加 end -->