package tv.xza.fly

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ColumnServiceSpec extends Specification {

    ColumnService columnService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Column(...).save(flush: true, failOnError: true)
        //new Column(...).save(flush: true, failOnError: true)
        //Column column = new Column(...).save(flush: true, failOnError: true)
        //new Column(...).save(flush: true, failOnError: true)
        //new Column(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //column.id
    }

    void "test get"() {
        setupData()

        expect:
        columnService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Column> columnList = columnService.list(max: 2, offset: 2)

        then:
        columnList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        columnService.count() == 5
    }

    void "test delete"() {
        Long columnId = setupData()

        expect:
        columnService.count() == 5

        when:
        columnService.delete(columnId)
        sessionFactory.currentSession.flush()

        then:
        columnService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Column column = new Column()
        columnService.save(column)

        then:
        column.id != null
    }
}
