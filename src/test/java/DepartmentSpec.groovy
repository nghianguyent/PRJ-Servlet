import ClasssList.DepartmentList
import ClasssList.DoctorList
import Modal.Department
import Modal.Doctor
import Utils.Validation
import spock.lang.Specification
import spock.lang.Subject
import spock.mock.MockFactory

import javax.print.Doc

class DepartmentSpec extends Specification {

    def departmentList = new DepartmentList()

    def setup() {
        departmentList << new Department("1", "Noi thuong")
        departmentList << new Department("2", "Chan thuong")
        departmentList << new Department("3", "Ung thu")
        departmentList << new Department("4", "Dieu duong")
        departmentList << new Department("5", "Dieu duong")
    }

    def "should update success"() {
        given:
        def newDepartment = new Department(updateID, newName)
        when:
        def result = departmentList.updateItem(newDepartment)

        then:
        result == assertion

        where:
        updateID | newName      | assertion
        "1"      | null         | true
        "7"      | "Xuong khop" | false
        "4"      | "Than kinh"  | true
    }
}
