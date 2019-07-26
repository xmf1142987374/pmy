
//考勤规则设置界面


Ext.define("xw.kqgz",{
    extend:Ext.grid.Panel,
    id:"kqgz",
    frame:true,
    initComponent:function () {

//下拉框模型
        var zq = Ext.create('Ext.data.Store', {
            fields: ['zq', 'zqvalue'],
            data : [
                {"zq":"每周", "zqvalue":"每周"},
                {"zq":"半个月", "zqvalue":"半个月"},
                {"zq":"每月", "zqvalue":"每月"}
                //...
            ]
        });

//数据store
        var pages = 4;   // 设置你想要的每页显示条数
        var store= Ext.create('Ext.data.Store', {
            id:"xx",
            fields:["sets_id","site_type","valid_time","log_cycle","log_count","valid_start_time","valid_end_time"],
            proxy:{
                type:"ajax",
                url:"selkqsets",
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
                handler:function () {
                    var win=new Ext.Window({
                        title:"添加考勤规则",
                        width:300,
                        height:300,
                        frame:true
                    });
                    var form=new Ext.form.Panel({

                        border:false,
                        frame:true,
                        layout:"form",
                        items:[{
                            xtype:"textfield",
                            id:"type",
                            fieldLabel:"站点类型"
                        },{
                            xtype:"textfield",
                            id:"yxsc",
                            fieldLabel:"有效时长"
                        },{
                            xtype:"combo",
                            id:"zq",
                            store:zq,
                            queryMode:"local",
                            triggerAction:"all",
                            displayField:"zq",
                            value:"每周",
                            fieldLabel:"周期"
                        },{
                            xtype:"numberfield",
                            id:"cs",
                            allowDecimals:false,
                            hideTrigger:true,
                            fieldLabel:"次数"
                        },{
                            xtype:"datefield",
                            id:"yxss",
                            fieldLabel:"有效期间_开始",
                            format:"y-m-d"
                        },{
                            xtype:"datefield",
                            id:"yxjs",
                            fieldLabel:"有效期间_结束",
                            format:"y-m-d"
                        }],
                        buttons:[{
                            text:"添加",
                            //提交事件
                            handler:function () {
                                var site_type=Ext.getCmp("type").value;
                                var valid_time=Ext.getCmp("yxsc").value;
                                var log_cycle=Ext.getCmp("zq").value;
                                var log_count=Ext.getCmp("cs").value;
                                var valid_start_time=Ext.getCmp("yxss").value;
                                var valid_end_time=Ext.getCmp("yxjs").value;
                                Ext.Ajax.request({
                                    url:"addkqset",
                                    type:"post",
                                    success:function () {
                                        alert("添加成功");
                                        store.load();//添加之后重载数据 局部刷新
                                        win.close();
                                    },
                                    failure:function () {
                                        alert("添加失败")
                                    },
                                    params:{
                                        site_type : site_type,
                                        valid_time : valid_time,
                                        log_cycle : log_cycle,
                                        log_count : log_count,
                                        valid_start_time : valid_start_time,
                                        valid_end_time : valid_end_time,
                                    }
                                });
                            }
                        },{
                            text:"取消",
                            handler:function () {
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
                icon:"img/15.png",
                handler:function () {
                    var selectdata=Ext.getCmp("kqgz").getSelectionModel().getSelection();
                    var data=new Array();
                    for (var i = 0; i <selectdata.length ; i++) {
                        data.push(selectdata[i].data.sets_id);
                    }

                    Ext.Ajax.request({
                        url: "delkqsets",
                        type: "post",
                        success:function () {
                            store.load();//添加之后重载数据 局部刷新
                            alert("删除成功");

                        },
                        failure:function () {
                            alert("删除失败")
                        },
                        params:{

                            data:data
                        }
                    });


                }

            },"->",{
                text:"查询",
                icon:"img/4.png",
                handler:function () {
                    alert(11);
                }
            }],
            selType:"checkboxmodel",
            columns:[{
                xtype: 'rownumberer',
                align: 'center',
                renderer: function (value, cellmeta, record,rowIndex, columnIndex, store) {
                    return rowIndex + 1;
                }
            },{
                text:"id",
                dataIndex:"sets_id",
                hidden:true
            },{
                text:"站点类型",
                dataIndex:"site_type",
                align:"center",
                flex:2,
                sortable:true
            },{
                text:"有效时长(分钟)",
                dataIndex:"valid_time",
                align:"center",
                flex:3,
                sortable:true
            },{
                text:"周期",
                align:"center",
                dataIndex:"log_cycle",
                flex:1,
                sortable:true
            },{
                text:"次数",
                align:"center",
                dataIndex:"log_count",
                flex:1,
                sortable:true
            },{
                text:"规则有效期间_开始",
                align:"center",
                dataIndex:"valid_start_time",
                flex:4,
                sortable:true
            },{
                text:"规则有效期间_结束",
                align:"center",
                dataIndex:"valid_end_time",
                flex:4,
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