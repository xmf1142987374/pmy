//巡维考勤界面


Ext.define("gjgl.ycsjhzb", {
    extend: Ext.grid.Panel,
    id: "gjgl",
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
//         var pages = 4;   // 设置你想要的每页显示条数
//         var store= Ext.create('Ext.data.Store', {
//             id:"xx",
//             fields:["sets_id","site_type","valid_time","log_cycle","log_count","valid_start_time","valid_end_time"],
//             proxy:{
//                 type:"ajax",
//                 //url:"selkqsets",
//                 reader:{
//                     type:"json",
//                     totalProperty:"totalCount",
//                     root:"data"
//                 }
//             },
//             pageSize:pages,
//             autoLoad:false
//         });
//
//         store.load({
//             params:{
//                 start:0,
//                 limit:pages
//             }
//         });
//站点下拉数据
        var zd = Ext.create('Ext.data.Store', {
            fields: ['site_name', 'zqvalue'],
            proxy: {
                type: "ajax",
                url: "selsitenames",
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
            tbar: ["->", {
                xtype: "combo",
                fieldLabel: "告警分类",

            }, {
                xtype: "combo",
                fieldLabel: "乡镇",
                store: zd,
                queryMode: "local",
                triggerAction: "all",
                displayField: "",
                labelAlign: "right"
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
                text: "导出异常数据基本情况汇总表",
                icon: "img/51.png"
            }, {
                text: "导出异常数据点位汇总表",
                icon: "img/51.png"
            }],
            selType: "checkboxmodel",
            columns: [{
                text: "乡镇名",
                dataIndex: "",
                align: "center",
                flex: 3,
                sortable: true
            }, {
                text: "村名",
                dataIndex: "",
                align: "center",
                flex: 3,
                sortable: true
            }, {
                text: "站点名称",
                align: "center",
                dataIndex: "",
                flex: 3,
                sortable: true
            }, {
                text: "告警级别",
                align: "center",
                dataIndex: "",
                flex: 3,
                sortable: true
            }, {
                text: "告警内容",
                align: "center",
                dataIndex: "",
                flex: 3,
                sortable: true
            }, {
                text: "是否处理",
                align: "center",
                dataIndex: "",
                flex: 3,
                sortable: true
            }, {
                text: "告警时间",
                align: "center",
                dataIndex: "",
                flex: 3,
                sortable: true
            }],
            bbar: new Ext.PagingToolbar({
                // store:store,
                displayInfo: true,
                displayMsg: "当前显示第{0}到{1}条记录,共有{1}条记录",
                emptyMsg: "无记录"
            }),
            // store:store
        });

        this.callParent(arguments);
    }
})