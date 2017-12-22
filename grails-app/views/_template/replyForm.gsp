<div class="layui-form layui-form-pane">
    <form action="${createLink(controller: 'post', action: 'reply',params: [id:topic.id])}" method="post">
        <div class="layui-form-item layui-form-text">
            <input type="hidden" name="post" value="${createLink(controller: 'html', action: 'index',params: [id:topic.id])}"  />
            <a name="comment"></a>
            <div class="layui-input-block">
                <textarea id="L_content" name="body" required lay-verify="required"
                          placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn" lay-filter="*" >提交回复</button>
        </div>
    </form>
</div>