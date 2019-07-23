
//巡维考勤界面


Ext.define("xw.xwkq",{
    extend:Ext.grid.Panel,
    id:"xwkq",
    frame:true,
    initComponent:function () {


//巡维考勤数据store
        var pages = 3;   // 设置你想要的每页显示条数
        var store= Ext.create('Ext.data.Store', {
            id:"xwkq",
            fields:["site_location","site_name","uname","arrive_time","leave_time","is_vaild"],
            proxy:{
                type:"ajax",
                url:"selXwkq",
                reader:{
                    type:"json",
                    totalProperty:"totalCount",
                    root:"data"
                }
            },
            pageSize:pages,
            autoLoad:false,
        });

        store.load({
            params:{
                start:0,
                limit:pages
            }
        });

//站点下拉数据
        var  site_names = Ext.create('Ext.data.Store', {
            fields: ["site_name"],
            proxy:{
                type:"ajax",
                url:"selSiteNames",
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
        Ext.apply(this,{
            tbar:["->",{
                xtype:"combo",
                fieldLabel:"归属区域",
                id:"gsqy",
                store:site_areas,
                queryMode:"local",
                triggerAction:"all",
                displayField:"site_location",
                labelAlign:"right"
            },{
                xtype:"combo",
                fieldLabel:"站点",
                id:"zd",
                store:site_names,
                queryMode:"local",
                triggerAction:"all",
                displayField:"site_name",
                labelAlign:"right"
            },{
                xtype:"datefield",
                fieldLabel:"开始时间",
                id:"kssj",
                labelAlign:"right"
            },{
                xtype:"datefield",
                fieldLabel:"结束时间",
                id:"jssj",
                labelAlign:"right"
            },{
                text:"查询",
                icon:"img/4.png",
                handler:function () {
                    alert(11);
                }

            },{
                text:"清空",
                handler:function(){
                    Ext.getCmp("gsqy").setValue("");
                    Ext.getCmp("zd").setValue("");
                    Ext.getCmp("kssj").setValue("");
                    Ext.getCmp("jssj").setValue("");
                },
                icon:"img/50.png"
            }],
            selType:"checkboxmodel",
            columns:[{
                text:"所属区域",
                dataIndex:"site_location",
                align:"center",
                flex:3,
                sortable:true
            },{
                text:"站点",
                dataIndex:"site_name",
                align:"center",
                flex:3,
                sortable:true
            },{
                text:"人员",
                align:"center",
                dataIndex:"uname",
                flex:2,
                sortable:true
            },{
                text:"开始时间",
                align:"center",
                dataIndex:"arrive_time",
                flex:3,
                sortable:true
            },{
                text:"离开时间",
                align:"center",
                dataIndex:"leave_time",
                flex:3,
                sortable:true
            },{
                text:"考勤状态",
                align:"center",
                dataIndex:"is_vaild",
                flex:1,
                sortable:true
            }],
            bbar:new Ext.PagingToolbar({
                 store:store,
                displayInfo:true,
                displayMsg:"当前显示第{0}到{1}条记录,共有{1}条记录",
                emptyMsg:"无记录"
            }),
            store:store
        });

        this.callParent(arguments);
    }
})