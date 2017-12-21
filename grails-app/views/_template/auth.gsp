<ul class="layui-nav fly-nav-user">
    <my:notLogin>
        <!-- 未登入的状态 -->
        <li class="layui-nav-item">
            <a class="iconfont icon-touxiang layui-hide-xs" href="${createLink(controller: 'auth', action: 'login')}"></a>
        </li>
        <li class="layui-nav-item">
            <a href="${createLink(controller: 'auth', action: 'login')}">登入</a>
        </li>
        <li class="layui-nav-item">
            <a href="${createLink(controller: 'auth', action: 'reg')}">注册</a>
        </li>
    %{--
         <li class="layui-nav-item layui-hide-xs">
             <a href="/app/qq/" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" title="QQ登入" class="iconfont icon-qq"></a>
         </li>
         <li class="layui-nav-item layui-hide-xs">
             <a href="/app/weibo/" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" title="微博登入" class="iconfont icon-weibo"></a>
         </li>
         --}%
    </my:notLogin>
    <my:isLogin>
        <!-- 登入后的状态 -->
        <li class="layui-nav-item">
            <a class="fly-nav-avatar" href="javascript:;">
                <cite class="layui-hide-xs">${session.user.username}</cite>
                <i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：layui 作者"></i>
                <i class="layui-badge fly-badge-vip layui-hide-xs">VIP3</i>
                <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg">
            </a>
            <dl class="layui-nav-child">
                <dd><a href="${createLink(controller: 'self', action: 'set')}"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
                %{--
                <dd><a href="user/message.html"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a></dd>
                  <dd><a href="user/home.html"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a></dd>
                  --}%
                <hr style="margin: 5px 0;">
                <my:isAdmin>
                    <dd><a href="${createLink(controller: 'column', action: 'index')}">
                        <i class="layui-icon" style="top: 4px;">&#xe614;</i>
                        标签们</a>
                    </dd>
                    <dd><a href="${createLink(controller: 'user', action: 'index')}">
                        <i class="layui-icon" style="top: 4px;">&#xe614;</i>会员们</a>
                    </dd>
                </my:isAdmin>
                <dd><a href="${createLink(controller: 'auth', action: 'logout')}" style="text-align: center;">退出</a></dd>
            </dl>
        </li>
    </my:isLogin>
</ul>