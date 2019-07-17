
//用户管理界面


Ext.define("mf.bm",{
    extend:Ext.grid.Panel,
    id:"bm",
    frame:true,
    // width:1350,
    // height:650,
    initComponent:function(){



        var store=new Ext.data.Store({
            fields:["dep_id","dep_name","dep_desc","dep_state","create_user","create_time","modify_user","modify_time"],
            proxy:{
                type:"ajax",
                url:"seldept",
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
            tbar:[{
                text:"添加",
                icon:"img/16.png",
                handler:function(){
                    var win=new Ext.Window({
                        title:'添加部门',
                        width:300,
                        height:200,
                        frame:true
                    });
                    var form= new Ext.panel.Panel({
                        border:false,
                        frame:true,
                        layout:"form",
                        items:[{
                            xtype:"textfield",
                            id:"id",
                            fieldLabel:"部门id"
                        },{
                            xtype:"textfield",
                            id:"name",
                            fieldLabel:"部门名称"
                        },{
                            xtype:"textfield",
                            id:"ms",
                            fieldLabel:"部门描述"
                        },{
                            xtype:"textfield",
                            id:"zt",
                            fieldLabel:"部门状态"
                        }],
                        buttons:[{
                            text:'添加',
                            handler:function () {
                                var dep_id=Ext.getCmp("id").value;
                                var dep_name=Ext.getCmp("name").value;
                                var dep_desc=Ext.getCmp("ms").value;
                                var dep_state=Ext.getCmp("zt").value;
                                Ext.Ajax.request({
                                    url:"adddept",
                                    type:"post",
                                    success:function(){
                                        alert("成功");
                                        store.load();
                                        win.close();
                                    },
                                    failure:function(){
                                        alert("失败");
                                    },
                                    params:{
                                        dep_id : dep_id,
                                        dep_name : dep_name,
                                        dep_desc : dep_desc,
                                        dep_state : dep_state,
                                    }
                                })
                            }
                        },{
                            text:"取消",
                            handler:function(){
                                win.close();
                            }
                        }]
                    })
                    win.add(form);
                    win.show();
                }
            },{
                text:"修改",
                icon:"img/50.png"
            },{
                text:"删除",
                icon:"img/15.png"
            },{
                text:"导出Excel",
                icon:"img/51.png"
            },{
                text:"刷新",
                icon:"img/57.png"
            }],
            selType:"checkboxmodel",
            columns:[{
                text:"部门代码",
                dataIndex:"dep_id",
                align:"center",
                flex:1,
                sortable:true
            },{
                text:"部门名称",
                dataIndex:"dep_name",
                align:"center",
                flex:3,
                sortable:true
            },{
                text:"部门描述",
                align:"center",
                dataIndex:"dep_desc",
                flex:3,
                sortable:true
            },{
                text:"状态",
                align:"center",
                dataIndex:"dep_state",
                flex:1,
                sortable:true
            },{
                text:"创建时间",
                align:"center",
                dataIndex:"create_time",
                flex:3,
                sortable:true
            },{
                text:"创建者",
                align:"center",
                dataIndex:"create_user",
                flex:1,
                sortable:true
            },{
                text:"修改时间",
                align:"center",
                dataIndex:"modify_time",
                flex:3,
                sortable:true
            },{
                text:"修改者",
                align:"center",
                flex:1,
                dataIndex:"modify_user",
                sortable:true
            }],
            bbar:new Ext.PagingToolbar({
                pageSize:20,
                displayInfo:true,
                displayMsg:"当前显示第{0}到{1}条记录,共有2条记录",
                emptyMsg:"无记录"
            }),
            store:store
        });

        this.callParent(arguments);
    }
    //this.callParent(arguments)

});