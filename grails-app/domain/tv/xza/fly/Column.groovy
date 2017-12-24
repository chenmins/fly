package tv.xza.fly

class Column {

    String columnName

    boolean top = false

    boolean  badge = false

    Roles role = Roles.user

    static mapping = {
        table('fly_column')
    }

    static constraints = {
    }
}
