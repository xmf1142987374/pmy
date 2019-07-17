
//用户管理界面


Ext.define("mf.three",{
    extend:Ext.grid.Panel,
    id:"mf",
    frame:true,
    // width:1350,
    // height:650,
    initComponent:function(){



        var store=new Ext.data.Store({
            fields:["userid","uname","user_dept","username","user_sex","user_tel","user_phone","user_state"],
            proxy:{
                type:"ajax",
                url:"seluser",
                reader:{
                    type:"json",
                    totalProperty:"totalCount",
                    root:"data"
                }
            },
            autoLoad:true
        });

        Ext.regModel("xbmodel",{
            fields:[{
                name:"xb"
            },{
                name:"xbvalue"
            }]
        });

        var xb=Ext.create(Ext.data.Store,{
            model:"xbmodel",
            data:[{
                xb:"男",
                //xbvalue:"1"
            },{
                xb:"女",
                //xbvalue:"2"
            }]
        });

        Ext.apply(this,{
            tbar:[{
                text:"添加",
                icon:"img/16.png"
            },{
                text:"修改",
                icon:"img/50.png"
            },{
                text:"导出Excel",
                icon:"img/51.png"
            },{
                text:"删除",
                icon:"img/15.png"
            },{
                text:"修改密码",
                icon:"img/56.png"
            },{
                text:"刷新",
                icon:"img/57.png"
            },"-","->",{
                xtype:"textfield",
                labelAlign: "right",
                width:200,
                fieldLabel:"姓名"
            },{
                xtype:"textfield",
                labelAlign: "right",
                width:200,
                fieldLabel:"登录账户"
            },{
                xtype:"combo",
                labelAlign: "right",
                width:150,
                fieldLabel:"性别",
                store:xb,
                queryMode:"local",
                triggerAction:"all",
                displayField:"xb",
                valueField:"xbvalue"
            },{
                text:"查询",
                icon:"img/4.png",
                handler:function () {
                    alert(11);
                }
            }],
            selType:"checkboxmodel",
            columns:[{
                text:"工号",
                dataIndex:"userid",
                align:"center",
                flex:1,
                sortable:true
            },{
                text:"姓名",
                dataIndex:"uname",
                align:"center",
                flex:3,
                sortable:true
            },{
                text:"部门",
                align:"center",
                dataIndex:"user_dept",
                flex:3,
                sortable:true
            },{
                text:"登录账户",
                align:"center",
                dataIndex:"username",
                flex:3,
                sortable:true
            },{
                text:"性别",
                align:"center",
                dataIndex:"user_sex",
                flex:1,
                sortable:true
            },{
                text:"电话",
                align:"center",
                dataIndex:"user_tel",
                flex:3,
                sortable:true
            },{
                text:"手机号",
                align:"center",
                dataIndex:"user_phone",
                flex:3,
                sortable:true
            },{
                text:"状态",
                align:"center",
                dataIndex:"user_state",
                flex:1,
                sortable:true
            },{
                text:"操作",
                align:"center",
                flex:4,
                xtype:"actioncolumn",
                items:[{
                    icon:"img/55.png",
                    text:"查看明细"
                },{
                    icon:"img/52.png",
                    dataIndex:"角色"
                },{
                    icon:"img/54.png",
                    dataIndex:"数据权限"
                }]
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