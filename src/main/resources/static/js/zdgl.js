
//站点管理界面


Ext.define("mf.zd",{
    extend:Ext.grid.Panel,
    id:"zdss",
    frame:true,
    initComponent:function(){


        var pages=7; //每页显示的条数
        var store=Ext.create('Ext.data.Store',{
            id:"zdfy",
            fields:["site_id","site_name","site_location","site_type","site_desc","site_pic","town_x_num","town_y_num","uname","user_tel"],
            proxy:{
                type:"ajax",
                url:"selSite",
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

        //站点类型下拉数据
        var  site_types = Ext.create('Ext.data.Store', {
            fields: ["site_type"],
            proxy:{
                type:"ajax",
                url:"selSiteType",
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
                        title:'添加站点',
                        width:300,
                        height:300,
                        frame:true
                    });
                    var form= new Ext.form.Panel({
                        border:false,
                        frame:true,
                        fileUpload:true,
                        layout:"form",
                        items:[{
                            xtype:"textfield",
                            id:"id",
                            fieldLabel:"站点编号"
                        },{
                            xtype:"textfield",
                            id:"name",
                            fieldLabel:"站点名称"
                        },{
                            xtype:"textfield",
                            id:"dz",
                            fieldLabel:"站点地址"
                        },{
                            xtype:"combo",
                            id:"lx",
                            fieldLabel:"站点类型",
                            store:site_types,
                            queryMode:"local",
                            triggerAction:"all",
                            displayField:"site_type"
                        },{
                            xtype:"textfield",
                            id:"ms",
                            fieldLabel:"站点描述"
                        },{
                            xtype:"textfield",
                            id:"jcy",
                            fieldLabel:"片区巡查员"
                        },{
                            xtype:"textfield",
                            id:"lxfs",
                            fieldLabel:"联系方式"
                        },{
                            xtype:"filefield",
                            name:"fname",
                            id:"tp",
                            fieldLabel:"前期施工"
                        }],
                        buttons:[{
                            text:'添加',
                            handler:function () {
                                var site_id=Ext.getCmp("id").value;
                                var site_name=Ext.getCmp("name").value;
                                var site_location=Ext.getCmp("dz").value;
                                var site_type=Ext.getCmp("lx").value;
                                var site_desc=Ext.getCmp("ms").value;
                                var uname=Ext.getCmp("jcy").value;
                                var user_tel=Ext.getCmp("lxfs").value;
                                var site_pic=Ext.getCmp("tp").value;
                                console.log(site_pic);
                                form.getForm().submit({
                                    clientValidation: true,
                                    url: 'addsite',
                                    params: {
                                        site_id : site_id,
                                        site_name : site_name,
                                        site_location : site_location,
                                        site_type : site_type,
                                        site_desc : site_desc,
                                        uname : uname,
                                        user_tel : user_tel
                                    },
                                    failure: function(form, action) {
                                       store.load();
                                           win.close();
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
            },{
                text:"修改",
                icon:"img/50.png",
                handler:function(){
                    var selectdata=Ext.getCmp("zdss").getSelectionModel().getSelection();
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
                        });
                        win.add(form);
                        win.show();
                    }
                }
            },{
                text:"删除",
                icon:"img/15.png",
                handler:function(){
                    var selectdata=Ext.getCmp("zdss").getSelectionModel().getSelection();
                    var data=new Array();
                    for (var i = 0; i <selectdata.length ; i++) {
                        data.push(selectdata[i].data.site_id);
                    }

                    Ext.Ajax.request({
                        url:"delsite",
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
            }],
            selType:"checkboxmodel",
            columns:[{
                text:"照片",
                dataIndex:"site_pic",
                renderer :function(value){
                    return "<img src='siteimg/"+value+"'style='width: 100px; height: 66px'/>";
                },
                align:"center",
                flex:2,
                sortable:true
            },{
                text:"站点编号",
                dataIndex:"site_id",
                align:"center",
                flex:2,
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
                text:"站点地址",
                align:"center",
                dataIndex:"site_location",
                flex:2,
                sortable:true
            },{
                text:"站点管理员",
                align:"center",
                dataIndex:"uname",
                flex:2,
                sortable:true
            },{
                text:"联系方式",
                align:"center",
                dataIndex:"user_tel",
                flex:2,
                sortable:true
            },{
                text:"站点描述",
                align:"center",
                dataIndex:"site_desc",
                flex:3,
                sortable:true
            },{
                text:"经度",
                align:"center",
                flex:2,
                dataIndex:"town_x_num",
                sortable:true
            },{
                text:"纬度",
                align:"center",
                flex:2,
                dataIndex:"town_y_num",
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