<!doctype html>
<html>
<my:topic id="${params.id}" var="topic">
    <head>
        <meta name="layout" content="home"/>
        <title>${topic.title}-社区1221</title>
        <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    </head>

    <body>

    <g:render template="../template/nav"></g:render>

    <div class="layui-container">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md8 content detail">
                <div class="fly-panel detail-box">
                    <h1>${topic.title}</h1>

                    <div class="fly-detail-info">
                        <g:if test="${topic.verify == false}">
                            <span class="layui-badge">审核中</span>
                        </g:if>

                        <span class="layui-badge layui-bg-green fly-detail-column">${topic.column.columnName}</span>

                        <g:if test="${topic.close == true}">
                            <span class="layui-badge" style="background-color: #5FB878;">已结</span>
                        </g:if>
                        <g:else>
                            <span class="layui-badge" style="background-color: #999;">未结</span>
                        </g:else>
                        <g:if test="${topic.top == true}">
                            <span class="layui-badge layui-bg-black">置顶</span>
                        </g:if>
                        <g:if test="${topic.star == true}">
                            <span class="layui-badge layui-bg-red">精帖</span>
                        </g:if>
                        <my:isAdmin>
                            <div class="fly-admin-box" data-id="123">
                                <span class="layui-btn layui-btn-xs jie-admin" type="del">删除</span>
                                <g:if test="${topic.top == true}">
                                    <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0"
                                          style="background-color:#ccc;">取消置顶</span>
                                </g:if>
                                <g:else>
                                    <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick"
                                          rank="1">置顶</span>
                                </g:else>
                                <g:if test="${topic.star == true}">
                                    <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="0"
                                          style="background-color:#ccc;">取消加精</span>
                                </g:if>
                                <g:else>
                                    <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status"
                                          rank="1">加精</span>
                                </g:else>
                            </div>
                        </my:isAdmin>
                        <span class="fly-list-nums">
                            <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i>${topic.replyCount}</a>
                            <i class="iconfont" title="人气">&#xe60b;</i> ${topic.readCount}
                        </span>
                    </div>

                    <div class="detail-about">
                        <a class="fly-avatar" href="../user/home.html">
                            <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"
                                 alt="贤心">
                        </a>

                        <div class="fly-detail-user">
                            <a href="../user/home.html" class="fly-link">
                                <cite>${topic.user.username}</cite>
                                <i class="iconfont icon-renzheng" title="认证信息：{{ rows.user.approve }}"></i>
                                <i class="layui-badge fly-badge-vip">VIP3</i>
                            </a>
                            <span><my:smileDate date="${topic.releaseDate}"></my:smileDate></span>
                        </div>

                        <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                            <g:if test="${topic.feiwen > 0}">
                                <span style="padding-right: 10px; color: #FF7200">悬赏：${topic.feiwen}飞吻</span>
                            </g:if>
                            <my:hasAuthority id="${topic.user.id}">
                                <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="add.html">编辑此贴</a>
                                </span>
                            </my:hasAuthority>
                        </div>
                    </div>

                    <div class="detail-body photos">
                        ${topic.topicBody.body}
                    </div>
                </div>

                <div class="fly-panel detail-box" id="flyReply">
                    <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                        <legend>回帖</legend>
                    </fieldset>

                    <ul class="jieda" id="jieda">
                    %{--
                     <g:each in="${topic.topicReply}" var="reply">
                         <li class="fly-none">${reply.body}</li>
                     </g:each>
                    --}%
                        <my:replyGood id="${topic.id}" var="reply">
                            <li data-id="111" class="jieda-daan">
                                <a name="item-1111111111"></a>

                                <div class="detail-about detail-about-reply">
                                    <a class="fly-avatar" href="">
                                        <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"
                                             alt=" ">
                                    </a>

                                    <div class="fly-detail-user">
                                        <a href="" class="fly-link">
                                            <cite>${reply.user.username}</cite>
                                            <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                            <i class="layui-badge fly-badge-vip">VIP3</i>
                                        </a>
                                        <g:if test="${reply.user.id == topic.user.id}">
                                            <span>(楼主)</span>
                                        </g:if>
                                        <g:if test="${reply.user.role == tv.xza.fly.Roles.admin}">
                                            <span style="color:#5FB878">(管理员)</span>
                                        </g:if>
                                    <!--
                            <span style="color:#FF9E3F">（社区之光）</span>
                            <span style="color:#999">（该号已被封）</span>
                            -->
                                    </div>

                                    <div class="detail-hits">
                                        <span><my:smileDate date="${reply.releaseDate}"></my:smileDate></span>
                                    </div>

                                    <i class="iconfont icon-caina" title="最佳答案"></i>
                                </div>

                                <div class="detail-body jieda-body photos">
                                    ${reply.body}
                                </div>

                                <div class="jieda-reply">
                                    <span class="jieda-zan zanok" type="zan">
                                        <i class="iconfont icon-zan"></i>
                                        <em>${reply.praise}</em>
                                    </span>
                                    <span type="reply">
                                        <i class="iconfont icon-svgmoban53"></i>
                                        回复
                                    </span>
                                    <div class="jieda-admin">
                                        <my:isAdmin>
                                            <span type="edit">编辑</span>
                                            <span type="del">删除</span>
                                        </my:isAdmin>
                                    </div>
                                </div>
                            </li>
                        </my:replyGood>
                        <my:replyOther id="${topic.id}" var="reply" max="5" offset="0">
                            <li data-id="111" class="jieda-daan">
                                <a name="item-1111111111"></a>

                                <div class="detail-about detail-about-reply">
                                    <a class="fly-avatar" href="">
                                        <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"
                                             alt=" ">
                                    </a>

                                    <div class="fly-detail-user">
                                        <a href="" class="fly-link">
                                            <cite>${topic.user.username}</cite>
                                            <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                            <i class="layui-badge fly-badge-vip">VIP3</i>
                                        </a>
                                        <g:if test="${reply.user.id == topic.user.id}">
                                            <span>(楼主)</span>
                                        </g:if>
                                        <g:if test="${reply.user.role == tv.xza.fly.Roles.admin}">
                                            <span style="color:#5FB878">(管理员)</span>
                                        </g:if>
                                    <!--
                            <span style="color:#FF9E3F">（社区之光）</span>
                            <span style="color:#999">（该号已被封）</span>
                            -->
                                    </div>

                                    <div class="detail-hits">
                                        <span><my:smileDate date="${reply.releaseDate}"></my:smileDate></span>
                                    </div>

                                </div>

                                <div class="detail-body jieda-body photos">
                                    ${reply.body}
                                </div>

                                <div class="jieda-reply">
                                    <span class="jieda-zan zanok" type="zan">
                                        <i class="iconfont icon-zan"></i>
                                        <em>${reply.praise}</em>
                                    </span>
                                    <span type="reply">
                                        <i class="iconfont icon-svgmoban53"></i>
                                        回复
                                    </span>

                                    <div class="jieda-admin">
                                        <my:hasAuthority id="${reply.user.id}">
                                            <span type="edit">编辑</span>
                                            <span type="del">删除</span>
                                        </my:hasAuthority>
                                        <my:hasAuthority id="${topic.user.id}">
                                            <span class="jieda-accept" type="accept">采纳</span>
                                        </my:hasAuthority>
                                    </div>

                                </div>
                            </li>
                        </my:replyOther>
                    <!-- 无数据时 -->
                        <g:if test="${topic.replyCount == 0}">
                            <li class="fly-none">消灭零回复</li>
                        </g:if>
                    </ul>

                    <g:render template="../template/replyForm" var="${topic}"></g:render>

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
</my:topic>
</html>
