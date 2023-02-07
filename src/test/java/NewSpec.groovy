import binhnd.controller.LoginServlet
import binhnd.registration.RegistrationDAO
import binhnd.registration.RegistrationDTO
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class NewSpec extends Specification {
    @Subject
    private RegistrationDAO registration = new RegistrationDAO();

    @Unroll
    def "Should be able to use create account dao"() {
        given:
        def registrationDTO = Stub(RegistrationDTO);
        when:
        registrationDTO.getUsername() >> username;
        registrationDTO.getFullName() >> name;
        registrationDTO.getPassword() >> password
        registrationDTO.isRole() >> role;
        def result = registration.createAccount(registrationDTO);

        then:
        result == expected

        where:
        username  | name                 | role  | password       | expected
        null      | "Anonymous user"     | true  | "anonymous11"  | false
        "binhnd"  | "Ngo Duy Binh"       | false | "Binh2901"     | true
        "hutruc"  | "Nguyen Hu Truc"     | true  | "987654321"    | false
        "thanhhg" | "Houang Gia Thanh"   | true  | "houanghouang" | true
        "khanh"   | "Nguyen Trong Khanh" | false | "98928887"     | false
        "nghiant" | "Nguyen Trong Nghia" | true  | "Nghiane"      | true
        "binhnt"  | "Dao Thien Binh"     | false | "binhdtrai"    | true
    }

    def "Should be able to login"() {
        given:
        def request = Stub(HttpServletRequest.class)
        def response = Stub(HttpServletResponse.class)
        def dao = Mock(RegistrationDAO)
        when:
        request.getParameter("txtUsername") >> "bindnd"
        request.getParameter("txtPassword") >> "Binh2901"
        new LoginServlet().processRequest(request, response);
        then:
        1 * dao.checkLogin("binhnd", "Binh2901")

    }
    def "Should be able to update account"() {
        when:

        then:

    }
}
