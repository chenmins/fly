package fly

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification

class MyTagLibSpec extends Specification implements TagLibUnitTest<MyTagLib> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
