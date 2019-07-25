//异常数据汇总表


Ext.define("gjgl.ycsjhzb", {
    extend: Ext.grid.Panel,
    id: "gjgl",
    frame: true,
    initComponent: function () {


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
        var store = new Ext.data.Store({
            fields: ["warning_id", "site_location", "site_id", "warning_level", "warning_desc", "warning_state", "operate_time"],
            proxy: {
                type: "ajax",
                url: 'findAll',
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

        //站点区域数据
        var site_areas = Ext.create('Ext.data.Store', {
            fields: ["site_location"],
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
                displayField: "site_location",
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
                handler:function(){
                    Ext.Ajax.request({
                        url:"addExcel",
                        success:function(){
                            store.load();
                            alert("成功");
                        },
                        failure:function(){
                            alert("失败");
                        }
                    })
                }
            }],

            columns: [
                {
                    xtype: 'rownumberer',
                    align: 'center',
                    renderer: function (value, cellmeta, record,rowIndex, columnIndex, store) {
                        return rowIndex + 1;
                    }
                },{
                    header: "告警id",
                    hidden: true,
                    dataIndex: "warning_id"
                }, {
                    header: '乡镇名',
                    dataIndex: 'site_location',
                    align: "center",
                    flex: 3,
                    sortable: true
                }, {
                    header: '站点名称',
                    align: "center",
                    dataIndex: "site_id",
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
                 store:store,
                displayInfo: true,
                displayMsg: "当前显示第{0}到{1}条记录,共有{1}条记录",
                emptyMsg: "无记录"
            }),
        });

        this.callParent(arguments);
        store.load({
            params: {
                start: 0,
                limit: 20
            }
        });
    }
})