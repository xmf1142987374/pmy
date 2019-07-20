
//用户管理界面


Ext.define("mf.js",{
    extend:Ext.grid.Panel,
    id:"js",
    frame:true,
    // width:1350,
    // height:650,
    initComponent:function(){


        var pages=4; //每页显示的条数
        var store=Ext.create('Ext.data.Store',{
            id:"jsfy",
            fields:["role_id","role_name","role_state"],
            proxy:{
                type:"ajax",
                url:"selrole",
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
                text:"添加",
                icon:"img/16.png",
                handler:function(){
                    var win=new Ext.Window({
                        title:'添加角色',
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
                            fieldLabel:"角色编号"
                        },{
                            xtype:"textfield",
                            id:"name",
                            fieldLabel:"角色名称"
                        },{
                            xtype:"textfield",
                            id:"zt",
                            fieldLabel:"角色状态"
                        }],
                        buttons:[{
                            text:'添加',
                            handler:function () {
                                var role_id=Ext.getCmp("id").value;
                                var role_name=Ext.getCmp("name").value;
                                var role_state=Ext.getCmp("zt").value;
                                Ext.Ajax.request({
                                    url:"addrole",
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
                                        role_id : role_id,
                                        role_name : role_name,
                                        role_state : role_state
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
                icon:"img/51.png",
                handler:function(){
                    Ext.Ajax.request({
                        url:"addExcelRole",
                        success:function(){
                            store.load();
                            alert("成功");
                        },
                        failure:function(){
                            alert("失败");
                        }
                    })
                }
            },{
                text:"刷新",
                icon:"img/57.png"
            }],
            selType:"checkboxmodel",
            columns:[{
                text:"角色编号",
                dataIndex:"role_id",
                align:"center",
                flex:1,
                sortable:true
            },{
                text:"角色名称",
                dataIndex:"role_name",
                align:"center",
                flex:2,
                sortable:true
            },{
                text:"状态",
                align:"center",
                dataIndex:"role_state",
                flex:1,
                sortable:true
            },{
                text:"操作",
                align:"center",
                flex:2,
                xtype:"actioncolumn",
                items:[{
                    icon:"img/54.png",
                    dataIndex:"权限分配"
                }]
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