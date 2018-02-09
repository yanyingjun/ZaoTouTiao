<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>字典类型配置管理 - 信用管家</title>
    <#include "../common/style.ftl"/>
    <link href="../resources/css/dictMsg.css" rel="stylesheet">
</head>
<body>
<#include "../common/topBar.ftl"/>
<!-- 主体内容 start -->
<div class="main">
    <div class="selfTitle">
        <div>字典类型配置管理</div>
    </div>
    <!-- 搜索 start -->
    <div class="selfSearch">
        <form id="dictTypeSearchForm" class="form-horizontal" tabindex="0" style="outline: none;" method="post">
            <div class="row">
                <div class="control-group no-margin">
                    <div class="controls no-margin">
                        <input name="name" class="input-normal" type="text" value="" placeholder="请输入名称">
                        <input name="code" class="input-normal" type="text" value="" placeholder="请输入编码Code">
                        <button type="submit" class="button button-primary"><i class="icon-search icon-white"></i>查&nbsp;&nbsp;询</button>
                        <button type="button" class="button button-warning dictTypeAddButton" style="margin-right: 0px;"><i class="icon-plus icon-white"></i>字典类型添加
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!-- 搜索 end -->

    <!-- 数据列表 start -->
    <div id="dictTypeGrid"></div>
    <!-- 数据列表 end -->

    <!-- 数据分页 start -->
    <div id="dictTypeBar"></div>
    <!-- 数据分页 end -->
</div>
<!-- 主体内容 end -->

<#include "create_type.ftl"/>
<#include "modify_type.ftl"/>
<#include "../common/script.ftl"/>

<!-- script start -->
<script type="text/javascript">

    // 设置样式
    $(".topBar li.dictType").addClass("active");

    BUI.use(['bui/grid', 'bui/data', 'bui/toolbar', 'bui/overlay', 'bui/form', 'bui/select', 'bui/tooltip'], function (Grid, Data, Toolbar, Overlay, Form, Select, Tooltip) {

        var Grid = Grid,
                Store = Data.Store,
                dictTypeColumns = [{
                    title: '名称',
                    dataIndex: 'name',
                    sortable: false,
                    width: 240
                }, {
                    title: '编码',
                    dataIndex: 'code',
                    sortable: false,
                    width: 240
                }, {
                    title: '状态',
                    dataIndex: 'status',
                    sortable: false,
                    width: 50,
                    renderer: function (value, obj) {

                        if (value == 'US') {
                            return '<span class="badge badge-success">使用</span>';
                        }

                        return '<span class="badge badge-error">作废</span>';
                    }
                }, {
                    title: '修改者',
                    dataIndex: 'modifier',
                    sortable: false,
                    width: 130
                }, {
                    title: '修改时间',
                    dataIndex: 'gmtModify',
                    width: 130,
                    sortable: false,
                    renderer: BUI.Grid.Format.datetimeRenderer
                }, {
                    title: '操作',
                    width: 80,
                    dataIndex: '',
                    sortable: false,
                    renderer: function (value, obj) {
                        var dictTypeEditor = '<button class="button button-mini dictTypeModify button-warning"><i class="icon-edit icon-white i2 dictTypeModify"></i>编辑</button>';
                        return dictTypeEditor;
                    }
                }
                ];

        new Tooltip.Tips({
            tip: {
                trigger: '.setTip a',
                alignType: 'right', //默认方向
                elCls: 'tips  tips-notice',
                titleTpl: '<span class="x-icon x-icon-small x-icon-warning"><i class="icon icon-white icon-volume-up"></i></span><div class="tips-content">{title}</div>',
                offset: 10 //距离左边的距离
            }
        }).render();

        // 创建编辑Dialog
        var dictTypeModifyDialogEditiong = new Grid.Plugins.DialogEditing({
            contentId: 'dictTypeModifyContent',
            autoSave: true,
            triggerCls: 'dictTypeModify', //触发显示Dialog的样式
            editor: {
                title: "<i class='icon-editor i3'></i>字典类型信息编辑",
                width: 600,
                height: 400,
                mask: true,
                success: function () {

                    // 添加是否成功
                    var isSuccess = false;

                    var edtor = this,
                            form = edtor.get('form');

                    // 检验
                    form.valid();

                    // 校验是否通过
                    if (form.isValid()) {

                        form.ajaxSubmit({ //表单异步提交
                            async: false,
                            url: '../dict_type/update.json',
                            type: 'post',
                            success: function (data) {

                                // 校验成功
                                if (data.result.success == 'true') {

                                    // 弹出窗口提示
                                    BUI.Message.Alert(data.result.message, data.result.status);

                                    // 加载
                                    dictTypeStore.load();

                                    // 设置成功
                                    isSuccess = true;

                                } else {
                                    // 弹出窗口提示
                                    BUI.Message.Alert(data.result.message, data.result.status);
                                }

                            }
                        });
                    }

                    // 如果成功则关闭编辑窗口
                    if (isSuccess) {
                        dictTypeModifyDialogEditiong.cancel();
                    }
                }
            }
        });

        var dictTypeStore = new Store({
                    url: '../dict_type/queryPage.json',
                    autoLoad: true,
                    remoteSort: false,
                    root: 'dictTypeVOList',
                    totalProperty: 'totalCount',
                    pageSize: 15,
                    proxy: {
                        method: 'post'
                    },
                    params: {
                        name: "",
                        code: "",
                        start: 0,
                        pageIndex: 0
                    }
                }),

                // 创建类型容器
                dictTypeGrid = new Grid.Grid({
                    render: '#dictTypeGrid',
                    columns: dictTypeColumns,
                    store: dictTypeStore,
                    loadMask: true,
                    innerBorder: true,
                    stripeRows: false,
                    emptyDataTpl: '<#include "../common/empty.ftl"/>',
                    plugins: [Grid.Plugins.ColumnResize, dictTypeModifyDialogEditiong]
                }).render();

        var dictTypeBar = new Toolbar.NumberPagingBar({
            render: '#dictTypeBar',
            elCls: 'pagination pull-right',
            store: dictTypeStore,
            prevText: '上一页',
            nextText: '下一页'
        }).render();

        // 行列表点击操作
        dictTypeGrid.on('itemclick', function (ev) {

            var fieldSender = $(ev.domTarget),
                    fieldItem = ev.item;

            // 点击删除操作
            if (fieldSender.hasClass('dictTypeDelete')) {

                // 删除信息确认提示
                BUI.Message.Confirm('您确认要删除此字典类型配置信息记录吗？', function (ev) {

                    $.ajax({
                        type: "get",
                        url: "../dict_type/delete.json",
                        dataType: "JSON",
                        data: {
                            "id": fieldItem.id
                        },
                        success: function (data) {

                            // 处理结果
                            if (data.result.success == 'true') {

                                // 弹出信息提示窗口
                                BUI.Message.Alert(data.result.message, data.result.status);

                                //序列化成对象
                                dictTypeStore.load();
                            } else {

                                // 弹出信息提示窗口
                                BUI.Message.Alert(data.result.message, data.result.status);
                            }
                        }
                    });
                }, 'question');
            }
        });

        // 创建添加Form表单
        var dictTypeCreateForm = new Form.HForm({
            srcNode: '#dictTypeCreateForm',
            submitType: 'ajax',
            callback: function (data) {

                // 校验成功
                if (data.result.success == 'true') {

                    BUI.Message.Alert(data.result.message, data.result.status);

                    dictTypeCreateForm.clearFields();
                    dictTypeCreateForm.clearErrors(true, true);

                    // 加载
                    var obj = dictTypeSearchForm.serializeToObject();
                    obj.start = 0;
                    obj.pageIndex = 0;
                    dictTypeStore.load(obj);

                    // 取消窗口
                    dictTypeCreateDialog.close();

                } else {
                    // 弹出窗口提示
                    BUI.Message.Alert(data.result.message, data.result.status);
                }
            }
        }).render();

        // 创建添加Dialog
        var dictTypeCreateDialog = new Overlay.Dialog({
            title: "<i class='icon-add i3'></i>字典类型信息添加",
            contentId: 'dictTypeCreateContent',
            width: 600,
            height: 350,
            mask: true, // 显示mask
            buttons: [{
                text: "确定",
                elCls: 'button button-primary',
                handler: function () {

                    // 参数校验
                    dictTypeCreateForm.valid();

                    // 校验是否通过
                    if (dictTypeCreateForm.isValid()) {
                        dictTypeCreateForm.ajaxSubmit();
                    }
                }
            }, {
                text: "取消",
                elCls: 'button button-primary',
                handler: function () {
                    dictTypeCreateForm.clearFields();
                    dictTypeCreateForm.clearErrors(true, true);
                    this.hide();
                }
            }
            ]
        });

        // 绑定配置信息添加事件
        $('.dictTypeAddButton').on('click', function () {

            // 初始化表单
            dictTypeCreateForm.clearFields();
            dictTypeCreateForm.clearErrors(true, true);

            // 打开窗口
            dictTypeCreateDialog.show();
        });

        // 创建字典类型搜索Form
        var dictTypeSearchForm = new BUI.Form.HForm({
            srcNode: '#dictTypeSearchForm',
            method: 'post'
        }).render();

        // 搜索按钮提交
        dictTypeSearchForm.on('beforesubmit', function (ev) {

            // 序列化成对象
            var obj = this.serializeToObject();
            obj.start = 0; //返回第一页
            obj.pageIndex = 0;

            $(".assitData input[name='instanceCode']").val(obj.instanceCode);

            dictTypeStore.load(obj);

            return false;
        });

    });

</script>
<!-- script end -->
<#include "../common/footer.ftl"/>
</body>
</html>