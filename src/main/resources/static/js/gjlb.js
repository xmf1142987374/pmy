/*告警列表*/
Ext.define("gjgl.gjlb", {
    extend: 'Ext.grid.Panel',
    id: 'gjlb',
    frame: true,
    initComponent: function () {
        var ds = new Ext.data.Store({

            fields: ["warning_type", "site_id", "warning_level", "warning_desc","is_valid","warning_state","operate_time"],
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
        var gj = Ext.create(Ext.data.Store, {
            model: "gjmodel",
            data: [{
                gj: "水流量为零告警男",
            }, {
                gj: "水流量小于阈值告警",
            },{
                gj:"水流量小于下限告警",
            },{
                gj:"水流量大于上限告警",
            },{
                gj:"无水流量数据告警",
            },{
                gj:"动力设备24小时未开启告警",
            },{
                gj:"配电箱非法开启告警",
            },{
                gj:"考勤次数不达标告警",
            },{
                gj:"无效考勤告警",
            },{
                gj:"工单处理超时告警",
            }]
        });
        Ext.regModel("zdmodel", {
            fields: [{
                name: "zd"
            }, {
                name: "zdvalue"
            }]
        });
        var zd = Ext.create(Ext.data.Store, {
            model: "zdmodel",
            data: [{
                zd: "A站点",
            },{
                zd:'B站点',
            },{
                zd:"C站点",
            },{
                zd:"D站点",
            }]
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
            }, {
                xtype: "combo",
                labelAlign: "right",
                width: 270,
                fieldLabel: "告警分类",
                store: gj,
                queryMode: "local",
                triggerAction: "all",
                displayField: "gj",
                valueField: "gjvalue"
            }, {
                xtype: "combo",
                width: 200,
                fieldLabel: "乡镇",
                labelAlign: "right"
            }, {
                xtype: "combo",
                width: 180,
                fieldLabel: "村",
                labelAlign: "right"
            }, {
                xtype: "combo",
                labelAlign: "right",
                width: 180,
                fieldLabel: "站点",
                store: zd,
                queryMode: "local",
                triggerAction: "all",
                displayField: "zd",
                valueField: "zdvalue"
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
                header: '告警分类',
                sortable: true,
                dataIndex: 'warning_type'
            }, {
                header: '乡镇名',
                sortable: true,
                dataIndex: ''
            }, {
                header: '村名',
                sortable: true,
                dataIndex: ''
            }, {
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
                header: '操作',
                xtype: 'actioncolumn',
                items: [{
                    icon: '',
                    handler: function () {
                        alert("修改");
                    }
                }, {
                    icon: '',
                    handler: function () {
                        alert("删除");
                    }
                }]
            }],
            store: ds,
            bbar: new Ext.PagingToolbar({
                pageSize: 20,
                store: ds,
                displayInfo: true,
                displayMsg: '当前显示第{0}条到第{1}条记录，总共有{2}条记录',
                emptyMsg: '无记录'
            })
        });
        this.callParent(arguments);
        ds.load({
            params: {
                start: 0,
                limit: 20
            }
        });
    }
});