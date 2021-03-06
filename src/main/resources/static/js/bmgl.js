
//用户管理界面


Ext.define("mf.bm",{
    extend:Ext.grid.Panel,
    id:"bm",
    frame:true,
    // width:1350,
    // height:650,
    initComponent:function(){


        var pages=4; //每页显示的条数
        var store=Ext.create('Ext.data.Store',{
            id:"bmfy",
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
                                        dep_state : dep_state
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
                    var selectdata=Ext.getCmp("bm").getSelectionModel().getSelection();
                    //console.log(selectdata[0].data.dep_id);

                    if(selectdata.length>1){
                        alert("只能一个一个的修改哟!");
                    }else if(selectdata.length<1){
                        alert("请选择一个修改哟!");
                    }else{
                        var d_id=selectdata[0].data.dep_id;
                        var d_name=selectdata[0].data.dep_name;
                        var d_desc=selectdata[0].data.dep_desc;
                        var d_state=selectdata[0].data.dep_state;
                        var c_user=selectdata[0].data.create_user;
                        var c_time=selectdata[0].data.create_time;
                        var win=new Ext.Window({
                            title:'修改部门',
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
                                fieldLabel:"部门id",
                                disabled:true,
                                value:d_id
                            },{
                                xtype:"textfield",
                                id:"name",
                                fieldLabel:"部门名称",
                                value:d_name
                            },{
                                xtype:"textfield",
                                id:"ms",
                                fieldLabel:"部门描述",
                                value:d_desc
                            },{
                                xtype:"textfield",
                                id:"zt",
                                fieldLabel:"部门状态",
                                value:d_state
                            }],
                            buttons:[{
                                text:'确定修改',
                                handler:function () {
                                    var dep_id=Ext.getCmp("id").value;
                                    var dep_name=Ext.getCmp("name").value;
                                    var dep_desc=Ext.getCmp("ms").value;
                                    var dep_state=Ext.getCmp("zt").value;
                                    Ext.Ajax.request({
                                        url:"updatedept",
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
                                            create_user:c_user,
                                            create_time:c_time
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
                handler:function(){
                    var selectdata=Ext.getCmp("bm").getSelectionModel().getSelection();
                    var data=new Array();
                    for (var i = 0; i <selectdata.length ; i++) {
                        data.push(selectdata[i].data.dep_id);
                    }

                    Ext.Ajax.request({
                        url:"deldept",
                        type:"post",
                        success:function(){
                            store.load();//添加之后重载数据 局部刷新
                            alert("删除成功");
                        },
                        failure:function(){
                            alert("删除失败");
                        },
                        params:{
                            data:data
                        }
                    })
                }
            },{
                text:"导出Excel",
                icon:"img/51.png",
                handler:function(){
                    Ext.Ajax.request({
                        url:"addExcelDept",
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