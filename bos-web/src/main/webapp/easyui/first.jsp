<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>tabs</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        var add = function(name,url){
            var e = $("#mytabs").tabs("exists",name);
            if(e){
                $("#mytabs").tabs("select",name);
            }else{
                $("#mytabs").tabs("add",{
                    title:name,
                    closable:true,
                    content:'<iframe frameborder="0" height="100%" width="100%" src="'+url+'"></iframe>'
                });
            }
        };
        $(function(){
            /*动态添加选项卡*/
            $("#but1").click(add);

            /*标准json数据 构造ztree*/
            var setting = {
                data:{
                 simpleData:{
                     /*使用简单json数据构造ztree*/
                     enable:true
                 }
                },
                callback:{
                    /*为ztree节点绑定单击事件*/
                    onClick:function(event, treeId, treeNode){
                        if(treeNode.page != undefined){
                            add(treeNode.name,treeNode.page);
                        }
                    }
                }
            };
            var url = "${pageContext.request.contextPath}/json/menu.json";
            $.post(url,function(data){
                /*调用API初始化ztree*/
                $.fn.zTree.init($("#ztree1"), setting, data);
            },'json');

            //messager
            /*$.messager.alert("标题","内容","info");*/
            /*$.messager.confirm("提示信息","你确定要删除当前内容吗？",function(r){
                alert(r);
            });*/
            $.messager.show({
                title:'欢迎信息',
                msg:'欢迎admin登录系统',
                timeout:'5000',
                showType:'slide'
            });


        });


    </script>
</head>
<body class="easyui-layout">
<!-- 使用div元素描述每个区域 -->
<div title="BOS管理系统" style="height: 100px" data-options="region:'north'">
    <%--制作菜单--%>
    <a data-options="menu:'#mn'" class="easyui-menubutton">控制面板</a>
    <%--使用div制作元素下拉菜单--%>
        <div id="mn">
            <div>修改密码</div>
            <div>练习管理员</div>
            <div class="menu-sep"></div>
            <div>退出系统</div>
        </div>
</div>
<div title="系统菜单" style="width: 200px" data-options="region:'west'">
    <!--制作accordion折叠面板-->
    <div class="easyui-accordion" data-options="fit:true">
        <div title="面板一">
            <%--动态添加选项卡--%>
            <a id="but1" class="easyui-linkbutton">添加一个选项卡</a>
        </div>
        <div title="面板二">
            <ul id="ztree1" class="ztree"></ul>
        </div>
        <div title="面板三">33333</div>
    </div>
</div>
<div data-options="region:'center'">
    <!--制作tabs选项卡-->
    <div id="mytabs" class="easyui-tabs" data-options="fit:true">
        <div title="面板一" data-options="closable:true">11111</div>
        <div title="面板二">22222</div>
        <div title="面板三">33333</div>
    </div></div>
<div style="width: 100px" data-options="region:'east'">东部区域</div>
<div style="height: 50px" data-options="region:'south'">南部区域</div>



</body>
</html>
