package tv.xza.fly

import com.google.gson.Gson
import com.qiniu.common.QiniuException
import com.qiniu.common.Zone
import com.qiniu.http.Response
import com.qiniu.storage.Configuration
import com.qiniu.storage.UploadManager
import com.qiniu.storage.model.DefaultPutRet
import com.qiniu.util.Auth
import grails.converters.JSON

class UpController {

    def index() {

    }

    def sim() {

    }

    def json(){
        String accessKey = "_tHGHsfzctpubXOLyclhWWsMIVsN6TEBOTKVTYk1";
        String secretKey = "aMiNGaFW3tosNu5mi3hFSHDmar0yFV8JLaQxCBSI";
        String bucket = "blog";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        System.out.println(upToken);
        def map = ['uptoken':upToken]
        render  map as JSON
    }

    def test(){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone1());
//...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "_tHGHsfzctpubXOLyclhWWsMIVsN6TEBOTKVTYk1";
        String secretKey = "aMiNGaFW3tosNu5mi3hFSHDmar0yFV8JLaQxCBSI";
        String bucket = "blog";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png nexus.txt
//        String localFilePath = "/home/qiniu/test.png";
        String localFilePath = "D:\\nexus.txt";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "nexus.txt";

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        render '{"uptoken":"${upToken}"}'

    }
}
