<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/28
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="<%=basePath%>resouce/css/index.css">
</head>
<body>
<div class="wrap">
    <img class="logo" src="<%=basePath%>resouce/img/top.jpg" alt="">
    <%--起始选择区域开始--%>
    <div id="style" class="startAre">
        <h1 class="title">请选择参数提供方式:</h1>
        <button id="online" class="btn">在线配置</button>
        <button id="upload" class="btn">本地配置文件</button>
    </div>
    <%--起始选择区域结束--%>
    <%--上传配置文件区域开始--%>
    <div id="form1" class="fileAre">
        <div class="mb20">
            <label for="file" class="btn fileLabel">点击选择文件</label>
            <input id="file" name="configFile" type="file" accept=".properties">
        </div>
        <button id="uploadButton" class="btn" type="button">提交</button>
        <div class="tip2">
            <p>说明：</p>
            <p>1.目前只支持.properties文件</p>
            <p>2.格式如下：</p>
            <p>nodeNum:5</p>
            <p>TopyType:Mesh</p>
            <p>outputType:JSON</p>
            <p>traversalStarts:node0,node1,node2</p>
        </div>
    </div>
    <%--上传配置文件区域结束--%>
    <%--在线配置区域开始--%>
    <div id="form" class="onlineAre">
        <div>
            <p>第一步：输入节点个数</p>
            <input id="nodeNum" style="" type="text" name="nodeNum" value="5">
        </div>
        <div>
            <p>第二步：选择拓扑类型</p>
            <select id="TopyType" name="TopyType">
                <option selected="selected" value="Chain">Chain</option>
                <option value="Ring">Ring</option>
                <option value="Mesh">Mesh</option>
            </select>
        </div>
        <div>
            <p>第三步：选择输出的数据类型</p>
            <select id="outputType" name="outputType">
                <option selected="selected" value="JSON">JSON</option>
                <option value="XML" DISABLED>XML</option>
            </select>
        </div>
        <div>
            <p>第四步：选择起始节点（支持多选）</p>
            <div id="start">
                <input type="checkbox" class="checkedStart1" checked="checked" value="node0" name="start">node0
                <input type="checkbox" class="checkedStart"  value="node1" name="start">node1
                <input type="checkbox" class="checkedStart" value="node2" name="start">node2
                <input type="checkbox" class="checkedStart"  value="node3" name="start">node3
                <input type="checkbox"class="checkedStart"  value="node4" name="start">node4
            </div>
        </div>
        <button id="submit" type="button" class="btn">创建</button>
    </div>
    <%--在线配置区域结束--%>
    <%--结果显示框开始--%>
    <div class="resultAre">
        <button id="download" class="btn" type="button">下载到本地</button>
        <p class="tip">提示：点击蓝色节点或者减号可收缩</p>
        <pre id="result"></pre>
    </div>
    <%--结果显示框结束--%>
</div>
<script>
    var onlineUrl = "<%=basePath%>topologys";
    var uploadFileUrl = "<%=basePath%>topologys2";
</script>
<script src="<%=basePath%>resouce/js/jquery.min.js"></script>
<script src="<%=basePath%>resouce/js/ajaxfileupload.js"></script>
<script src="<%=basePath%>resouce/js/jquery.jsonview.js"></script>
<script src="<%=basePath%>resouce/js/index.js"></script>
</body>
</html>
