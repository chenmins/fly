<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="http://app.chenmin.org/res/layui/css/layui.css">
    <link rel="stylesheet" href="http://app.chenmin.org/res/css/global.css">
    <g:layoutHead/>
</head>
<body>

<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <a class="fly-logo" href="/">
            <img src="http://app.chenmin.org/res/images/logo.png" alt="layui">
        </a>
        <ul class="layui-nav fly-nav layui-hide-xs">
            <li class="layui-nav-item layui-this">
                <a href="/"><i class="iconfont icon-jiaoliu"></i>交流</a>
            </li>
            <li class="layui-nav-item">
                <a href="case/case.html"><i class="iconfont icon-iconmingxinganli"></i>案例</a>
            </li>
            <li class="layui-nav-item">
                <a href="http://www.layui.com/" target="_blank"><i class="iconfont icon-ui"></i>框架</a>
            </li>
        </ul>

        <ul class="layui-nav fly-nav-user">

            <!-- 未登入的状态 -->
            <li class="layui-nav-item">
                <a class="iconfont icon-touxiang layui-hide-xs" href="${createLink(controller: 'user', action: 'login')}"></a>
            </li>
            <li class="layui-nav-item">
                <a href="${createLink(controller: 'user', action: 'login')}">登入</a>
            </li>
            <li class="layui-nav-item">
                <a href="${createLink(controller: 'user', action: 'reg')}">注册</a>
            </li>
            <li class="layui-nav-item layui-hide-xs">
                <a href="/app/qq/" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" title="QQ登入" class="iconfont icon-qq"></a>
            </li>
            <li class="layui-nav-item layui-hide-xs">
                <a href="/app/weibo/" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" title="微博登入" class="iconfont icon-weibo"></a>
            </li>

            <!-- 登入后的状态 -->
            <!--
      <li class="layui-nav-item">
        <a class="fly-nav-avatar" href="javascript:;">
          <cite class="layui-hide-xs">贤心</cite>
          <i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：layui 作者"></i>
          <i class="layui-badge fly-badge-vip layui-hide-xs">VIP3</i>
          <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg">
        </a>
        <dl class="layui-nav-child">
          <dd><a href="user/set.html"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
          <dd><a href="user/message.html"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a></dd>
          <dd><a href="user/home.html"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a></dd>
          <hr style="margin: 5px 0;">
          <dd><a href="/user/logout/" style="text-align: center;">退出</a></dd>
        </dl>
      </li>
      -->
        </ul>
    </div>
</div>

<g:layoutBody/>

<div class="fly-footer">
    <p><a href="http://fly.layui.com/" target="_blank">Fly社区</a> 2017 &copy; <a href="http://www.layui.com/" target="_blank">layui.com 出品</a></p>
    <p>
        <a href="http://fly.layui.com/jie/3147/" target="_blank">付费计划</a>
        <a href="http://www.layui.com/template/fly/" target="_blank">获取Fly社区模版</a>
        <a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>
    </p>
</div>

<script src="http://app.chenmin.org/res/layui/layui.js"></script>
<script>
    layui.cache.page = '';
    layui.cache.user = {
        username: '游客'
        ,uid: -1
        ,avatar: 'http://app.chenmin.org/res/images/avatar/00.jpg'
        ,experience: 83
        ,sex: '男'
    };
    layui.config({
        version: "3.0.0"
        ,base: 'http://app.chenmin.org/res/mods/' //这里实际使用时，建议改成绝对路径
    }).extend({
        fly: 'index'
    }).use('fly');
</script>

</body>
</html>