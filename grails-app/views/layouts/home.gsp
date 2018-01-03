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
    <script src="//apps.bdimg.com/libs/zepto/1.1.4/zepto.min.js"></script>
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

    $(function(){
        //分页测试
        $(".prevLink").each(function(){
            $(this).addClass("laypage-prev");
        });
        $(".currentStep").each(function(){
            $(this).addClass("laypage-curr");
        });
        $(".nextLink").each(function(){
            $(this).addClass('laypage-next');
        });
    });

</script>
<script>
    (function(){
        var bp = document.createElement('script');
        var curProtocol = window.location.protocol.split(':')[0];
        if (curProtocol === 'https') {
            bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
        }
        else {
            bp.src = 'http://push.zhanzhang.baidu.com/push.js';
        }
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(bp, s);
    })();
</script>
</body>
</html>