/*告警列表*/
Ext.define("gjgl.gjlb", {
    extend: 'Ext.grid.Panel',
    id: 'gjlb',
    frame: true,
    initComponent: function () {
        var store = new Ext.data.Store({
            fields: ["warning_id","site_location","warning_type", "site_id", "warning_level", "warning_desc", "is_valid", "warning_state", "operate_time", "cz"],
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
            autoLoad: true
        });
        Ext.regModel("gjmodel", {
            fields: [{
                name: "gj"
            }, {
                name: "gjvalue"
            }]
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
        Ext.regModel("zdmodel", {
            fields: [{
                name: "zd"
            }, {
                name: "zdvalue"
            }]
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
                width: 200,
                fieldLabel: "乡镇",
                labelAlign: "right",


            },{
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
                icon: "img/15.png",
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
            },{
                header: '站点名称',
                sortable: true,
                dataIndex: 'site_id'
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
                text: '操作',
                xtype: "actioncolumn",

            }],
            store: store,
            bbar: new Ext.PagingToolbar({
                pageSize: 20,
                store: store,
                displayInfo: true,
                displayMsg: '当前显示第{0}条到第{1}条记录，总共有{2}条记录',
                emptyMsg: '无记录'
            })
        });
        this.callParent(arguments);
        store.load({
            params: {
                start: 0,
                limit: 20
            }
        });
    }
});