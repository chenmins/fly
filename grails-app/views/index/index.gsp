<!doctype html>
<html>
<head>
    <meta name="layout" content="home"/>
    <title>主页面</title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>

<body>

<g:render template="../template/nav"></g:render>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">

            <g:render template="../template/top"></g:render>

            <div class="fly-panel" style="margin-bottom: 0;">

                <g:render template="../template/first"></g:render>

            </div>
        </div>

        <div class="layui-col-md4">

            <g:render template="../template/hot"></g:render>

            <g:render template="../template/sign"></g:render>

            <g:render template="../template/replyWeek"></g:render>

            <g:render template="../template/hotWeek"></g:render>

            <g:render template="../template/ads"></g:render>

            <g:render template="../template/link"></g:render>

        </div>
    </div>
</div>

</body>
</html>
