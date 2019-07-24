//巡检工作界面


Ext.define("xw.xjgz", {
    extend: Ext.grid.Panel,
    id: "xjgz",
    frame: true,
    initComponent: function () {

//下拉框模型
//         var zq = Ext.create('Ext.data.Store', {
//             fields: ['zq', 'zqvalue'],
//             data : [
//                 {"zq":"每周", "zqvalue":"每周"},
//                 {"zq":"半个月", "zqvalue":"半个月"},
//                 {"zq":"每月", "zqvalue":"每月"}
//                 //...
//             ]
//         });

//数据store
        var pages = 4;   // 设置你想要的每页显示条数
        var store = Ext.create('Ext.data.Store', {
            id: "xjgz",
            fields: ["sets_id", "site_type", "valid_time", "log_cycle", "log_count", "valid_start_time", "valid_end_time"],
            proxy: {
                type: "ajax",
                //url:"selkqsets",
                reader: {
                    type: "json",
                    totalProperty: "totalCount",
                    root: "data"
                }
            },
            pageSize: pages,
            autoLoad: false
        });

        // store.load({
        //     params: {
        //         start: 0,
        //         limit: pages
        //     }
        // });
//站点下拉数据
        var site_names = Ext.create('Ext.data.Store', {
            fields: ['site_name'],
            proxy:{
                type:"ajax",
                url:"selSiteAreas",
                reader:{
                    type:"json",
                    totalProperty:"totalCount",
                    root:"data"
                }
            },
            autoLoad:true
        });

  //站点区域数据
        var site_areas = Ext.create('Ext.data.Store', {
            fields: ["site_location"],
            proxy:{
                type:"ajax",
                url:"selSiteAreas",
                reader:{
                    type:"json",
                    totalProperty:"totalCount",
                    root:"data"
                }
            },
            autoLoad:true
        });

        //界面
        Ext.apply(this, {
            tbar: [{
                text: '删除',
                icon: "img/15.png",
            }, "->", {
                xtype: "combo",
                labelAlign: "right",
                fieldLabel: "乡镇",
                store: site_areas,
                queryMode: "local",
                triggerAction: "all",
                displayField:"site_location",

            }, {
                xtype: "combo",
                fieldLabel: "站点",
                store: site_names,
                queryMode: "local",
                triggerAction: "all",
                displayField: "site_name",
                labelAlign: "right"
            }, {
                text: "查询",
                icon: "img/4.png",
                handler: function () {
                    alert(11);
                }

            }],
            selType: "checkboxmodel",
            columns: [{
                text: "乡镇",
                dataIndex: "site_type",
                align: "center",
                flex: 3,
                sortable: true
            }, {
                text: "站点名称",
                align: "center",
                dataIndex: "log_cycle",
                flex: 3,
                sortable: true
            }, {
                text: "巡维人员",
                align: "center",
                dataIndex: "log_count",
                flex: 3,
                sortable: true
            }, {
                text: "巡维时间",
                align: "center",
                dataIndex: "valid_start_time",
                flex: 3,
                sortable: true
            }, {
                text: "总分",
                align: "center",
                dataIndex: "valid_start_time",
                flex: 3,
                sortable: true
            }, {
                text: "总评价",
                align: "center",
                dataIndex: "valid_start_time",
                flex: 3,
                sortable: true
            }, {
                text: "操作",
                align: "center",
                dataIndex: "valid_start_time",
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