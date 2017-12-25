package fly

import grails.converters.JSON
import tv.xza.fly.JWT
import tv.xza.fly.User

import javax.servlet.http.Cookie


class TokenInterceptor {

    public TokenInterceptor() {
        matchAll()
    }

    boolean before() {
        String token = null;
        request.getCookies().each {cookie ->
            if(cookie.getName().equals("token")){
                //得到cookie的值
                token=cookie.getValue();
                return  ;
            }
        }
//        println "init token:${token}"
        if(token == null)
            return true
        if(session.user==null){
            String users = JWT.unsign(token,String.class);
            if (users==null){
                //另一种可能就是token失效，清理掉
                Cookie oItem;
                // 因为Cookie 中不允许保存特殊字符, 所以采用 BASE64 编码，CookieUtil.encode()是BASE64编码方法,略..
                oItem = new Cookie("token", "");
                oItem.setMaxAge(-1); //关闭浏览器后，cookie立即失效
                oItem.setPath("/");
                response.addCookie(oItem);
                return true
            }
            println "init users:${users}"
            def jsonStr=JSON.parse(users)
            def u = User.get(jsonStr.id)
            session.user = u
            println "init jsonStr:${jsonStr.id}"
            println "init session:${ u}"
        }

        return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
