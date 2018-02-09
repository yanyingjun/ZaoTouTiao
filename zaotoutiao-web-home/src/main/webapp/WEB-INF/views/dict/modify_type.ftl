<!-- 字典类型配置信息编辑 start -->
<div id="dictTypeModifyContent" class="hidden">
	<form class="form-horizontal" id="dictTypeModifyForm" method="post">
		<div class="row">
	  		<div class="control-group span25">
	    		<label class="control-label"><s>*</s>类型名称：</label>
	    		<div class="controls">
					<input name="id" value="" type="hidden">
	      			<input name="name" type="text" data-rules="{required:true,maxlength:30}" 
							data-messages="{required:'请输入类型名称'}" class="input-large control-text">
	    		</div>
	  		</div>
		</div>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label"><s>*</s>类型编码：</label>
                <div class="controls setTip">
                    <input name="code" type="text" class="input-large control-text" readonly="true">
                </div>
            </div>
        </div>
		<div class="row">
	  		<div class="control-group span25">
	    		<label class="control-label"><s>*</s>状态：</label>
	    		<div class="controls">
                    <select name="status" class="input-large" data-rules="{required:true}" data-messages="{required:'请选择字典类型状态'}">
                        <option value="" selected>--请选择字典类型状态</option>
                        <option value="US" selected>使用</option>
                        <option value="AB" selected>作废</option>
                    </select>
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
<!-- 字典类型配置信息编辑 end -->