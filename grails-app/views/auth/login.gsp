<!doctype html>
<html>
<head>
    <meta name="layout" content="home"/>
    <title>登入</title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>

<div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
                <li class="layui-this">登入</li>
                <li><a href="reg">注册</a></li>
            </ul>
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">
<g:form action="check" method="POST">
                            <div class="layui-form-item">
                                <label for="L_email" class="layui-form-label">邮箱</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_email" name="email" required lay-verify="required" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_pass" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_pass" name="password" required lay-verify="required" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_vercode" class="layui-form-label">人类验证</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_vercode" name="vercode" required lay-verify="required" placeholder="请填写后面的字符" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid">
                                    <span style="color: #c00;">
                                        <img src="${createLink(controller: 'simpleCaptcha', action: 'captcha')}"/>
                                    </span>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <g:if test="${flash.real!=null}">
                                    <input type="hidden" name="real" value="${flash.real}" />
                                </g:if>
                                <button class="layui-btn" lay-filter="*">立即登录</button>
                                <span style="padding-left:20px;">
                                    <a href="${createLink(controller: 'auth', action: 'forget')}">忘记密码？</a>
                                </span>
                            </div>
    %{--
                            <div class="layui-form-item fly-form-app">
                                <span>或者使用社交账号登入</span>
                                <a href="" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-qq" title="QQ登入"></a>
                                <a href="" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-weibo" title="微博登入"></a>
                            </div>
    --}%
</g:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<g:if test="${flash.message!=null}" >
    <script type="text/javascript">
        alert("${flash.message}")
    </script>
</g:if>
</body>
</html>
