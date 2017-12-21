package tv.xza.fly

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TopicbodyServiceSpec extends Specification {

    TopicbodyService topicbodyService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Topicbody(...).save(flush: true, failOnError: true)
        //new Topicbody(...).save(flush: true, failOnError: true)
        //Topicbody topicbody = new Topicbody(...).save(flush: true, failOnError: true)
        //new Topicbody(...).save(flush: true, failOnError: true)
        //new Topicbody(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //topicbody.id
    }

    void "test get"() {
        setupData()

        expect:
        topicbodyService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Topicbody> topicbodyList = topicbodyService.list(max: 2, offset: 2)

        then:
        topicbodyList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        topicbodyService.count() == 5
    }

    void "test delete"() {
        Long topicbodyId = setupData()

        expect:
        topicbodyService.count() == 5

        when:
        topicbodyService.delete(topicbodyId)
        sessionFactory.currentSession.flush()

        then:
        topicbodyService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Topicbody topicbody = new Topicbody()
        topicbodyService.save(topicbody)

        then:
        topicbody.id != null
    }
}
