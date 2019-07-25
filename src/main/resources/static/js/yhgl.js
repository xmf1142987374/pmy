//用户管理界面


Ext.define("mf.three", {
    extend: Ext.grid.Panel,
    id: "mf",
    frame: true,
    // width:1350,
    // height:650,
    initComponent: function () {
        var store;

        var pages = 4; //每页显示的条数
        store = Ext.create('Ext.data.Store', {
            fields: ["userid", "uname", "user_dept", "username", "password", "user_sex", "user_tel", "user_phone", "user_state","create_time"],
            proxy: {
                type: "ajax",
                url: "seluser",
                reader: {
                    type: "json",
                    totalProperty: "totalCount",
                    root: "data"
                }
            },
            pageSize: pages,
            autoLoad: false
        });

        store.load({
            params: {
                start: 0,
                limit: pages
            }
        });
        var states =Ext.create(Ext.data.Store,{
            fields:['sex'],
            data:[
                {"sex":"男"},
                {"sex":"女"}
            ]
        });

        //界面
        Ext.apply(this, {
            tbar: [{
                text: "添加",
                icon: "img/16.png",
                handler: function () {
                    var win = new Ext.Window({
                        title: '添加用户',
                        width: 300,
                        height: 300,
                        frame: true
                    });
                    var form = new Ext.panel.Panel({
                        border: false,
                        frame: true,
                        layout: "form",
                        items: [{
                            xtype: "textfield",
                            id: "gh",
                            fieldLabel: "工号"
                        }, {
                            xtype: "textfield",
                            id: "name",
                            fieldLabel: "姓名"
                        }, {
                            xtype: "textfield",
                            id: "password",
                            fieldLabel: "密码"
                        }, {
                            xtype: "textfield",
                            id: "loginName",
                            fieldLabel: "登录账号"
                        }, {
                            xtype: "textfield",
                            id: "bm",
                            fieldLabel: "部门"
                        }, {
                            xtype: "textfield",
                            id: "sex",
                            fieldLabel: "性别"
                        }, {
                            xtype: "textfield",
                            id: "tel",
                            fieldLabel: "电话"
                        }, {
                            xtype: "textfield",
                            id: "phone",
                            fieldLabel: "手机号"
                        }],
                        buttons: [{
                            text: '添加',
                            handler: function () {
                                var user_id = Ext.getCmp("gh").value;
                                var user_name = Ext.getCmp("name").value;
                                var user_password = Ext.getCmp("password").value;
                                var user_login_name = Ext.getCmp("loginName").value;
                                var user_department = Ext.getCmp("bm").value;
                                var user_gender = Ext.getCmp("sex").value;
                                var user_tel = Ext.getCmp("tel").value;
                                var user_phone = Ext.getCmp("phone").value;
                                Ext.Ajax.request({
                                    url: "adduser",
                                    type: "post",
                                    success: function () {
                                        alert("成功");
                                        store.load();
                                        win.close();
                                    },
                                    failure: function () {
                                        alert("失败");
                                    },
                                    params: {
                                        user_id: user_id,
                                        user_name: user_name,
                                        user_password: user_password,
                                        user_login_name: user_login_name,
                                        user_department: user_department,
                                        user_gender: user_gender,
                                        user_tel: user_tel,
                                        user_phone: user_phone
                                    }
                                })
                            }
                        }, {
                            text: "取消",
                            handler: function () {
                                win.close();
                            }
                        }]
                    });
                    win.add(form);
                    win.show();
                }
            }, {
                text: "修改",
                icon: "img/50.png",
                handler:function(){
                    var selectdata=Ext.getCmp("mf").getSelectionModel().getSelection();
                    //console.log(selectdata[0].data.dep_id);

                    if(selectdata.length>1){
                        alert("只能一个一个的修改哟!");
                    }else if(selectdata.length<1){
                        alert("请选择一个修改哟!");
                    }else{
                        var uid=selectdata[0].data.userid;
                        var u_name=selectdata[0].data.uname;
                        var user_name=selectdata[0].data.username;
                        var pword=selectdata[0].data.password;
                        var u_dept=selectdata[0].data.user_dept;
                        var u_sex=selectdata[0].data.user_sex;
                        var u_tel=selectdata[0].data.user_tel;
                        var u_phone=selectdata[0].data.user_phone;
                        var u_state=selectdata[0].data.user_state;
                        var c_time=selectdata[0].data.create_time;
                        //console.log(c_time);
                        //console.log(c_user);
                        var win=new Ext.Window({
                            title:'修改用户',
                            width:300,
                            height:300,
                            frame:true
                        });
                        var form= new Ext.panel.Panel({
                            border:false,
                            frame:true,
                            layout:"form",
                            items:[{
                                xtype:"textfield",
                                id:"id",
                                fieldLabel:"工号",
                                disabled:true,
                                value:uid
                            },{
                                xtype:"textfield",
                                id:"name",
                                fieldLabel:"姓名",
                                value:u_name
                            },{
                                xtype:"textfield",
                                id:"bm",
                                fieldLabel:"部门",
                                value:u_dept
                            },{
                                xtype:"textfield",
                                id:"zh",
                                fieldLabel:"登录账号",
                                value:user_name
                            },{
                                xtype:"textfield",
                                id:"xb",
                                fieldLabel:"性别",
                                value:u_sex
                            },{
                                xtype:"textfield",
                                id:"dh",
                                fieldLabel:"电话",
                                value:u_tel
                            },{
                                xtype:"textfield",
                                id:"sj",
                                fieldLabel:"手机号",
                                value:u_phone
                            },{
                                xtype:"textfield",
                                id:"zt",
                                fieldLabel:"状态",
                                value:u_state
                            }],
                            buttons:[{
                                text:'确定修改',
                                handler:function () {
                                    var userid=Ext.getCmp("id").value;
                                    var uname=Ext.getCmp("name").value;
                                    var username=Ext.getCmp("zh").value;
                                    var user_dept=Ext.getCmp("bm").value;
                                    var user_sex=Ext.getCmp("xb").value;
                                    var user_tel=Ext.getCmp("dh").value;
                                    var user_phone=Ext.getCmp("sj").value;
                                    var user_state=Ext.getCmp("zt").value;
                                    Ext.Ajax.request({
                                        url:"updateuser",
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
                                            userid : userid,
                                            uname : uname,
                                            username : username,
                                            password : pword,
                                            user_dept : user_dept,
                                            user_sex : user_sex,
                                            user_tel : user_tel,
                                            user_phone : user_phone,
                                            user_state : user_state,
                                            create_time : c_time
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
            }, {
                text: "导出Excel",
                icon: "img/51.png",
                handler:function(){
                    Ext.Ajax.request({
                        url:"addExcelUser",
                        success:function(){
                            store.load();
                            alert("成功");
                        },
                        failure:function(){
                            alert("失败");
                        }
                    })
                }
            }, {
                text: "删除",
                icon: "img/15.png",
                handler: function () {
                    var selectdata = Ext.getCmp("mf").getSelectionModel().getSelection();
                    var data = new Array();
                    for (var i = 0; i < selectdata.length; i++) {
                        data.push(selectdata[i].data.userid);
                    }

                    Ext.Ajax.request({
                        url: "delUser",
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
            }, {
                text: "刷新",
                handler:function(){
                    store.reload();
                },
                icon: "img/57.png"
            }, "-", "->", {
                xtype: "textfield",
                labelAlign: "right",
                width: 200,
                id:"u_xm",
                fieldLabel: "姓名"
            }, {
                xtype: "textfield",
                labelAlign: "right",
                width: 200,
                id:"u_zh",
                fieldLabel: "登录账户"
            }, {
                xtype: "combo",
                labelAlign: "right",
                width: 150,
                id:"u_xb",
                fieldLabel: "性别",
                store: states,
                queryMode: "local",
                displayField: "sex"
            }, {
                text: "查询",
                icon: "img/4.png",
                handler: function () {
                    var uname=Ext.getCmp("u_xm").value;
                    var username=Ext.getCmp("u_zh").value;
                    var user_sex=Ext.getCmp("u_xb").value;
                    store.proxy.url="GJseluser";
                    store.load({
                        params:{
                            start:0,
                            limit:pages,
                            uname : uname,
                            username : username,
                            user_sex : user_sex
                        }
                    });

                    store.on("beforeload",function(){

                        Ext.apply(store.proxy.extraParams, {
                            uname : uname,
                            username : username,
                            user_sex : user_sex
                        });

                    });
                }
            }],
            selType: "checkboxmodel",
            columns: [{
                xtype: 'rownumberer',
                align: 'center',
                renderer: function (value, cellmeta, record,rowIndex, columnIndex, store) {
                    return rowIndex + 1;
                }
            },{
                text: "工号",
                dataIndex: "userid",
                align: "center",
                flex: 1,
                sortable: true
            }, {
                text: "姓名",
                dataIndex: "uname",
                align: "center",
                flex: 3,
                sortable: true
            }, {
                text: "部门",
                align: "center",
                dataIndex: "user_dept",
                flex: 3,
                sortable: true
            }, {
                text: "登录账户",
                align: "center",
                dataIndex: "username",
                flex: 3,
                sortable: true
            }, {
                text: "性别",
                align: "center",
                dataIndex: "user_sex",
                flex: 1,
                sortable: true
            }, {
                text: "电话",
                align: "center",
                dataIndex: "user_tel",
                flex: 3,
                sortable: true
            }, {
                text: "手机号",
                align: "center",
                dataIndex: "user_phone",
                flex: 3,
                sortable: true
            }, {
                text: "状态",
                align: "center",
                dataIndex: "user_state",
                flex: 1,
                sortable: true
            }, {
                text: "操作",
                align: "center",
                flex: 5,
                xtype: "actioncolumn",
                items: [{
                    icon: "img/55.png",
                    text: "查看明细"
                }, {
                    icon: "img/52.png",
                    dataIndex: "角色"
                }, {
                    icon: "img/54.png",
                    dataIndex: "数据权限"
                }]
            }],
            bbar: new Ext.PagingToolbar({
                id:"bbar",
                 store: store,
                displayInfo: true,
                displayMsg: "当前显示第{0}到{1}条记录,共有{1}条记录",
                emptyMsg: "无记录"
            }),
            store: store
        });
        this.callParent(arguments);
    }
    //this.callParent(arguments)

});