package tv.xza.fly

import grails.converters.JSON
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.ws.rs.FormParam
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.Context

@Path('/api/common')
@Api(value = "common", description = "公共服务相关接口")
class CommonResource {

    AuthService authService

    def grailsApplication

    TopicService topicService

    @POST
    @Path('/setCookies/{key}')
    @Produces('text/plain')
    @ApiOperation(value="设置cookies", notes="设置具体的cookies值")
    void setCookies(@ApiParam(required = true, value = "cookies的名称") @PathParam("key") String key,
                    @ApiParam(required = true, value = "cookies的值") String value,
                    @Context HttpServletResponse response){
        println "key:${key}"
        println "value:${value}"
        Cookie oItem;
        // 因为Cookie 中不允许保存特殊字符, 所以采用 BASE64 编码，CookieUtil.encode()是BASE64编码方法,略..
        oItem = new Cookie(key, value);
        oItem.setMaxAge(-1); //关闭浏览器后，cookie立即失效
        oItem.setPath("/");
        response.addCookie(oItem);
    }

    @GET
    @Path('/cookies/{key}/{value}')
    @Produces('text/plain')
    @ApiOperation(value="设置cookies", notes="设置具体的cookies值")
    void cookies(@ApiParam(required = true, value = "cookies的名称") @PathParam("key") String key,
                 @ApiParam(required = true, value = "cookies的值")  @PathParam("value")String value,
                 @Context HttpServletResponse response){
        println "key:${key}"
        println "value:${value}"
        Cookie oItem;
        // 因为Cookie 中不允许保存特殊字符, 所以采用 BASE64 编码，CookieUtil.encode()是BASE64编码方法,略..
        oItem = new Cookie(key, value);
        oItem.setMaxAge(-1); //关闭浏览器后，cookie立即失效
        oItem.setPath("/");
        response.addCookie(oItem);
//        return "key:${key},value:${value}"
    }

    @GET
    @Path('/cookies/{key}')
    @Produces('text/plain')
    @ApiOperation(value="获得cookies", notes="获得具体的cookies值")
    String getCookies(@ApiParam(required = true, value = "cookies的名称") @PathParam("key") String key,
                      @Context HttpServletRequest request){
        String value = null
        request.getCookies().each {cookie ->
            if(cookie.getName().equals(key)){
                //得到cookie的值
                value=cookie.getValue();
                println "value:${value}"
                return  ;
            }
        }
        value = value==null?'empty':value
        return value;
    }

    @GET
    @Path('/getTopicReadCountAndInc/{id}')
    @Produces('text/plain')
    @ApiOperation(value="文章阅读++", notes="根据id增加文章阅读数")
    String getTopicReadCountAndInc(@ApiParam(required = true, value = "文章id") @PathParam("id")long id) {
        //TODO 临时方案待优化
        Topic.executeUpdate("update Topic c set c.readCount =  c.readCount+1 where c.id = :id",
                [id: id])
        return
//        def topic = topicService.get(id)
//        if (topic == null)
//            return 0;
//        def count = topic.readCount
//        count ++
//        topic.readCount ++
//        topicService.save(topic)
//        return count
    }

    @POST
    @Path('/sign')
    @Produces('text/plain')
    @ApiOperation(value="jwt签名" )
    String sign(String body){
        println "body:${body}"
        String token = JWT.sign(body, 60L* 1000L* 30L);
        return token
    }

    @POST
    @Path('/unsign')
    @Produces('text/plain')
    @ApiOperation(value="jwt签名解码" )
    String unsign(String body){
        println "unsign:body:${body}"
        String token = JWT.unsign(body,String.class);
        println "token:${token}"
        return token
    }

    @POST
    @Path('/login')
    @Produces('text/plain')
    @ApiOperation(value="邮箱名称登录" )
    String login(@ApiParam(required = true, value = "邮箱名称") @FormParam("email") String email,
                 @ApiParam(required = true, value = "密码") @FormParam("password") String password,
                 @Context HttpServletResponse response){
        def u = authService.login(email,password)
        if(u==null){
            return
        }
        u.password = null
        def auths = u.toString()
        String token = JWT.sign(auths, 60L* 1000L* 30L);
        println "login is token:${token}"
        Cookie oItem;
        // 因为Cookie中不允许保存特殊字符, 所以采用 BASE64 编码，CookieUtil.encode()是BASE64编码方法,略..
        oItem = new Cookie("token", token);
        oItem.setMaxAge(-1); //关闭浏览器后，cookie立即失效
        oItem.setPath("/");
        response.addCookie(oItem);
        return token
    }

    @POST
    @Path('/refreshToken')
    @Produces('text/plain')
    @ApiOperation(value="token更新" )
    String refreshToken(@ApiParam(required = true, value = "token") @FormParam("token") String old,
                 @Context HttpServletResponse response){
        String users = JWT.unsign(old,String.class);
        if (users==null){
            //另一种可能就是token失效，清理掉
            Cookie oItem;
            // 因为Cookie 中不允许保存特殊字符, 所以采用 BASE64 编码，CookieUtil.encode()是BASE64编码方法,略..
            oItem = new Cookie("token", "");
            oItem.setMaxAge(-1); //关闭浏览器后，cookie立即失效
            oItem.setPath("/");
            response.addCookie(oItem);
            return
        }
        def jsonStr=JSON.parse(users)
        def u = User.get(jsonStr.id)
//        def u = authService.login(email,password)
        if(u==null){
            return
        }
        u.password = null
        def auths = u.toString()
        String token = JWT.sign(auths, 60L* 1000L* 30L);
        println "login is token:${token}"
        Cookie oItem;
        // 因为Cookie中不允许保存特殊字符, 所以采用 BASE64 编码，CookieUtil.encode()是BASE64编码方法,略..
        oItem = new Cookie("token", token);
        oItem.setMaxAge(-1); //关闭浏览器后，cookie立即失效
        oItem.setPath("/");
        response.addCookie(oItem);
        return token
    }

    @GET
    @Path('/fly/{key}')
    @Produces('text/plain')
    @ApiOperation(value="获取配置文件" )
    String fly(@ApiParam(required = true, value = "配置名称")  @PathParam("key") String key){
//        println "key1:${key}"
//        grailsApplication.config.toProperties().each{keys,vals ->
//            println "keys:${keys}"
//            println "vals:${vals}"
//        }
        return grailsApplication.config.getProperty(key)
    }

}
