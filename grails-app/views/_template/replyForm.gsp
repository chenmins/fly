<div class="layui-form layui-form-pane">
    <form action="/jie/reply/" method="post">
        <div class="layui-form-item layui-form-text">
            <a name="comment"></a>
            <div class="layui-input-block">
                <textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <input type="hidden" name="jid" value="123">
            <button class="layui-btn" lay-filter="*" lay-submit>提交回复</button>
        </div>
    </form>
</div>