<div class="fly-panel-title fly-filter">
    <a href="" class="layui-this">综合</a>
    <span class="fly-mid"></span>
    <a href="">未结</a>
    <span class="fly-mid"></span>
    <a href="">已结</a>
    <span class="fly-mid"></span>
    <a href="">精华</a>
    <span class="fly-filter-right layui-hide-xs">
        <a href="" class="layui-this">按最新</a>
        <span class="fly-mid"></span>
        <a href="">按热议</a>
    </span>
</div>


<ul class="fly-list">

    <my:firstTopic var="topic" max="15">
        <my:user id="${topic.user.id}" var="user">
            <li>
                <a href="${createLink(controller: 'user', action: 'show',params: [id:topic.user.id])}" class="fly-avatar">
                    <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="贤心">
                </a>
                <h2>
                    <a class="layui-badge">${topic.column.columnName}</a>
                    <a href="${createLink(controller: 'html', action: 'index',params: [id:topic.id])}">${topic.title}</a>
                </h2>
                <div class="fly-list-info">
                    <a href="user/home.html" link>
                        <cite>${user.username}</cite>
                        <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                        <i class="layui-badge fly-badge-vip">VIP3</i>
                    </a>
                    <span>
                        <my:smileDate date="${topic.releaseDate}"></my:smileDate>
                    </span>
                    <g:if test="${topic.feiwen>0}">
                        <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> ${topic.feiwen}</span>
                    </g:if>
                    <g:if test="${topic.close==true}">
                        <span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
                    </g:if>
                    <span class="fly-list-nums">
                        <i class="iconfont icon-pinglun1" title="回答"></i> ${topic.replyCount}
                    </span>
                </div>
                <div class="fly-list-badge">
                    <g:if test="${topic.top==true}">
                        <span class="layui-badge layui-bg-black">置顶</span>
                    </g:if>
                    <g:if test="${topic.star==true}">
                        <span class="layui-badge layui-bg-red">精帖</span>
                    </g:if>
                </div>
            </li>
        </my:user>
    </my:firstTopic>
</ul>
<div style="text-align: center">
    <div class="laypage-main">
        <a href="${createLink(controller: 'list', action: 'index',params: [id:1])}" class="laypage-next">更多求解</a>
    </div>
</div>