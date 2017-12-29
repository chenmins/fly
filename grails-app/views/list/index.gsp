<!doctype html>
<html>
    <head>
        <meta name="layout" content="home"/>
        <title>111</title>
        <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    </head>

    <body>

    <g:render template="../template/nav"></g:render>



    <div class="layui-container">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md8">
                <div class="fly-panel" style="margin-bottom: 0;">

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
                        <g:each in="${list}" var="topic">
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
                                    <cite>${topic.user.username}</cite>
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
                        </g:each>
                    </ul>
                    <g:if test="${list==null}">
                        <div class="fly-none">没有相关数据</div>
                    </g:if>

                    <div style="text-align: center">
                        <div class="laypage-main">
                            <g:paginate total="${count ?: 0}" />
                            <span class="laypage-curr">1</span><a href="/jie/page/2/">2</a>

                            <a href="/jie/page/148/" class="laypage-last" title="尾页">尾页</a><a href="/jie/page/2/" class="laypage-next">下一页</a>
                        </div>
                    </div>

                </div>
            </div>
            <div class="layui-col-md4">

                <g:render template="../template/hotWeek"></g:render>

                <g:render template="../template/ads"></g:render>

                <g:render template="../template/link"></g:render>

            </div>
        </div>
    </div>


    </body>
</html>
