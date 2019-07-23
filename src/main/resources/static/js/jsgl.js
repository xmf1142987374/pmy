
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
            fields:["role_id","role_name","role_state","create_time","create_user"],
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
                icon:"img/50.png",
                handler:function(){
                    var selectdata=Ext.getCmp("js").getSelectionModel().getSelection();
                    //console.log(selectdata[0].data.dep_id);

                    if(selectdata.length>1){
                        alert("只能一个一个的修改哟!");
                    }else if(selectdata.length<1){
                        alert("请选择一个修改哟!");
                    }else{
                        var r_id=selectdata[0].data.role_id;
                        var r_name=selectdata[0].data.role_name;
                        var r_state=selectdata[0].data.role_state;
                        var c_time=selectdata[0].data.create_time;
                        var c_user=selectdata[0].data.create_user;
                        //console.log(c_time);
                        //console.log(c_user);
                        var win=new Ext.Window({
                            title:'修改角色',
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
                                fieldLabel:"角色编号",
                                disabled:true,
                                value:r_id
                            },{
                                xtype:"textfield",
                                id:"name",
                                fieldLabel:"角色名称",
                                value:r_name
                            },{
                                xtype:"textfield",
                                id:"zt",
                                fieldLabel:"角色状态",
                                value:r_state
                            }],
                            buttons:[{
                                text:'确定修改',
                                handler:function () {
                                    var role_id=Ext.getCmp("id").value;
                                    var role_name=Ext.getCmp("name").value;
                                    var role_state=Ext.getCmp("zt").value;
                                    Ext.Ajax.request({
                                        url:"updaterole",
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
                                            role_state : role_state,
                                            create_time : c_time,
                                            create_user : c_user
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
                }
            },{
                text:"删除",
                icon:"img/15.png",
                handler: function () {
                    var selectdata = Ext.getCmp("js").getSelectionModel().getSelection();
                    var data = new Array();
                    for (var i = 0; i < selectdata.length; i++) {
                        data.push(selectdata[i].data.role_id);
                    }

                    Ext.Ajax.request({
                        url: "delrole",
                        type: "post",
                        success: function () {
                            store.load();//添加之后重载数据 局部刷新
                            alert("删除成功");
                        },
                        failure: function () {
                            alert("删除失败");
                        },
                        params: {
                            data: data
                        }
                    })
                }
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
                handler:function(){
                    store.reload();
                },
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