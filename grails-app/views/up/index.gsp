<!doctype html>
<html>
<head>
    <meta name="layout" content="home"/>
    <title>404-社区1222</title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <asset:javascript src="qiniu.min.js"/>
</head>

<body>

<g:render template="../template/nav"></g:render>

<div class="layui-container fly-marginTop">
    <div class="fly-panel">
        <div class="fly-none">
            <p>这个上传页面</p>

            <div id="container">
                <button  id="pickfiles" class="layui-btn layui-btn-danger">上传文件</button>
                <button  id="getText" class="layui-btn layui-btn-danger">getText</button>
                <button  id="getContent" class="layui-btn layui-btn-danger">getContent</button>
            </div>
            <textarea id="demo2" style="display: none;"></textarea>
            <div class="layui-progress layui-progress-big" lay-showpercent="true" lay-filter="demo">
                <div class="layui-progress-bar layui-bg-red" lay-percent="0%"></div><div id="speeds"></div>
            </div>
            <div id="up">

            </div>

        </div>
    </div>
</div>
<script>
    var layedit,editindex
    function initEdit(id) {
        layui.use('layedit', function(){
            layedit = layui.layedit;
            editindex = layedit.build(id,
                {tool: [
                        'strong' //加粗
                        ,'italic' //斜体
                        ,'underline' //下划线
                        ,'del' //删除线
                        ,'|' //分割线
                        ,'left' //左对齐
                        ,'center' //居中对齐
                        ,'right' //右对齐
                        ,'link' //超链接
                        ,'unlink' //清除链接
                        // ,'face' //表情
                        // ,'image' //插入图片
                        // ,'help' //帮助
                    ]}
            ); //建立编辑器
        });
    }
    function initEVT(id) {
        $('#getContent').click(function(){
            alert(layedit.getContent(editindex))
        });
        $('#getText').click(function(){
            alert(layedit.getText(editindex))
        });
    }
    initEdit('demo2')
    initEVT('demo2')
    function addHTML(id,some) {
        layedit.sync(editindex);
        $("#"+id).html(some+"<br/>"+ $("#demo2").html())
        initEdit(id)
    }
    function speeds(as) {
        $("#speeds").html(as)
    }
    function process(n) {
        layui.use('element', function(){
            var $ = layui.jquery
                ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
            element.progress('demo', n+'%');
        });
    }
    function checkEnd(str,end){
        var pos = str.lastIndexOf(end);
        if(pos === -1){
            return false;
        }else{
            return pos + end.length === str.length;
        }
    }
    function returnFloat(value){
        var value=Math.round(parseFloat(value)*100)/100;
        var xsd=value.toString().split(".");
        if(xsd.length==1){
            value=value.toString()+".00";
            return value;
        }
        if(xsd.length>1){
            if(xsd[1].length<2){
                value=value.toString()+"0";
            }
            return value;
        }
    }
    var uploader = Qiniu.uploader({
        runtimes: 'html5,flash,html4',      // 上传模式，依次退化
        browse_button: 'pickfiles',         // 上传选择的点选按钮，必需
        // 在初始化时，uptoken，uptoken_url，uptoken_func三个参数中必须有一个被设置
        // 切如果提供了多个，其优先级为uptoken > uptoken_url > uptoken_func
        // 其中uptoken是直接提供上传凭证，uptoken_url是提供了获取上传凭证的地址，如果需要定制获取uptoken的过程则可以设置uptoken_func
        // uptoken : '<Your upload token>', // uptoken是上传凭证，由其他程序生成
        uptoken_url: '/up/json',         // Ajax请求uptoken的Url，强烈建议设置（服务端提供）
        // uptoken_func: function(){    // 在需要获取uptoken时，该方法会被调用
        //    // do something
        //    return uptoken;
        // },
        get_new_uptoken: false,             // 设置上传文件的时候是否每次都重新获取新的uptoken
        // downtoken_url: '/downtoken',
        // Ajax请求downToken的Url，私有空间时使用，JS-SDK将向该地址POST文件的key和domain，服务端返回的JSON必须包含url字段，url值为该文件的下载地址
        unique_names: true,              // 默认false，key为文件名。若开启该选项，JS-SDK会为每个文件自动生成key（文件名）
        // save_key: true,                  // 默认false。若在服务端生成uptoken的上传策略中指定了sava_key，则开启，SDK在前端将不对key进行任何处理
        domain: 'ohlo5qp57.qnssl.com',     // bucket域名，下载资源时用到，必需
        container: 'container',             // 上传区域DOM ID，默认是browser_button的父元素
        max_file_size: '100mb',             // 最大文件体积限制
        flash_swf_url: 'path/of/plupload/Moxie.swf',  //引入flash，相对路径
        max_retries: 3,                     // 上传失败最大重试次数
        dragdrop: true,                     // 开启可拖曳上传
        drop_element: 'container',          // 拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
        chunk_size: '4mb',                  // 分块上传时，每块的体积
        auto_start: true,                   // 选择文件后自动上传，若关闭需要自己绑定事件触发上传
        //x_vars : {
        //    查看自定义变量
        //    'time' : function(up,file) {
        //        var time = (new Date()).getTime();
        // do something with 'time'
        //        return time;
        //    },
        //    'size' : function(up,file) {
        //        var size = file.size;
        // do something with 'size'
        //        return size;
        //    }
        //},
        init: {
            'FilesAdded': function(up, files) {
                plupload.each(files, function(file) {
                    // 文件添加进队列后，处理相关的事情
                });
            },
            'BeforeUpload': function(up, file) {
                // 每个文件上传前，处理相关的事情
                // file.id
                // file.name
            },
            'UploadProgress': function(up, file) {
                // 每个文件上传时，处理相关的事情
                var info =(returnFloat(file.speed/ 1024)+ 'KB/s ' + returnFloat((file.loaded / 1024/ 1024))+"MB/"+returnFloat((file.size / 1024/ 1024)) +"MB");
                process(file.percent)
                speeds(info)
            },
            'FileUploaded': function(up, file, info) {
                // 每个文件上传成功后，处理相关的事情
                // 其中info.response是文件上传成功后，服务端返回的json，形式如：
                // {
                //    "hash": "Fh8xVqod2MQ1mocfI4S4KpRL6D98",
                //    "key": "gogopher.jpg"
                //  }
                // 查看简单反馈
                var domain = up.getOption('domain');
                var res = $.parseJSON(info.response);
                var sourceLink = "http://"+domain +"/"+ res.key; //获取上传成功后的文件的Url
                var a='<a href="'+sourceLink+'">'+sourceLink+'</a><br/>';
                if(checkEnd(res.key,".jpg")||checkEnd(res.key,".png")||checkEnd(res.key,".gif")){
                    // $('#up').append("<img src='"+sourceLink+"' />");
                    addHTML("demo2","<img src='"+sourceLink+"' />")
                }else{
                    addHTML("demo2",a+"<br/>")
                }
                // $('#up').append(a);
                process(100)
            },
            'Error': function(up, err, errTip) {
                //上传出错时，处理相关的事情
            },
            'UploadComplete': function() {
                //队列文件处理完毕后，处理相关的事情
            },
            'Key': function(up, file) {
                // 若想在前端对每个文件的key进行个性化处理，可以配置该函数
                // 该配置必须要在unique_names: false，save_key: false时才生效
                var key = "";
                // do something with key here
                return key
            }
        }
    });

</script>
</body>
</html>
