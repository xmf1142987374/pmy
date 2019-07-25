//巡维考勤界面


Ext.define("xw.gdgl", {
    extend: Ext.grid.Panel,
    id: "gdgl",
    frame: true,
    initComponent: function () {


//数据store
        var pages = 4;   // 设置你想要的每页显示条数
        var store = Ext.create('Ext.data.Store', {
            fields: ["order_id", "order_name", "site_id", "order_machine", "order_problem_from", "order_desc"],
            proxy: {
                type: "ajax",
                url: "all",
                reader: {
                    type: "json",
                    totalProperty: "totalCount",
                    root: "data"
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
//站点下拉数据
        var site_names = Ext.create('Ext.data.Store', {
            fields: ['site_name'],
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

        //界面
        Ext.apply(this, {
            tbar: [{
                text: '添加',
                icon: "img/16.png",
                handler: function () {
                    var win = new Ext.Window({
                        title: '添加工单',
                        width: 300,
                        height: 215,
                        frame: true
                    });
                    var form = new Ext.panel.Panel({
                        border: false,
                        frame: true,
                        layout: "form",
                        items: [{
                            xtype: "combo",
                            id: "gd",
                            fieldLabel: "工单名称",
                            store: gj,
                            queryMode: "local",
                            triggerAction: "all",
                            displayField: "warning_type",
                        }, {
                            xtype: "combo",
                            id:"zd",
                            fieldLabel: "站点",
                            store: site_names,
                            queryMode: "local",
                            triggerAction: "all",
                            displayField: "site_name",

                        }, {
                            xtype: "combo",
                            id: "jx",
                            fieldLabel: "检修设备"
                        }, {
                            xtype: "textarea",
                            id: "wt",
                            fieldLabel: "问题来源",

                        }],
                        buttons: [{
                            text: '添加',
                            handler: function () {
                                var order_name = Ext.getCmp("gd").value;
                                var site_id = Ext.getCmp("zd").value;
                                var order_machine = Ext.getCmp("jx").value;
                                var order_problem_from = Ext.getCmp("wt").value;
                                var order_desc = Ext.getCmp("jz").value;

                                Ext.Ajax.request({
                                    url: "addgdgl",
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
                                        order_name: order_name,
                                        site_id: site_id,
                                        order_machine: order_machine,
                                        order_problem_from: order_problem_from,
                                        order_desc: order_desc,
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
            }, {
                text: '删除',
                icon: "img/15.png",
                handler: function () {
                    var selectdata = Ext.getCmp("gdgl").getSelectionModel().getSelection();
                    var data = new Array();
                    for (var i = 0; i < selectdata.length; i++) {
                        data.push(selectdata[i].data.order_id);
                    }

                    Ext.Ajax.request({
                        url: "delegdgl",
                        type: "post",
                        success: function () {
                            store.reload();
                        },
                        params: {
                            data: data
                        }
                    });
                }
            }, "->", {
                xtype: "combo",
                fieldLabel: "站点",
                store: site_names,
                queryMode: "local",
                triggerAction: "all",
                displayField: "site_name",
                labelAlign: "right"
            },{
                xtype: "combo",
                fieldLabel: "状态",
                labelAlign: "right"
            }, {
                text: "查询",
                icon: "img/4.png",
                handler: function () {
                    alert(11);
                }

            }],
            border: true,
            forceFit: true,// 列表宽度自动适应
            selType: 'checkboxmodel',// 选择框
            multiSelect: true,// 设置为多选
            columns: [{
                xtype: 'rownumberer',
                align: 'center',
                renderer: function (value, cellmeta, record,rowIndex, columnIndex, store) {
                    return rowIndex + 1;
                }
            }, {
                header: "工单id",
                hidden: true,
                dataIndex: "order_id"
            }, {
                text: "工单名称",
                dataIndex: "order_name",
                align: "center",
                flex: 3,
                sortable: true
            }, {
                text: "站点",
                dataIndex: "site_id",
                align: "center",
                flex: 3,
                sortable: true
            }, {
                text: "设备",
                align: "center",
                dataIndex: "",
                flex: 3,
                sortable: true
            }, {
                text: "检修设备",
                align: "center",
                dataIndex: "order_machine",
                flex: 3,
                sortable: true
            }, {
                text: "问题来源",
                align: "center",
                dataIndex: "order_problem_from",
                flex: 3,
                sortable: true
            },{
                text: "状态",
                align: "center",
                dataIndex: "order_desc",
                flex: 3,
                sortable: true
            }, {
                text: "操作",
                align: "center",
                dataIndex: "",
                flex: 3,
                sortable: true
            }],
            bbar: new Ext.PagingToolbar({
                store: store,
                displayInfo: true,
                displayMsg: "当前显示第{0}到{1}条记录,共有{1}条记录",
                emptyMsg: "无记录"
            }),
            store: store
        });
        this.callParent(arguments);
    }
})