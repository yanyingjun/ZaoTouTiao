<!-- 渠道配置编辑 start -->
<div id="channelAllocationModifyContent" class="hidden">
    <form class="form-horizontal" id="channelAllocationModifyForm" action="../channel/update.json" class="form-horizontal" method="post">
        <input type="hidden" name="id" id="id" value=""/>
        <input type="hidden" name="terms" value=""/>
        <input type="hidden" name="timeFrame" value=""/>
        <input type="hidden" name="bankList" value=""/>
        <input type="hidden" name="isWithholding" value=""/>
        <input type="hidden" name="status" value=""/>
        <input type="hidden" name="cutInterest" value=""/>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label">渠道名称：</label>
                <div class="controls">
                    <input name="channelCode" type="hidden" id="channelCode" value=""/>
                    <input name="channelName" class="input-large control-text" type="text" readonly="readonly" value=""/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label">利率：</label>
                <div class="controls">
                    <input name="interestRate" class="input-large control-text" type="text" value="" placeholder="利率"
                           data-rules="{required:true,maxlength:6,regexp: /^\d+(\.\d)?\d{0,4}$/ }"
                           data-messages="{required:'请输入利率',regexp:'利率不合法'}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label">服务费率：</label>
                <div class="controls">
                    <input name="serviceRate" class="input-large control-text" type="text" value="" placeholder="服务费率"
                           data-rules="{required:true,maxlength:6,regexp: /^\d+(\.\d)?\d{0,4}$/ }"
                           data-messages="{required:'请输入服务费率',regexp:'服务费率不合法'}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label">优先级：</label>
                <div class="controls">
                    <input name="priority" class="input-large control-text" type="text" value="" placeholder="优先级"
                           data-rules="{required:true,maxlength:8,regexp: /^\d{0,8}$/ }"
                           data-messages="{required:'请输入优先级',regexp:'优先级不合法'}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label">标的期限：</label>
                <div class="controls1">
                    <label class="control-label2 checkbox">
                        <input name="termList" type="checkbox" value="7">7天
                    </label>
                    <label class="control-label2 checkbox">
                        <input name="termList" type="checkbox" value="14">14天
                    </label>
                    <label class="control-label2 checkbox">
                        <input name="termList" type="checkbox" value="21">21天
                    </label>
                    <label class="control-label2 checkbox">
                        <input name="termList" type="checkbox" value="30">30天
                    </label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label">代付时间：</label>
                <div class="controls1">
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="0">0
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="1">1
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="2">2
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="3">3
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="4">4
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="5">5
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="6">6
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="7">7
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="8">8
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="9">9
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="10">10
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="11">11
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="12">12
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="13">13
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="14">14
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="15">15
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="16">16
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="17">17
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="18">18
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="19">19
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="20">20
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="21">21
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="22">22
                    </label>
                    <label class="control-label-time checkbox">
                        <input name="timeFrameList" type="checkbox" value="23">23
                    </label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label">支持银行：</label>
                <div class="controls1">
                        <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="中国银行">中国银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="中国农业银行">中国农业银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="中国工商银行">中国工商银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="中国建设银行">中国建设银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="交通银行">交通银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="中信银行">中信银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="中国光大银行">中国光大银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="华夏银行">华夏银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="中国民生银行">中国民生银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="招商银行">招商银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="兴业银行">兴业银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="广发银行">广发银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="平安银行">平安银行
                    </label>
                    <label class="control-label1 checkbox" style="width: 150px;">
                        <input name="bankCheck" type="checkbox" value="上海浦东发展银行">上海浦东发展银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="恒丰银行">恒丰银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="渤海银行">渤海银行
                    </label>
                    <label class="control-label1 checkbox" style="width: 150px;">
                        <input name="bankCheck" type="checkbox" value="中国邮政储蓄银行">中国邮政储蓄银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="北京银行">北京银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="上海银行">上海银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="南京银行">南京银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="成都银行">成都银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="杭州银行">杭州银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="宁波银行">宁波银行
                    </label>
                    <label class="control-label1 checkbox">
                        <input name="bankCheck" type="checkbox" value="上海农商银行">上海农商银行
                    </label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label">是否支持代扣：</label>
                <div class="controls1">
                    <label class="control-label2 radio">
                        <input name="isWithholdingRadio" type="radio" value="1">是</label>
                    </label>
                    <label class="control-label2 radio">
                        <input name="isWithholdingRadio" type="radio" value="2">否</label>
                    </label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label">是否上线：</label>
                <div class="controls1">
                    <label class="control-label2 radio">
                        <input name="statusRadio" type="radio" value="1">是</label>
                    </label>
                    <label class="control-label2 radio">
                        <input name="statusRadio" type="radio" value="2">否</label>
                    </label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="control-group span25">
                <label class="control-label">是否支持砍头息：</label>
                <div class="controls1">
                    <label class="control-label2 radio">
                        <input name="cutInterestRadio" type="radio" value="1">是</label>
                    </label>
                    <label class="control-label2 radio">
                        <input name="cutInterestRadio" type="radio" value="2">否</label>
                    </label>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- 渠道配置编辑 end -->