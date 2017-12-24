package tv.xza.fly

import io.swagger.annotations.Api

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
@Api('common')
class CommonResource {

    def authService
    def grailsApplication
    TopicService topicService

    @POST
    @Path('/setCookies/{key}')
    @Produces('text/plain')
    void setCookies(@PathParam("key") String key, String value,@Context HttpServletResponse response){
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
    void cookies(@PathParam("key") String key,@PathParam("value")String value,@Context HttpServletResponse response){
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
    String getCookies(@PathParam("key") String key, @Context HttpServletRequest request){
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
    String getTopicReadCountAndInc(@PathParam("id")long id) {
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
    String sign(String body){
        println "body:${body}"
        String token = JWT.sign(body, 60L* 1000L* 30L);
        return token
    }

    @POST
    @Path('/unsign')
    @Produces('text/plain')
    String unsign(String body){
        println "unsign:body:${body}"
        String token = JWT.unsign(body,String.class);
        println "token:${token}"
        return token
    }

    @POST
    @Path('/login')
    @Produces('text/plain')
    String login(@FormParam("email") String email,@FormParam("password") String password){
        def u = authService.login(user.email,user.password)
        if(u==null){
            return
        }
        u.password = null
        def auths = u.toString()
        String token = JWT.sign(auths, 60L* 1000L* 30L);
        println "login is token:${token}"
        Cookie oItem;
        // 因为Cookie 中不允许保存特殊字符, 所以采用 BASE64 编码，CookieUtil.encode()是BASE64编码方法,略..
        oItem = new Cookie("token", token);
        oItem.setMaxAge(-1); //关闭浏览器后，cookie立即失效
        oItem.setPath("/");
        response.addCookie(oItem);
        return token
    }

    @GET
    @Path('/fly/{key}')
    @Produces('text/plain')
    String fly(@PathParam("key") String key){
        println "key1:${key}"
        grailsApplication.config.toProperties().each{keys,vals ->
            println "keys:${keys}"
            println "vals:${vals}"
        }
        return grailsApplication.config.getProperty(key)
    }

}
