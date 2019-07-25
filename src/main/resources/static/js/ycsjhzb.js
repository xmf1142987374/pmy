//异常数据汇总表
Ext.define("gjgl.ycsjhzb", {
    extend: Ext.grid.Panel,
    id: "gjgl",
    frame: true,

    initComponent: function () {

        var store;
        var pages = 4; //每页显示的条数
        store = new Ext.data.Store({
            fields: ["warning_id", "site_location", "site_name", "warning_level", "warning_desc", "warning_state", "operate_time"],
            proxy: {
                type: "ajax",
                url: 'findAll',
                reader: {
                    type: "json",
                    totalProperty: "totalCount",
                    root: "data"
                }
            },
            pageSize: pages,
            autoLoad: false
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
        store.load({
            params: {
                start: 0,
                limit: pages
            }
        });

        //界面
        Ext.apply(this, {
            tbar: ["->", {
                xtype: "combo",
                fieldLabel: "告警分类",
                labelAlign: "right",
                store: gj,
                width: 265,
                queryMode: "local",
                triggerAction: "all",
                displayField: "warning_type",
            }, {
                xtype: "combo",
                fieldLabel: "乡镇",
                labelAlign: "right",
                store: site_areas,
                queryMode: "local",
                triggerAction: "all",
                displayField: "town_name",
            }, {
                xtype: "datefield",
                fieldLabel: "时间范围",
                labelAlign: "right",
            }, {
                xtype: "datefield",
            }, {
                text: "查询",
                icon: "img/4.png",
                handler: function () {
                    alert(11);
                }
            }, {
                text: "导出异常数据汇总表",
                icon: "img/51.png",
                handler: function () {
                    Ext.Ajax.request({
                        url: "addExcel",
                        success: function () {
                            store.load();
                            alert("成功");
                        },
                        failure: function () {
                            alert("失败");
                        }
                    })
                }
            }],

            columns: [
                {
                    header: "告警id",
                    hidden: true,
                    dataIndex: "warning_id"
                }, {
                    header: '乡镇名',
                    dataIndex: 'town_name',
                    align: "center",
                    flex: 3,
                    sortable: true
                }, {
                    header: '站点名称',
                    align: "center",
                    dataIndex: "site_name",
                    flex: 3,
                    sortable: true
                }, {
                    header: '告警级别',
                    align: "center",
                    dataIndex: "warning_level",
                    flex: 3,
                    sortable: true
                }, {
                    header: '告警内容',
                    align: "center",
                    dataIndex: "warning_desc",
                    flex: 3,
                    sortable: true
                }, {
                    header: '是否处理',
                    align: "center",
                    dataIndex: "warning_state",
                    flex: 3,
                    sortable: true
                }, {
                    header: "告警时间",
                    align: "center",
                    dataIndex: "operate_time",
                    flex: 3,
                    sortable: true
                }],
            store: store,
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