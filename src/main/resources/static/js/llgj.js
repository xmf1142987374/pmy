
//流量告警界面


Ext.define("mf.ll",{
    extend:Ext.grid.Panel,
    id:"ll",
    frame:true,
    // width:1350,
    // height:650,
    initComponent:function(){


        var pages=4; //每页显示的条数
        var store=Ext.create('Ext.data.Store',{
            id:"llfy",
            fields:["site_id","site_name","site_type","warning_type","warning_max","warning_min"],
            proxy:{
                type:"ajax",
                url:"selwarning",
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
                text:"流量告警设置",
                icon:"img/16.png",
                handler:function(){
                    var win=new Ext.Window({
                        title:'设置水流量告警',
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
                            fieldLabel:"参考数据"
                        },{
                            xtype:"textfield",
                            id:"name",
                            fieldLabel:"上限值倍数"
                        },{
                            xtype:"textfield",
                            id:"ms",
                            fieldLabel:"下限值倍数"
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
                    });
                    win.add(form);
                    win.show();
                }
            }],
            selType:"checkboxmodel",
            columns:[{
                text:"站点编号",
                dataIndex:"site_id",
                align:"center",
                flex:1,
                sortable:true
            },{
                text:"站点名称",
                align:"center",
                dataIndex:"site_name",
                flex:2,
                sortable:true
            },{
                text:"站点类型",
                align:"center",
                dataIndex:"site_type",
                flex:2,
                sortable:true
            },{
                text:"水流量告警类型",
                align:"center",
                dataIndex:"warning_type",
                flex:2,
                sortable:true
            },{
                text:"上限值倍数",
                align:"center",
                dataIndex:"warning_max",
                flex:2,
                sortable:true
            },{
                text:"下限值倍数",
                align:"center",
                dataIndex:"warning_min",
                flex:2,
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