<div class="fly-panel">
    <h3 class="fly-panel-title">温馨通道</h3>
    <ul class="fly-panel-main fly-list-static">
<my:hotTopic var="topic" max="5">
    <li>
        <a href="${createLink(controller: 'html', action: 'index',params: [id:topic.id])}" target="_blank">${topic.title}</a>
    </li>
</my:hotTopic>
    </ul>
</div>