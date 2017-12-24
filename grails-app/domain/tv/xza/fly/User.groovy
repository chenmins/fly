package tv.xza.fly

import grails.converters.JSON

class User {

    String username
    String password
    String email
    Roles role = Roles.user

    static mapping = {
        table('fly_user')
    }

    static constraints = {
    }

    @Override
    String toString() {
        return this as JSON
    }
}
