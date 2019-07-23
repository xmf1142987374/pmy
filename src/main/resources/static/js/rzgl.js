
//用户管理界面


Ext.define("mf.rz",{
    extend:Ext.grid.Panel,
    id:"rz",
    frame:true,
    // width:1350,
    // height:650,
    initComponent:function(){



        var pages=4; //每页显示的条数
        var store=Ext.create('Ext.data.Store',{
            id:"rzfy",
            fields:["log_type","user_name","log_desc","user_id","log_time","user_ip","is_success"],
            proxy:{
                type:"ajax",
                url:"sellog",
                reader:{
                    type:"json",
                    totalProperty:"totalCount",
                    root:"data"
                }
            },
            pageSize:pages,
            autoLoad:false
        });

        store.load({
            params:{
                start:0,
                limit:pages
            }
        });



        //界面
        Ext.apply(this,{
            tbar:[{
                text:"刷新",
                handler:function(){
                    store.reload();
                },
                icon:"img/57.png"
            }],
            selType:"checkboxmodel",
            columns:[{
                text:"操作者id",
                dataIndex:"user_id",
                align:"center",
                flex:1,
                sortable:true
            },{
                text:"操作者",
                dataIndex:"user_name",
                align:"center",
                flex:1,
                sortable:true
            },{
                text:"操作者ip",
                dataIndex:"user_ip",
                align:"center",
                flex:3,
                sortable:true
            },{
                text:"操作时间",
                align:"center",
                dataIndex:"log_time",
                flex:3,
                sortable:true
            },{
                text:"日志内容",
                align:"center",
                dataIndex:"log_desc",
                flex:1,
                sortable:true
            },{
                text:"日志类型",
                align:"center",
                dataIndex:"log_type",
                flex:1,
                sortable:true
            },{
                text:"是否成功",
                align:"center",
                dataIndex:"is_success",
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
    //this.callParent(arguments)

});