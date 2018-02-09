<!-- 字典实体配置信息添加 start -->
<div id="dictEntityCreateContent" class="hidden">
	<form class="form-horizontal" id="dictEntityCreateForm" action="../dict_entity/insert.json" class="form-horizontal" method="post">
		<div class="row">
	  		<div class="control-group span25">
	    		<label class="control-label"><s>*</s>字典类型：</label>
	    		<div class="controls">
                    <select name="typeCode" class="input-large control-text" data-rules="{required:true}" data-messages="{required:'请选择字典类型'}">
                        <option value="" selected>--请选择字典类型</option>
						<#if dictTypeVOList?? && dictTypeVOList?size!=0>
							<#list dictTypeVOList as ddVO>
								<option value="${ddVO.code}">${ddVO.name}</option>
							</#list>
						</#if>
                    </select>
	    		</div>
	  		</div>
		</div>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label"><s>*</s>实体名称：</label>
                <div class="controls">
                    <input name="name" type="text" data-rules="{required:true}"
                           data-messages="{required:'请输入实体名称'}" class="input-large control-text" maxlength="30">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label"><s>*</s>实体内容：</label>
                <div class="controls setTip">
                    <input name="content" type="text" data-rules="{required:true,regexp:/^[A-Za-z0-9_]+$/}"
                           data-messages="{required:'请输入实体内容',regexp:'实体内容格式不合法，组成格式：大小写字母、数字、下划线'}" class="input-large control-text" maxlength="30">
                    <a class="badge badge-warning" title="实体内容组成格式：大小写字母、数字、下划线">?</a>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label"><s>*</s>实体排序：</label>
                <div class="controls">
                    <input name="sort" type="text" data-rules="{required:true,number:true}"
                           data-messages="{required:'请输入实体排序'}" class="input-large control-text" maxlength="3">
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
<!-- 字典实体配置信息添加 end -->