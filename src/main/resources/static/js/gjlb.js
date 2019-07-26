/*告警列表*/
Ext.define("gjgl.gjlb", {
    extend: 'Ext.grid.Panel',
    id: 'gjlb',
    frame: true,

    initComponent: function () {

        var store;
        var pages = 4; //每页显示的条数
        store = new Ext.data.Store({
            fields: ["warning_id", "site_id", "site_location", "warning_type", "site_name", "warning_level", "warning_desc", "is_valid", "warning_state", "operate_time", "cz"],
            proxy: {
                type: 'ajax',
                url: 'findAll',
                reader: {
                    type: 'json',// xml json
                    // Ext.data.reader.Json解析器
                    totalProperty: 'totalCount',
                    root: 'data'
                }
            },
            pageSize: pages,
            autoLoad: false
        });

        store.load({
            params: {
                start: 0,
                limit: pages
            }
        });

        //下拉告警列表数据
        var gj = Ext.create('Ext.data.Store', {
            fields: ["warning_type"],
            proxy: {
                type: "ajax",
                url: "findAll",
                reader: {
                    type: "json",
                    totalProperty: "totalCount",
                    root: "data"
                }
            },
            autoLoad: true
        });

        //站点下拉数据
        var site_names = Ext.create('Ext.data.Store', {
            fields: ['site_name'],
            proxy: {
                type: "ajax",
                url: "selSiteNames",
                reader: {
                    type: "json",
                    totalProperty: "totalCount",
                    root: "data"
                }
            },
            autoLoad: true
        });
        //乡镇站点区域数据
        var site_areas = Ext.create('Ext.data.Store', {
            fields: ["town_name"],
            proxy: {
                type: "ajax",
                url: "selSiteAreas",
                reader: {
                    type: "json",
                    totalProperty: "totalCount",
                    root: "data"
                }
            },
            autoLoad: true
        });

        var states =Ext.create(Ext.data.Store,{
            fields:['machine_name'],
            data:[
                {"machine_name":"风机"},
                {"machine_name":"水泵"}
            ]
        });

        Ext.apply(this, {
            tbar: [{
                text: '无效告警',
                icon: "img/50.png",
            }, {
                text: '告警处理',
                icon: "img/50.png",
            }, {
                text: '删除',
                icon: "img/15.png",
                handler: function () {
                    var selectdata = Ext.getCmp("gjlb").getSelectionModel().getSelection();
                    var data = new Array();
                    for (var i = 0; i < selectdata.length; i++) {
                        data.push(selectdata[i].data.warning_id);
                    }
                    Ext.Ajax.request({
                        url: "delegjlb",
                        type: "post",
                        success: function () {
                            store.reload();
                        },
                        params: {
                            data: data
                        }
                    });
                }
            }, {
                xtype: "combo",
                labelAlign: "right",
                width: 270,
                fieldLabel: "告警分类",
                store: gj,
                queryMode: "local",
                triggerAction: "all",
                displayField: "warning_type",

            }, {
                xtype: "combo",
                labelAlign: "right",
                fieldLabel: "乡镇",
                store: site_areas,
                queryMode: "local",
                triggerAction: "all",
                displayField: "town_name",

            }, {
                xtype: "combo",
                fieldLabel: "站点",
                store: site_names,
                queryMode: "local",
                triggerAction: "all",
                displayField: "site_name",
                labelAlign: "right"
            }, {
                xtype: "combo",
                width: 200,
                fieldLabel: "是否有效",
                labelAlign: "right"
            }, {
                text: '查询',
                icon: "img/4.png",
            }],
            border: true,
            forceFit: true,// 列表宽度自动适应
            selType: 'checkboxmodel',// 选择框
            multiSelect: true,// 设置为多选
            columns: [{
                xtype: 'rownumberer',
                align: 'center',
                renderer: function (value, cellmeta, record,
                                    rowIndex, columnIndex, store) {
                    return rowIndex + 1;
                }
            }, {
                header: "告警id",
                hidden: true,
                dataIndex: "warning_id"
            }, {
                header: '告警分类',
                sortable: true,
                dataIndex: 'warning_type'
            }, {
                header: '乡镇名',
                sortable: true,
                dataIndex: 'site_location'
            }, {
                header: '站点名称',
                sortable: true,
                dataIndex: 'site_name'
            }, {
                header: '告警级别',
                sortable: true,
                dataIndex: 'warning_level'
            }, {
                header: '告警内容',
                sortable: true,
                dataIndex: 'warning_desc'
            }, {
                header: '是否有效',
                sortable: true,
                dataIndex: 'is_valid'
            }, {
                header: '是否处理',
                sortable: true,
                dataIndex: 'warning_state'
            }, {
                header: '告警时间',
                sortable: true,
                dataIndex: 'operate_time'
            }, {
                header: '操作',
                xtype: "actioncolumn",
                align: "center",
                items: [{
                    icon: "img/50.png",
                    handler: function () {
                        var selectdata = Ext.getCmp("gjlb").getSelectionModel().getSelection();
                        console.log(selectdata)
                        var warning_type = selectdata[0].data.warning_type;
                        var site_name = selectdata[0].data.site_name;
                        var warning_desc = selectdata[0].data.warning_desc;

                        var win = new Ext.Window({
                            title: '添加工单',
                            width: 300,
                            height: 340,
                            frame: true
                        });
                        var form = new Ext.panel.Panel({
                            border: false,
                            frame: true,
                            layout: "form",
                            items: [{
                                xtype: "textfield",
                                id: "gdmc",
                                fieldLabel: "工单名称",
                                value: warning_type,
                            }, {
                                xtype: "textfield",
                                id: "zdmc",
                                fieldLabel: "站点名称",
                                value: site_name
                            }, {
                                xtype: "combo",
                                id: "sb",
                                fieldLabel: "设备",
                                store: states,
                                queryMode: "local",
                                displayField: "machine_name"
                            }, {
                                xtype: "textarea",
                                id: "wtms",
                                fieldLabel: "问题描述",
                                height: 150,
                                value: warning_desc,
                            }],
                            buttons: [{
                                text: '添加',
                                handler: function () {
                                    var warning_type=Ext.getCmp("gdmc").value;
                                    var site_name=Ext.getCmp("zdmc").value;
                                    var machine_name=Ext.getCmp("sb").value;
                                    var warning_desc=Ext.getCmp("wtms").value;
                                    Ext.Ajax.request({
                                        url: "addgd",
                                        type: "post",
                                        success: function () {
                                            alert("成功");
                                            store.load();
                                            win.close();
                                        },
                                        failure: function () {
                                            alert("失败");
                                        },
                                        params: {
                                            warning_type: warning_type,
                                            site_name: site_name,
                                            warning_desc: warning_desc,
                                            machine_name:machine_name,
                                        }
                                    })
                                }
                            }, {
                                text: "取消",
                                handler: function () {
                                    win.close();
                                }
                            }]
                        })
                        win.add(form);
                        win.show();
                    }
                }],

            }],
            store: store,
            bbar: new Ext.PagingToolbar({
                store: store,
                displayInfo: true,
                displayMsg: '当前显示第{0}条到第{1}条记录，总共有{1}条记录',
                emptyMsg: '无记录'
            }),
            store: store
        });
        this.callParent(arguments);

    }
});