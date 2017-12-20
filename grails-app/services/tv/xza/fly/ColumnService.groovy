package tv.xza.fly

import grails.gorm.services.Service

@Service(Column)
interface ColumnService {

    Column get(Serializable id)

    List<Column> list(Map args)

    Long count()

    void delete(Serializable id)

    Column save(Column column)

}