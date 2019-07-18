
Ext.onReady(function () {



    Ext.create(Ext.container.Viewport,{
        layout:"border",
        items:[{
            region:"north",
            html:"<b style='font-size: 40px'>环境设施管理系统</b><span id='admin' style='float: right;font-size: 15px;'>欢迎您 admin</span>",
            height:80
        },{
                region:"west",
                title:"菜单栏",
                width:250,
                height:600,
                // renderTo:"zuo",
                collapsible:true,
                layout:"accordion",
            items:[{
                    hidden:true
            },{
                    icon:"../img/43.png",
                    title:"视屏监控",
                    hideCollapseTool:true,
                    items:[new Ext.tree.Panel({
                        listeners:{
                            itemclick:function (tree,cor) {
                                var id=cor.raw;
                                var tabpanel=Ext.getCmp("tabpanel");
                                if (Ext.getCmp(id.id)) {
                                    tabpanel.setActiveTab(Ext.getCmp(id.id));
                                }else {
                                    var t=tabpanel.add({
                                        title:id.text,
                                        closable:true,
                                        id:id.id,
                                        html:id.text+"页面",
                                        // items:[new mf.three()]
                                    });
                                    tabpanel.setActiveTab(t);
                                }



                            }
                        },
                        rootVisible: false,
                        root:{
                            text:"视屏监控",
                            expand:true,
                            children:[{
                                icon:"../img/1.png",
                                text:"视屏监控配置",
                                id:"1",
                                leaf:true
                            },{
                                icon:"../img/3.png",
                                text:"视屏监控实播",
                                id:"2",
                                leaf:true
                            },{
                                icon:"../img/7.png",
                                text:"录像回放",
                                id:"3",
                                leaf:true
                            }]
                        }
                    })]
                },{
                    icon:"../img/44.png",
                    title:"巡维管理",
                    hideCollapseTool:true,
                    items:[new Ext.tree.Panel({
                        listeners:{
                            itemclick:function (tree,cor) {
                                var id = cor.raw;
                                var tabpanel = Ext.getCmp("tabpanel");

                                if (Ext.getCmp(id.id)) {
                                    tabpanel.setActiveTab(Ext.getCmp(id.id));
                                } else {
                                    switch (id.id) {
                                        case "5":
                                            var t=tabpanel.add({
                                                title:id.text,
                                                closable:true,
                                                id:id.id,
                                                layout:"fit",
                                                items:[new xw.kqgz()]

                                            });
                                            break;
                                        case "6":
                                            var t=tabpanel.add({
                                                title:id.text,
                                                closable:true,
                                                id:id.id,
                                                layout:"fit",
                                                items:[new xw.xwkq()]
                                            });
                                            break;
                                        case "7":
                                            var t=tabpanel.add({
                                                title:id.text,
                                                closable:true,
                                                id:id.id,
                                                html:id.text+"页面"
                                            });
                                            break;
                                        case "8":
                                            var t=tabpanel.add({
                                                title:id.text,
                                                closable:true,
                                                id:id.id,
                                                html:id.text+"页面"
                                            });
                                            break;
                                    }
                                    tabpanel.setActiveTab(t);
                                }
                            }
                        },
                        rootVisible: false,
                        root:{
                            text:"巡维管理",
                            children:[
                            //     {
                            //     icon:"../img/13.png",
                            //     text:"巡维计划",
                            //     id:"4",
                            //     leaf:true
                            // },
                                {
                                icon:"../img/2.png",
                                text:"考勤规则设置",
                                id:"5",
                                leaf:true
                            },{
                                icon:"../img/21.png",
                                text:"巡维考勤",
                                id:"6",
                                leaf:true
                            },{
                                icon:"../img/12.png",
                                text:"巡检工作",
                                id:"7",
                                leaf:true
                            },{
                                icon:"../img/17.png",
                                text:"工单管理",
                                id:"8",
                                leaf:true
                            }]
                        }
                    })]
                },{
                    icon:"../img/45.png",
                    title:"运行报表",
                    hideCollapseTool:true,
                    items:[new Ext.tree.Panel({
                        listeners:{
                            itemclick:function (tree,cor) {
                                var id=cor.raw;
                                var tabpanel=Ext.getCmp("tabpanel");
                                if(id.id=="yxbb"||id.id=="dlsbbb"){
                                    return;
                                }else{
                                    if (Ext.getCmp(id.id)) {
                                        tabpanel.setActiveTab(Ext.getCmp(id.id));
                                    }else {
                                        var t=tabpanel.add({
                                            title:id.text,
                                            closable:true,
                                            id:id.id,
                                            html:id.text+"页面",
                                            // items:[new mf.three()]
                                        });
                                        tabpanel.setActiveTab(t);
                                    }
                                }

                            }
                        },
                        rootVisible: false,
                        root:{
                            text:"运行报表",

                            children:[{
                                icon:"../img/31.png",
                                text:"水流量报表",
                                id:"yxbb",
                                children:[{
                                    icon:"../img/31.png",
                                    text:"单站点水流量查询表",
                                    id:"9",
                                    leaf:true
                                },{
                                    icon:"../img/31.png",
                                    text:"水流量智能分析表",
                                    id:"10",
                                    leaf:true
                                },{
                                    icon:"../img/31.png",
                                    text:"村级多站点累计流量报表",
                                    id:"11",
                                    leaf:true
                                },{
                                    icon:"../img/31.png",
                                    text:"镇级各村累计流量报表",
                                    id:"12",
                                    leaf:true
                                },{
                                    icon:"../img/31.png",
                                    text:"县级各镇累计流量报表",
                                    id:"13",
                                    leaf:true
                                }]
                            },{
                                text:"动力设备报表",
                                id:"dlsbbb",
                                icon:"../img/32.png",
                                children:[{
                                    icon:"../img/32.png",
                                    text:"风机开启监测",
                                    id:"14",
                                    leaf:true
                                },{
                                    icon:"../img/32.png",
                                    text:"水泵开启监测",
                                    id:"15",
                                    leaf:true
                                }]
                            },{
                                icon:"../img/30.png",
                                text:"巡检评分汇总表",
                                id:"16",
                                leaf:true
                            }]
                        }
                    })]
                },{
                    icon:"../img/46.png",
                    title:"告警管理",
                    hideCollapseTool:true,
                    items:[new Ext.tree.Panel({
                        listeners:{
                            itemclick:function (tree,cor) {
                                var id=cor.raw;
                                var tabpanel=Ext.getCmp("tabpanel");
                                if (Ext.getCmp(id.id)) {
                                    tabpanel.setActiveTab(Ext.getCmp(id.id));
                                }else {
                                    var t=tabpanel.add({
                                        title:id.text,
                                        closable:true,
                                        id:id.id,
                                        html:id.text+"页面",
                                        // items:[new mf.three()]
                                    });
                                    tabpanel.setActiveTab(t);
                                }
                            }
                        },
                        rootVisible: false,
                        root:{
                            text:"告警管理",
                            expand:true,
                            children:[{
                                icon:"../img/33.png",
                                text:"告警列表",
                                id:"17",
                                leaf:true
                            },{
                                icon:"../img/34.png",
                                text:"异常数据汇总表",
                                id:"18",
                                leaf:true
                            }]
                        }
                    })]
                },{
                    icon:"../img/47.png",
                    title:"基础信息管理",
                    hideCollapseTool:true,
                    items:[new Ext.tree.Panel({
                        listeners:{
                            itemclick:function (tree,cor) {
                                var id=cor.raw;
                                var tabpanel=Ext.getCmp("tabpanel");
                                if (Ext.getCmp(id.id)) {
                                    tabpanel.setActiveTab(Ext.getCmp(id.id));
                                }else {
                                    var t=tabpanel.add({
                                        title:id.text,
                                        closable:true,
                                        id:id.id,
                                        html:id.text+"页面",
                                        // items:[new mf.three()]
                                    });
                                    tabpanel.setActiveTab(t);
                                }
                            }
                        },
                        rootVisible: false,
                        root:{
                            text:"基础信息管理",
                            expand:true,
                            children:[{
                                icon:"../img/42.png",
                                text:"站点管理",
                                id:"20",
                                leaf:true
                            },{
                                icon:"../img/37.png",
                                text:"人员管理",
                                id:"21",
                                leaf:true
                            },{
                                icon:"../img/40.png",
                                text:"流量报警设置",
                                id:"22",
                                leaf:true
                            }]
                        }
                    })]
                },{
                    icon:"../img/48.png",
                    title:"系统管理",
                    hideCollapseTool:true,
                    items:[new Ext.tree.Panel({
                        listeners:{
                            itemclick:function (tree,cor) {
                                var id=cor.raw;
                                var tabpanel=Ext.getCmp("tabpanel");

                                if (Ext.getCmp(id.id)) {
                                    tabpanel.setActiveTab(Ext.getCmp(id.id));
                                }else {
                                    switch (id.id) {
                                        case "23":
                                            var t=tabpanel.add({
                                                title:id.text,
                                                closable:true,
                                                id:id.id,
                                                layout:"fit",
                                                items:[new mf.bm()]
                                            });
                                            break;
                                        case "user":
                                            var t=tabpanel.add({
                                                title:id.text,
                                                closable:true,
                                                id:id.id,
                                                layout:"fit",
                                                items:[new mf.three()]
                                            });
                                            break;
                                        case "25":
                                            var t=tabpanel.add({
                                                title:id.text,
                                                closable:true,
                                                id:id.id,
                                                layout:"fit",
                                                items:[new mf.js()]
                                            });
                                            break;
                                        case "26":
                                            var t=tabpanel.add({
                                                title:id.text,
                                                closable:true,
                                                id:id.id,
                                                html:id.text+"页面"
                                            });
                                            break;
                                    }
                                    tabpanel.setActiveTab(t);
                                }
                            }
                        },
                        rootVisible: false,
                        root:{
                            text:"系统管理",
                            expand:true,
                            children:[{
                                icon:"../img/39.png",
                                text:"部门管理",
                                id:"23",
                                leaf:true
                            },{
                                icon:"../img/35.png",
                                text:"用户管理",
                                id:"user",
                                leaf:true
                            },{
                                icon:"../img/38.png",
                                text:"角色管理",
                                id:"25",
                                leaf:true
                            },{
                                icon:"../img/41.png",
                                text:"日志管理",
                                id:"26",
                                leaf:true
                            }]
                        }
                    })]
                }]
        },{
            region:"center",
            xtype:"tabpanel",
            id:"tabpanel",
            items:[{
                icon:"../img/19.png",
                title:"主页",
                html:"<iframe src='html/map.html' width='100%' height='100%'></iframe>"
                // closable:true
            }]
        },{
            region:"south",
            height:30,
            tbar:["管理系统","->","Copyright © 2019 pmf,myf,yjw"]
        }]
    });



})