package fly

import java.text.SimpleDateFormat
import tv.xza.fly.*

class LoginTagLib {
//    static defaultEncodeAs = [taglib:'html']
    static defaultEncodeAs = [taglib:'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
//    static encodeAsForTags = [tagName: 'raw']
    static namespace = "my"

    boolean isLogin(){
        return session.user!=null;
    }

    boolean isAdmins(){
        if(!isLogin())
            return false;
        return session.user.role==Roles.admin;
    }

    def isLogin={attr,body->
        if(isLogin()){
            out <<  body()
        }
    }

    def notLogin={attr,body->
        if(!isLogin()){
            out <<  body()
        }
    }

    def isAdmin={attr,body->
        if(isAdmins()){
            out <<  body()
        }
    }
    def dateFormat={attr,body->
        if(attr.dateString){
            if(attr.dateString==~/^\d{14}$/){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                try{
                    out<<sdf.parse(attr.dateString).format("yyyy年MM月dd日 HH:mm:ss");
                }catch(Exception e){
                    out<<attr.dateString;
                }
            }else{
                out<<attr.dateString;
            }
        }
    }

}
