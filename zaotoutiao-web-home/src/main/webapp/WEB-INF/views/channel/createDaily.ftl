<!-- 渠道配置添加 start -->
<div id="dailyLimitCreateContent" class="hidden">
    <form class="form-horizontal" id="dailyLimitCreateForm" action="../daily/insert.json" class="form-horizontal"   method="post">
        <input type="hidden" id="channelCodeView" name="channelCode" value=""/>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label">标记&nbsp;&nbsp;日期：</label>
                <#--<div class="controls">-->
                    <#--<input name="markDay" class="input-large control-text" type="text" value=""-->
                           <#--placeholder="请输入日期" data-rules="{required:true,maxlength:10,regexp: /^\d{4}-d{2}-d{2}$/}"-->
                           <#--data-messages="{required:'请输入日期',regexp:'日期格式不正确'}">-->
                <#--</div>-->
                <div class="controls">
                    <input name="markDay" class="input-large calendar" type="text"
                           placeholder="请选择日期" data-rules="{required : true}" data-messages="{required:'请选择日期'}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label">金额（万）：</label>
                <div class="controls">
                    <input name="availableAmount" class="input-middle control-text" type="text" value="" placeholder="可用金额"
                           data-rules="{required:true,maxlength:8,regexp: /^\d{0,8}$/ }"
                           data-messages="{required:'请输入可用金额',regexp:'可用金额格式不正确'}">
                </div>
            </div>
        </div>
    </form>
</div>
<!-- 渠道配置添加 end -->