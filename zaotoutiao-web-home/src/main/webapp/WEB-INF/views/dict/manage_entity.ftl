<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>字典实体配置管理 - 信用管家</title>
<#include "../common/style.ftl"/>
    <link href="../resources/css/dictMsg.css" rel="stylesheet">
</head>
<body>
<#include "../common/topBar.ftl"/>
<!-- 主体内容 start -->
<div class="main">
    <div class="selfTitle">
        <div>字典实体配置管理</div>
    </div>
    <!-- 搜索 start -->
    <div class="selfSearch">
        <form id="dictEntitySearchForm" class="form-horizontal" tabindex="0" style="outline: none;" method="post">
            <div class="row">
                <div class="control-group no-margin">
                    <div class="controls no-margin">
                        <select name="typeCode" class="input-normal"  data-rules="{required:true}" data-messages="{required:'请选择字典类型'}">
                            <option value="" selected>--请选择字典类型</option>
                                <#if dictTypeVOList?? && dictTypeVOList?size!=0>
                                    <#list dictTypeVOList as ddVO>
                                        <#if ddVO.code == dictEntityQuery.typeCode?default('')>
                                            <option value="${ddVO.code}" selected>${ddVO.name}</option>
                                        <#else>
                                            <option value="${ddVO.code}">${ddVO.name}</option>
                                        </#if>
                                    </#list>
                                </#if>
                        </select>
                        <input name="name" class="input-normal" type="text" value="" placeholder="请输入名称">
                        <button type="submit" class="button button-primary"><i class="icon-search icon-white"></i>查&nbsp;&nbsp;询</button>
                        <button type="button" class="button button-warning dictEntityAddButton" style="margin-right: 0px;"><i class="icon-plus icon-white"></i>字典实体添加
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!-- 搜索 end -->

    <!-- 数据列表 start -->
    <div id="dictEntityGrid"></div>
    <!-- 数据列表 end -->

    <!-- 数据分页 start -->
    <div id="dictEntityBar"></div>
    <!-- 数据分页 end -->
</div>
<!-- 主体内容 end -->

<#include "create_entity.ftl"/>
<#include "modify_entity.ftl"/>
<#include "../common/script.ftl"/>

<!-- script start -->
<script type="text/javascript">

    // 设置样式
    $(".topBar li.dictEntity").addClass("active");

    BUI.use(['bui/grid', 'bui/data', 'bui/toolbar', 'bui/overlay', 'bui/form', 'bui/select', 'bui/tooltip'], function (Grid, Data, Toolbar, Overlay, Form, Select, Tooltip) {

        var dictTypeVOList = {
            <#if dictTypeVOList?? && dictTypeVOList?size!=0>
                <#list dictTypeVOList as ddVO>
                    '${ddVO.code}':'${ddVO.name}',
                </#list>
            </#if>
                '无':''
            };

        var Grid = Grid,
                Store = Data.Store,
                dictEntityColumns = [{
                    title: '所属类型',
                    dataIndex: 'typeCode',
                    sortable: false,
                    width: 100,
                    renderer : BUI.Grid.Format.enumRenderer(dictTypeVOList)
                }, {
                    title: '实体名称',
                    dataIndex: 'name',
                    sortable: false,
                    width: 100
                }, {
                    title: '实体内容',
                    dataIndex: 'content',
                    sortable: false,
                    width: 150
                }, {
                    title: '排序',
                    dataIndex: 'sort',
                    sortable: false,
                    width: 50
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
                    width: 90
                }, {
                    title: '修改时间',
                    dataIndex: 'gmtModify',
                    width: 90,
                    sortable: false,
                    renderer: BUI.Grid.Format.datetimeRenderer
                }, {
                    title: '操作',
                    width: 80,
                    dataIndex: '',
                    sortable: false,
                    renderer: function (value, obj) {
                        var dictEntityEditor = '<button class="button button-mini dictEntityModify button-warning"><i class="icon-edit icon-white i2 dictEntityModify"></i>编辑</button>';
                        var dictEntityDelete = '<button class="button button-mini dictEntityDelete button-danger"><i class="icon-remove icon-white i2 dictEntityDelete"></i>删除</button>';
                        return dictEntityEditor + dictEntityDelete;
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

        // 创建编辑Dialog
        var dictEntityModifyDialogEditiong = new Grid.Plugins.DialogEditing({
            contentId: 'dictEntityModifyContent',
            autoSave: true,
            triggerCls: 'dictEntityModify', //触发显示Dialog的样式
            editor: {
                title: "<i class='icon-editor i3'></i>字典实体信息编辑",
                width: 600,
                height: 500,
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
                            url: '../dict_entity/update.json',
                            type: 'post',
                            success: function (data) {

                                // 校验成功
                                if (data.result.success == 'true') {

                                    // 弹出窗口提示
                                    BUI.Message.Alert(data.result.message, data.result.status);

                                    // 加载
                                    dictEntityStore.load();

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
                        dictEntityModifyDialogEditiong.cancel();
                    }
                }
            }
        });

        var dictEntityStore = new Store({
                    url: '../dict_entity/queryPage.json',
                    autoLoad: true,
                    remoteSort: false,
                    root: 'dictEntityVOList',
                    totalProperty: 'totalCount',
                    pageSize: 15,
                    proxy: {
                        method: 'post'
                    },
                    params: {
                        name: "",
                        typeCode: "${dictEntityQuery.typeCode?default('')}",
                        start: 0,
                        pageIndex: 0
                    }
                }),

                // 创建类型容器
                dictEntityGrid = new Grid.Grid({
                    render: '#dictEntityGrid',
                    columns: dictEntityColumns,
                    store: dictEntityStore,
                    loadMask: true,
                    innerBorder: true,
                    stripeRows: false,
                    emptyDataTpl: '<#include "../common/empty.ftl"/>',
                    plugins: [Grid.Plugins.ColumnResize, dictEntityModifyDialogEditiong]
                }).render();

        var dictEntityBar = new Toolbar.NumberPagingBar({
            render: '#dictEntityBar',
            elCls: 'pagination pull-right',
            store: dictEntityStore,
            prevText: '上一页',
            nextText: '下一页'
        }).render();

        // 行列表点击操作
        dictEntityGrid.on('itemclick', function (ev) {

            var fieldSender = $(ev.domTarget),
                    fieldItem = ev.item;

            // 点击删除操作
            if (fieldSender.hasClass('dictEntityDelete')) {

                // 删除信息确认提示
                BUI.Message.Confirm('您确认要删除此字典实体配置信息记录吗？', function (ev) {

                    $.ajax({
                        type: "post",
                        url: "../dict_entity/delete.json",
                        dataType: "JSON",
                        data: {
                            "id": fieldItem.id,
							"typeCode": fieldItem.typeCode
                        },
                        success: function (data) {

                            // 处理结果
                            if (data.result.success == 'true') {

                                // 弹出信息提示窗口
                                BUI.Message.Alert(data.result.message, data.result.status);

                                //序列化成对象
                                dictEntityStore.load();
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
        var dictEntityCreateForm = new Form.HForm({
            srcNode: '#dictEntityCreateForm',
            submitType: 'ajax',
            callback: function (data) {

                // 校验成功
                if (data.result.success == 'true') {

                    BUI.Message.Alert(data.result.message, data.result.status);

                    dictEntityCreateForm.clearFields();
                    dictEntityCreateForm.clearErrors(true, true);

                    // 加载
                    var obj = dictEntitySearchForm.serializeToObject();
                    obj.start = 0;
                    obj.pageIndex = 0;
                    dictEntityStore.load(obj);

                    // 取消窗口
                    dictEntityCreateDialog.close();

                } else {
                    // 弹出窗口提示
                    BUI.Message.Alert(data.result.message, data.result.status);
                }
            }
        }).render();

        // 创建添加Dialog
        var dictEntityCreateDialog = new Overlay.Dialog({
            title: "<i class='icon-add i3'></i>字典实体信息添加",
            contentId: 'dictEntityCreateContent',
            width: 600,
            height: 450,
            mask: true, // 显示mask
            buttons: [{
                text: "确定",
                elCls: 'button button-primary',
                handler: function () {

                    // 参数校验
                    dictEntityCreateForm.valid();

                    // 校验是否通过
                    if (dictEntityCreateForm.isValid()) {
                        dictEntityCreateForm.ajaxSubmit();
                    }
                }
            }, {
                text: "取消",
                elCls: 'button button-primary',
                handler: function () {
                    dictEntityCreateForm.clearFields();
                    dictEntityCreateForm.clearErrors(true, true);
                    this.hide();
                }
            }
            ]
        });

        // 绑定配置信息添加事件
        $('.dictEntityAddButton').on('click', function () {

            // 初始化表单
            dictEntityCreateForm.clearFields();
            dictEntityCreateForm.clearErrors(true, true);

            var typeCode = dictEntitySearchForm.getFieldValue("typeCode");

            if (typeCode != null && typeCode != undefined && typeCode != '') {
                dictEntityCreateForm.setFieldValue("typeCode", typeCode);
            }

            // 打开窗口
            dictEntityCreateDialog.show();
        });

        // 创建字典实体搜索Form
        var dictEntitySearchForm = new BUI.Form.HForm({
            srcNode: '#dictEntitySearchForm',
            method: 'post'
        }).render();

        // 搜索按钮提交
        dictEntitySearchForm.on('beforesubmit', function (ev) {

            // 序列化成对象
            var obj = this.serializeToObject();
            obj.start = 0; //返回第一页
            obj.pageIndex = 0;

            $(".assitData input[name='instanceCode']").val(obj.instanceCode);

            dictEntityStore.load(obj);

            return false;
        });

    });

</script>
<!-- script end -->
<#include "../common/footer.ftl"/>
</body>
</html>