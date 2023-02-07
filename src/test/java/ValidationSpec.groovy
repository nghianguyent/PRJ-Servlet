import Utils.Validation
import spock.lang.Specification
import spock.lang.Unroll

class ValidationSpec extends Specification {

    def validation = new Validation();

    @Unroll
    def "Should valid the date"() {
        when:
        def result = validation.checkValidDate(date)
        then:
        result == assertion
        where:
        date         | assertion
        '12-12-2002' | true
        '12/12/2002' | false // wrong format
        '2002-12-12' | false // wrong format
        '11-13-2002' | false // invalid month
        '04-12-2002' | true
        '31-2-2002'  | false // invalid date
        '29-2-2000'  | true // is leap year so 29-2 is valid
        '29-2-2002'  | false // not a leap year
        '29-12-1888' | false // invalid year because < 1900
    }
}
