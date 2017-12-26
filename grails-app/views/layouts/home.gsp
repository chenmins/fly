<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        <g:layoutTitle default="Grails"/> - <g:meta name="fly.site" ></g:meta>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="//ohlo5qp57.qnssl.com/res/layui/css/layui.css">
    <link rel="stylesheet" href="//ohlo5qp57.qnssl.com/res/css/global.css">
    <g:layoutHead/>
</head>
<body>

<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <g:render template="../template/header"></g:render>
        <g:render template="../template/auth"></g:render>
    </div>
</div>

<g:layoutBody/>

<g:render template="../template/footer"></g:render>

<script src="//ohlo5qp57.qnssl.com/res/layui/layui.js"></script>
<script>
    layui.cache.page = '';
    layui.cache.user = {
        username: '游客'
        ,uid: -1
        ,avatar: '//ohlo5qp57.qnssl.com/res/images/avatar/00.jpg'
        ,experience: 83
        ,sex: '男'
    };
    layui.config({
        version: "3.0.0"
        ,base: '//ohlo5qp57.qnssl.com/res/mods/' //这里实际使用时，建议改成绝对路径
    }).extend({
        fly: 'index'
    }).use('fly');
</script>

</body>
</html>