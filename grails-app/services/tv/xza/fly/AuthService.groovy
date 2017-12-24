package tv.xza.fly

import grails.gorm.transactions.Transactional

@Transactional
class AuthService {

    User login(String email,String password) {
        def u = User.findByEmail(email)
        if(u==null||u.password!=password){
            return null;
        }
        return u;
    }
}
