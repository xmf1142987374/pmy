
//站点考勤纪录界面


Ext.define("site.kqjl",{
    extend:Ext.grid.Panel,
    id:"kqjl",
    frame:true,
    initComponent:function () {

        var sitelocation =parent.parent.Ext.getCmp("tabpanel").getActiveTab().title;
// 考勤纪录数据store
        var pages = 1;   // 设置你想要的每页显示条数
        var store= Ext.create('Ext.data.Store', {
            fields:["uname","uname","arrive_time","leave_time","is_vaild"],
            proxy:{
                type:"ajax",
                url:"/selSiteXwkq",
                reader:{
                    type:"json",
                    totalProperty:"totalCount",
                    root:"data",
                }
            },
            pageSize:pages,
            autoLoad:false,
        });

        store.load({
            params:{
                start:0,
                limit:pages,
                sitelocation:sitelocation
            }
        });

        store.on("beforeload",function(){

            Ext.apply(store.proxy.extraParams, {sitelocation:sitelocation});

        });



        //界面
        Ext.apply(this,{
            selType:"checkboxmodel",
            columns:[{
                xtype: 'rownumberer',
                align: 'center',
                renderer: function (value, cellmeta, record,rowIndex, columnIndex, store) {
                    return rowIndex + 1;
                }
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