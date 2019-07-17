
//巡维考勤界面


Ext.define("xw.xwkq",{
    extend:Ext.grid.Panel,
    id:"xwkq",
    frame:true,
    initComponent:function () {

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
            proxy:{
                type:"ajax",
                url:"selsitenames",
                reader:{
                    type:"json",
                    totalProperty:"totalCount",
                    root:"data"
                }
            },
            autoLoad:true
        });

        //界面
        Ext.apply(this,{
            tbar:["->",{
                xtype:"combo",
                fieldLabel:"归属区域",
                labelAlign:"right"
            },{
                xtype:"combo",
                fieldLabel:"站点",
                store:zd,
                queryMode:"local",
                triggerAction:"all",
                displayField:"site_name",
                labelAlign:"right"
            },{
                xtype:"datefield",
                fieldLabel:"开始时间",
                labelAlign:"right"
            },{
                xtype:"datefield",
                fieldLabel:"结束时间",
                labelAlign:"right"
            },{
                text:"查询",
                icon:"img/4.png",
                handler:function () {
                    alert(11);
                }

            },{
                text:"清空",
                icon:"img/50.png"
            }],
            selType:"checkboxmodel",
            columns:[{
                text:"所属区域",
                dataIndex:"site_type",
                align:"center",
                flex:3,
                sortable:true
            },{
                text:"站点",
                dataIndex:"valid_time",
                align:"center",
                flex:3,
                sortable:true
            },{
                text:"人员",
                align:"center",
                dataIndex:"log_cycle",
                flex:2,
                sortable:true
            },{
                text:"开始时间",
                align:"center",
                dataIndex:"log_count",
                flex:3,
                sortable:true
            },{
                text:"离开时间",
                align:"center",
                dataIndex:"valid_start_time",
                flex:3,
                sortable:true
            },{
                text:"考勤状态",
                align:"center",
                dataIndex:"valid_start_time",
                flex:1,
                sortable:true
            }],
            bbar:new Ext.PagingToolbar({
                // store:store,
                displayInfo:true,
                displayMsg:"当前显示第{0}到{1}条记录,共有{1}条记录",
                emptyMsg:"无记录"
            }),
            // store:store
        });

        this.callParent(arguments);
    }
})