package tv.xza.fly

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TopicreplyServiceSpec extends Specification {

    TopicreplyService topicreplyService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Topicreply(...).save(flush: true, failOnError: true)
        //new Topicreply(...).save(flush: true, failOnError: true)
        //Topicreply topicreply = new Topicreply(...).save(flush: true, failOnError: true)
        //new Topicreply(...).save(flush: true, failOnError: true)
        //new Topicreply(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //topicreply.id
    }

    void "test get"() {
        setupData()

        expect:
        topicreplyService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Topicreply> topicreplyList = topicreplyService.list(max: 2, offset: 2)

        then:
        topicreplyList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        topicreplyService.count() == 5
    }

    void "test delete"() {
        Long topicreplyId = setupData()

        expect:
        topicreplyService.count() == 5

        when:
        topicreplyService.delete(topicreplyId)
        sessionFactory.currentSession.flush()

        then:
        topicreplyService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Topicreply topicreply = new Topicreply()
        topicreplyService.save(topicreply)

        then:
        topicreply.id != null
    }
}
