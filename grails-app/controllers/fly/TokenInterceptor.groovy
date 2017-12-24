package fly

import grails.converters.JSON
import tv.xza.fly.JWT
import tv.xza.fly.User


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
            if (users==null)
                return true
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
