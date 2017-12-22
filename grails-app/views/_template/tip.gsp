<!doctype html>
<html>
<head>
    <meta name="layout" content="home"/>
    <title>${flash.title}</title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
<div class="layui-container fly-marginTop">
    <div class="fly-panel">
        <div class="fly-none">
            <h2><i class="iconfont icon-tishilian"></i></h2>
            <p>${flash.message}</p>
        </div>
    </div>
</div>
<g:if test="${flash.back!=null&&flash.back==true}" >
    <script type="text/javascript">
        //延时3秒
        setTimeout("window.history.back();",3000);
    </script>
</g:if>
<g:if test="${flash.redirect!=null}" >
    <script type="text/javascript">
        //延时3秒
        setTimeout('window.location.href="${flash.redirect}";',3000);
    </script>
</g:if>
</body>
</html>
