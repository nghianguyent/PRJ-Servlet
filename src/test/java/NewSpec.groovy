import spock.lang.Specification

class NewSpec extends Specification{
    def "two plus two should equal four"() {
        given:
        int left = 2
        int right = 5

        when:
        int result = left + right

        then:
        result == 4
    }
}
