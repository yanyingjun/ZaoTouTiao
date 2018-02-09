<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>渠道配置管理 - 信用管家</title>
<#include "../common/style.ftl"/>
    <link href="../resources/css/channelAllocation.css" rel="stylesheet">
    <style>
        .bui-stdmod-body {
            overflow-x: auto;
            overflow-y: auto;
        }
    </style>
</head>
<body>
<div class="banner-div"></div>
<!-- 主体内容 start -->
<div class="main">
    <div class="selfTitle">
        <div>渠道配置管理</div>
    </div>
    <div class="selfSearch">
        <form id="channelSearchForm" class="form-horizontal" tabindex="0" style="outline: none;" method="post">
            <div class="row">
                <div class="control-group no-margin">
                    <div class="controls no-margin">
                        <input name="channelName" class="input-normal" type="text" value="" placeholder="请输入渠道名称">
                        <button type="submit" class="button button-primary"><i class="icon-search icon-white"></i>查&nbsp;&nbsp;询
                        </button>
                        <button type="button" class="button button-warning channelAllocationDialogAddButton"
                                style="margin-right: 0px;"><i class="icon-plus icon-white"></i>添加
                        </button>
                        <a class="button button-danger channelAllocationBatchDelete"><i class="icon-remove icon-white"></i>批量删除</a>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!-- 数据列表 start -->
    <div id="channelAllocationGrid"></div>
    <!-- 数据列表 end -->

    <!-- 数据分页 start -->
    <div id="channelAllocationBar"></div>
    <!-- 数据分页 end -->

    <!-- 每日额度配置 start -->
    <div id="dailyLimitPopup" class="hide">
        <div class="selfSearch">
            <form id="dailySearchForm" class="form-horizontal" tabindex="0" style="outline: none;" method="post">
                <div class="row">
                    <div class="control-group no-margin">
                        <div class="controls no-margin">
                            <input name="markYear" class="input-normal" type="text" value="" placeholder="请输入年份">
                            <input name="markMonth" class="input-normal" type="text" value="" placeholder="请输入月份">
                            <button type="submit" class="button button-primary" style="margin-top: 15px;"><i class="icon-search icon-white"></i>查&nbsp;&nbsp;询
                            </button>
                            <button type="button" class="button button-warning dailyLimitDialogAddButton"
                                    style="margin-right: 0px;margin-top: 15px;"><i class="icon-plus icon-white"></i>添加
                            </button>
                            <a class="button button-danger dailyLimitBatchDelete" style="margin-top: 15px;"><i class="icon-remove icon-white"></i>批量删除</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div id="dailyLimitPopupGrid"></div>
    </div>
    <!-- 每日额度配置 end -->

</div>
<!-- 主体内容 end -->
<#include "./create.ftl"/>
<#include "./createDaily.ftl"/>
<#include "./modify.ftl"/>
<#include "../common/script.ftl"/>

<!-- script start -->
<script type="text/javascript">

    var id_selected = '';//选中当前ID

    // 设置样式
    $(".topBar li.channel").addClass("active");

    BUI.use(['bui/grid', 'bui/data', 'bui/toolbar', 'bui/overlay', 'bui/form', 'bui/tooltip', 'bui/select'], function (Grid, Data, Toolbar, Overlay, Form, Tooltip, Select) {
        var Grid = Grid,
                Store = Data.Store,
                channelAllocationColumns = [
                    {title: '渠道名称', dataIndex: 'channelName', width: 40, sortable: true},
                    {
                        title: '当前状态', dataIndex: 'status', width: 40, sortable: true,
                        renderer: function (value, obj, index) {
                            var result = "";
                            switch (value) {
                                case 1:
                                    result = "上线";
                                    break;
                                case 2:
                                    result = "下线";
                                    break;
                                default:
                                    result = "未知";
                            }
                            return result;
                        }
                    },
                    {title: '优先级', dataIndex: 'priority', width: 40, sortable: true},
                    {title: '利率', dataIndex: 'interestRate', width: 40, sortable: true, renderer: function (value, obj){
                        var rate = value*100;
                        return rate.toString().match(/^\d+(?:\.\d{0,4})?/) + "%";
                    }},
                    {title: '服务费率', dataIndex: 'serviceRate', width: 40, sortable: true, renderer: function (value, obj){
                        var rate = value*100;
                        return rate.toString().match(/^\d+(?:\.\d{0,4})?/) + "%";
                    }},
                    {title: '今日可用金额（单位/万）', dataIndex: 'availableAmount', width: 60, sortable: true, renderer: function (value, obj){
                        return value/10000;
                    }},
                    {title: '今日剩余金额（单位/万）', dataIndex: 'residualAmount', width: 60, sortable: true, renderer: function (value, obj){
                        return value/10000;
                    }},
                    {
                        title: '操作', width: 60, dataIndex: '', sortable: false, elCls: 'center',
                        renderer: function (value, obj) {
                            channelAllocationEditor = '<button class="button button-mini channelAllocationModify button-warning"><i class="icon-edit icon-white i2 channelAllocationModify"></i>编辑</button>';
                            dailyLimitList = '<button class="button button-mini dailyLimitList button-success"><i class="icon-edit icon-white i2 dailyLimitList"></i>每日额度</button>';
                            return channelAllocationEditor + dailyLimitList;
                        }
                    }];

        new Tooltip.Tips({
            tip: {
                trigger: '.setTip a',
                alignType: 'right', //默认方向
                elCls: 'tips  tips-notice',
                titleTpl: '<span class="x-icon x-icon-small x-icon-warning"><i class="icon icon-white icon-volume-up"></i></span><div class="tips-content">{title}</div>',
                offset: 10 //距离左边的距离
            }
        }).render();

        var items = [
            {text:'亿奇乐',value:'1'},
            {text:'考拉',value:'2'},
            {text:'微贷',value:'3'}
        ];
        var select = new Select.Select({
            render:'#channelNameSelectDiv',
            valueField:'#channelNameSelect',
            items:items
        });
        select.render();
        select.on('change', function(ev){
            $("#channelName").val(ev.item.text);
        });

        // 场景集合存储器
        var channelAllocationStore = new Store({
                    url: '../channel/queryPage.json',
                    autoLoad: true,
                    remoteSort: false,
                    root: 'channelAllocationVOList',
                    totalProperty: 'totalCount',
                    pageSize: 15,
                    proxy: {
                        method: 'post'
                    },
                    params: {
                        pageSize:15,
                        start: 0,
                        pageIndex: 0
                    }
                }),
                // 创建类型容器
                channelAllocationGrid = new Grid.Grid({
                    render: '#channelAllocationGrid',
                    columns: channelAllocationColumns,
                    store: channelAllocationStore,
                    loadMask: true,
                    innerBorder: true,
                    stripeRows: false,
                    emptyDataTpl: '<#include "../common/empty.ftl"/>',
                    plugins: [Grid.Plugins.ColumnResize, Grid.Plugins.CheckSelection]
                }).render();

        // 分页工具
        var channelAllocationBar = new Toolbar.NumberPagingBar({
            render: '#channelAllocationBar',
            elCls: 'pagination pull-right',
            store: channelAllocationStore,
            prevText: '上一页',
            nextText: '下一页'
        }).render();

        // 创建键值搜索Form
        var channelSearchForm = new BUI.Form.HForm({
            srcNode: '#channelSearchForm',
            method: 'post'
        }).render();

        // 搜索按钮提交
        channelSearchForm.on('beforesubmit', function (ev) {

            // 序列化成对象
            var obj = this.serializeToObject();
            obj.start = 0; //返回第一页
            obj.pageIndex = 0;

            channelAllocationStore.load(obj);

            return false;
        });

        // 创建实例添加Form表单
        var channelAllocationCreateForm = new Form.HForm({
            srcNode: '#channelAllocationCreateForm',
            submitType: 'ajax',
            callback: function (data) {
                // 校验成功
                if (data.result.success == 'true') {
                    BUI.Message.Alert(data.result.message, data.result.status);

                    channelAllocationCreateForm.clearErrors(true, true);
                    channelAllocationCreateForm.clearFields();
                    // 重载
                    channelAllocationStore.load();
                    // 取消窗口
                    channelAllocationCreateDialog.close();
                } else {
                    // 弹出窗口提示
                    BUI.Message.Alert(data.result.message, data.result.status);
                }
            }
        }).render();

        // 添加Dialog
        var channelAllocationCreateDialog = new Overlay.Dialog({
            title: "<i class='icon-add i3'></i>渠道配置新增",
            contentId: 'channelAllocationCreateContent',
            width: 700,
            height: 600,
            mask: true, // 显示mask
            buttons: [{
                text: "确定",
                elCls: 'button button-primary',
                handler: function () {
                    // 参数校验
                    channelAllocationCreateForm.valid();
                    // 校验是否通过
                    if (channelAllocationCreateForm.isValid()) {
                        /***********复选框**********/
                                //标的期限
                        var terms = channelAllocationCreateForm.getFieldValue("termList1");
                        if(terms == null){
                            BUI.Message.Alert("标的期限不能为空");
                            return false;
                        }
                        channelAllocationCreateForm.setFieldValue("terms", terms);
                        //代付时间
                        var timeFrame = channelAllocationCreateForm.getFieldValue("timeFrameList1");
                        if(timeFrame == null){
                            BUI.Message.Alert("代付时间不能为空");
                            return false;
                        }
                        channelAllocationCreateForm.setFieldValue("timeFrame", timeFrame);
                        //支持银行
                        var bankList = channelAllocationCreateForm.getFieldValue("banksList");
                        if(bankList == null){
                            BUI.Message.Alert("支持银行不能为空");
                            return false;
                        }
                        channelAllocationCreateForm.setFieldValue("bankList", bankList);
                        //是否支持代扣
                        var isWithholding = channelAllocationCreateForm.getFieldValue("isWithholding");
                        if(isWithholding == null){
                            BUI.Message.Alert("是否支持代扣不能为空");
                            return false;
                        }
                        //当前状态
                        var state = channelAllocationCreateForm.getFieldValue("status");
                        if(state == null){
                            BUI.Message.Alert("当前状态是否上线不能为空");
                            return false;
                        }
                        //当前状态
                        var cutInterest = channelAllocationCreateForm.getFieldValue("cutInterest");
                        if(cutInterest == null){
                            BUI.Message.Alert("是否支持砍头息不能为空");
                            return false;
                        }
                        channelAllocationCreateForm.setFieldValue("termList1", '');
                        channelAllocationCreateForm.setFieldValue("timeFrameList1", '');
                        channelAllocationCreateForm.setFieldValue("banksList", '');

                        channelAllocationCreateForm.ajaxSubmit();
                    }
                }
            }, {
                text: "取消",
                elCls: 'button button-primary',
                handler: function () {
                    channelAllocationCreateForm.clearFields();
                    channelAllocationCreateForm.clearErrors(true, true);
                    this.hide();
                }
            }
            ]
        });

        // 创建实例添加Form表单
        var channelAllocationModifyForm = new Form.HForm({
            srcNode: '#channelAllocationModifyForm',
            submitType: 'ajax',
            callback: function (data) {
                // 校验成功
                if (data.result.success == 'true') {
                    BUI.Message.Alert(data.result.message, data.result.status);

                    channelAllocationModifyForm.clearErrors(true, true);
                    channelAllocationModifyForm.clearFields();
                    // 重载
                    channelAllocationStore.load();
                    // 取消窗口
                    channelAllocationModifyDialog.close();
                } else {
                    // 弹出窗口提示
                    BUI.Message.Alert(data.result.message, data.result.status);
                }
            }
        }).render();

        var channelAllocationModifyDialog = new Overlay.Dialog({
            title: "<i class='icon-add i3'></i>渠道配置编辑",
            contentId: 'channelAllocationModifyContent',
            width: 700,
            height: 600,
            mask: true,
            buttons: [{
                text: "确定",
                elCls: 'button button-primary',
                handler: function () {
                    // 参数校验
                    channelAllocationModifyForm.valid();
                    // 校验是否通过
                    if (channelAllocationModifyForm.isValid()) {
                        var terms = channelAllocationModifyForm.getFieldValue("termList");
                        if (terms == null) {
                            BUI.Message.Alert("标的期限不能为空");
                            return false;
                        }
                        channelAllocationModifyForm.setFieldValue("terms", terms);
                        //代付时间
                        var timeFrame = channelAllocationModifyForm.getFieldValue("timeFrameList");
                        if (timeFrame == null) {
                            BUI.Message.Alert("代付时间不能为空");
                            return false;
                        }
                        channelAllocationModifyForm.setFieldValue("timeFrame", timeFrame);
                        //支持银行
                        var bankList = channelAllocationModifyForm.getFieldValue("bankCheck");
                        if (bankList == null) {
                            BUI.Message.Alert("支持银行不能为空");
                            return false;
                        }
                        channelAllocationModifyForm.setFieldValue("bankList", bankList);
                        //是否支持代扣
                        var isWithholding = channelAllocationModifyForm.getFieldValue("isWithholdingRadio");
                        if (isWithholding == null) {
                            BUI.Message.Alert("是否支持代扣不能为空");
                            return false;
                        }
                        channelAllocationModifyForm.setFieldValue("isWithholding", isWithholding);
                        //当前状态
                        var status = channelAllocationModifyForm.getFieldValue("statusRadio");
                        if (status == null) {
                            BUI.Message.Alert("当前状态是否上线不能为空");
                            return false;
                        }
                        channelAllocationModifyForm.setFieldValue("status", status);
                        //是否支持砍头息
                        var cutInterest = channelAllocationModifyForm.getFieldValue("cutInterestRadio");
                        if (cutInterest == null) {
                            BUI.Message.Alert("是否支持砍头息不能为空");
                            return false;
                        }
                        channelAllocationModifyForm.setFieldValue("cutInterest", cutInterest);

                        channelAllocationModifyForm.setFieldValue("termList", '');
                        channelAllocationModifyForm.setFieldValue("timeFrameList", '');
                        channelAllocationModifyForm.setFieldValue("bankCheck", '');
                        channelAllocationModifyForm.setFieldValue("isWithholdingRadio", '');
                        channelAllocationModifyForm.setFieldValue("statusRadio", '');
                        channelAllocationModifyForm.setFieldValue("cutInterestRadio", '');
                        channelAllocationModifyForm.ajaxSubmit();
                        channelAllocationModifyForm.clearErrors(true, true);
                        channelAllocationModifyForm.clearFields();
                    }
                }
            }, {
                text: "取消",
                elCls: 'button button-primary',
                handler: function () {
                    channelAllocationModifyForm.setFieldValue("termList", '');
                    channelAllocationModifyForm.setFieldValue("timeFrameList", '');
                    channelAllocationModifyForm.setFieldValue("bankCheck", '');
                    channelAllocationModifyForm.setFieldValue("isWithholdingRadio", '');
                    channelAllocationModifyForm.setFieldValue("statusRadio", '');
                    channelAllocationModifyForm.setFieldValue("cutInterestRadio", '');
                    window.location.reload();
                    channelAllocationModifyForm.clearFields();
                    channelAllocationModifyForm.clearErrors(true, true);
                    this.hide();
                }
            }
            ]
        });


        // 绑定添加渠道配置事件
        $('.channelAllocationDialogAddButton').on('click', function () {
            // 初始化表单
            channelAllocationCreateForm.clearFields();
            channelAllocationCreateForm.clearErrors(true, true);
            // 打开窗口
            channelAllocationCreateDialog.show();
        });

        /****************************************批量删除渠道*********************************************/
        // 指定相应的事件
        $(".channelAllocationBatchDelete").on('click', function (ev) {
            // 定义ID拼接字符串
            var idStr = dispIdStr(channelAllocationGrid);
            if (idStr != '') {
                // 删除信息确认提示
                BUI.Message.Confirm('您确认要删除吗？删除后将不可恢复。', function (ev) {
                    $.ajax({
                        type: "post",
                        url: "../channel/deleteBatch.json",
                        dataType: "JSON",
                        data: {
                            "idStr": idStr
                        },
                        success: function (data) {
                            // 处理结果
                            if (data.result.success == 'true') {
                                // 弹出信息提示窗口
                                BUI.Message.Alert(data.result.message, data.result.status);
                                // 重载
                                channelAllocationStore.load();
                            } else {
                                // 弹出信息提示窗口
                                BUI.Message.Alert(data.result.message, data.result.status);
                            }
                        }
                    });
                }, 'question');
            } else {
                // 弹出窗口提示
                BUI.Message.Alert("请选择要删除的渠道配置", "warning");
            }
        });

        /**
         * 获取所选
         *
         * @returns {string}
         */
        function dispIdStr(ev) {
            // 定义ID拼接字符串
            var idStr = "";
            // 获取选择的
            var selectArray = ev.getSelection();
            // 校验选择的集合是否为空
            if (selectArray != null && selectArray != undefined && selectArray.length > 0) {
                // 循环处理
                for (var i = 0; i < selectArray.length; i++) {
                    // 获取选择的某条
                    var select = selectArray[i];
                    // 拼接
                    idStr = idStr + select.id + ',';
                }
            }
            return idStr;
        }

        /****************************************配置每日额度列表*********************************************/
        channelAllocationGrid.on('itemclick', function (ev) {
            var fieldSender = $(ev.domTarget),
                    fieldItem = ev.item;
            id_selected = fieldItem.channelCode;
            if (fieldSender.hasClass('dailyLimitList')) {
                dailyLimitStore.load({channelCode: id_selected});
                dailyLimitDialog.show();
            } else if (fieldSender.hasClass('channelAllocationModify')){
                // 初始化表单
                channelAllocationCreateForm.clearFields();
                channelAllocationCreateForm.clearErrors(true, true);
                channelAllocationModifyDialog.show();
                loadData(id_selected);
            }
        });

        var dailyLimitColumns = [
            {title: '日期', dataIndex: 'markDay', width: 40, sortable: true},
            {title: '金额（单位/万）', dataIndex: 'availableAmount', width: 40, sortable: true, renderer: function (value, obj){
                return value.amount/10000;
            }}
        ];

        var dailyLimitStore = new Store({
            url: '../daily/queryPage.json',
            autoLoad: true,
            remoteSort: false,
            root: 'dailyLimitVOList',
            totalProperty: 'totalCount',
            pageSize: 31,
            proxy: {
                method: 'post'
            },
            params: {
                pageSize: 31,
                pageIndex: 0
            }
        });

        var dailyLimitGrid = new Grid.Grid({
            render: '#dailyLimitPopupGrid',
            columns: dailyLimitColumns,
            store: dailyLimitStore,
            innerBorder: true,
            stripeRows: false,
            loadMask: true,
            emptyDataTpl: '<#include "../common/empty.ftl"/>',
            plugins: [Grid.Plugins.ColumnResize, Grid.Plugins.CheckSelection]
        }).render();

        var dailyLimitDialog = new Overlay.Dialog({
            title: '每日额度',
            width: 500,
            height: 510,
            mask: true,
            contentId: 'dailyLimitPopup',
            buttons: [{
                text: "关闭",
                elCls: 'button button-primary',
                handler: function () {
                    this.close();
                }
            }]
        });

        // 创建键值搜索Form
        var dailySearchForm = new BUI.Form.HForm({
            srcNode: '#dailySearchForm',
            method: 'post'
        }).render();

        // 搜索按钮提交
        dailySearchForm.on('beforesubmit', function (ev) {

            // 序列化成对象
            var obj = this.serializeToObject();
            obj.start = 0; //返回第一页
            obj.pageIndex = 0;

            dailyLimitStore.load(obj);

            return false;
        });

        /****************************************添加配置每日额度*********************************************/
        // 绑定添加每日额度配置事件
        $('.dailyLimitDialogAddButton').on('click', function () {
            // 初始化表单
            dailyLimitCreateForm.clearFields();
            dailyLimitCreateForm.clearErrors(true, true);

            // 打开窗口
            dailyLimitCreateDialog.show();
            $("#channelCodeView").val(id_selected);
        });

        // 创建实例添加Form表单
        var dailyLimitCreateForm = new Form.HForm({
            srcNode: '#dailyLimitCreateForm',
            submitType: 'ajax',
            callback: function (data) {
                // 校验成功
                if (data.result.success == 'true') {
                    BUI.Message.Alert(data.result.message, data.result.status);

                    dailyLimitCreateForm.clearErrors(true, true);
                    dailyLimitCreateForm.clearFields();
                    // 重载
                    dailyLimitStore.load();
                    // 取消窗口
                    dailyLimitCreateDialog.close();
                } else {
                    // 弹出窗口提示
                    BUI.Message.Alert(data.result.message, data.result.status);
                }
            }
        }).render();

        // 添加Dialog
        var dailyLimitCreateDialog = new Overlay.Dialog({
            title: "<i class='icon-add i3'></i>每日额度配置新增",
            contentId: 'dailyLimitCreateContent',
            width: 500,
            height: 510,
            mask: true, // 显示mask
            buttons: [{
                text: "确定",
                elCls: 'button button-primary',
                handler: function () {
                    // 参数校验
                    dailyLimitCreateForm.valid();
                    // 校验是否通过
                    if (dailyLimitCreateForm.isValid()) {
                        dailyLimitCreateForm.ajaxSubmit();
                    }
                }
            }, {
                text: "取消",
                elCls: 'button button-primary',
                handler: function () {
                    dailyLimitCreateForm.clearFields();
                    dailyLimitCreateForm.clearErrors(true, true);
                    this.hide();
                }
            }
            ]
        });

        /****************************************批量删除渠道*********************************************/
        // 指定相应的事件
        $(".dailyLimitBatchDelete").on('click', function (ev) {
            // 定义ID拼接字符串
            var idStr = dispIdStr(dailyLimitGrid);
            if (idStr != '') {
                // 删除信息确认提示
                BUI.Message.Confirm('您确认要删除吗？删除后将不可恢复。', function (ev) {
                    $.ajax({
                        type: "post",
                        url: "../daily/deleteBatch.json",
                        dataType: "JSON",
                        data: {
                            "idStr": idStr
                        },
                        success: function (data) {
                            // 处理结果
                            if (data.result.success == 'true') {
                                // 弹出信息提示窗口
                                BUI.Message.Alert(data.result.message, data.result.status);
                                // 重载
                                dailyLimitStore.load();
                            } else {
                                // 弹出信息提示窗口
                                BUI.Message.Alert(data.result.message, data.result.status);
                            }
                        }
                    });
                }, 'question');
            } else {
                // 弹出窗口提示
                BUI.Message.Alert("请选择要删除的每日额度", "warning");
            }
        });

    });

    function loadData(channelCode) {
        $.ajax({
            type:"GET",
            url:"../channel/getBuyId.json",
            data:{"channelCode":channelCode},
            datatype: "json",
            success:function(data){
                var vo = data.channelAllocationVO;
                $("input[name='id']").val(vo.id);
                $("input[name='channelName']").val(vo.channelName);
                $("input[name='channelCode']").val(vo.channelCode);
                $("input[name='interestRate']").val(vo.interestRate);
                $("input[name='serviceRate']").val(vo.serviceRate);
                $("input[name='priority']").val(vo.priority);
                var termArray = vo.terms.split(",");
                for(var i = 0; i < termArray.length; i++) {
                    $("input[name=termList][value="+ termArray[i] +"]").attr("checked", 'checked');
                }

                var timeArray = vo.timeFrame.split(",");
                for(var i = 0; i < timeArray.length; i++) {
                    $("input[name=timeFrameList][value="+ timeArray[i] +"]").attr("checked", 'checked');
                }

                var bankArray = vo.bankList.split(",");
                for(var i = 0; i < bankArray.length; i++) {
                    $("input[name=bankCheck][value="+ bankArray[i] +"]").attr("checked", 'checked');
                }

                $("input[type='radio'][name='isWithholdingRadio'][value="+ vo.isWithholding +"]").attr("checked", "checked");
                $("input[type='radio'][name='statusRadio'][value="+ vo.status +"]").attr("checked", "checked");
                $("input[type='radio'][name='cutInterestRadio'][value="+ vo.cutInterest +"]").attr("checked", "checked");
            }
        });
    }
</script>
<!-- script end -->
<#include "../common/footer.ftl"/>
</body>
</html>
