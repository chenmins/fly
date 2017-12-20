package tv.xza.fly

class User {

    String username
    String password
    String email
    Roles role = Roles.user

    static constraints = {
    }
}
