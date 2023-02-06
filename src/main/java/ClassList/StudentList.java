/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassList;

import Modal.Student;
import java.util.HashMap;

/**
 *
 * @author trong
 */
public class StudentList extends HashMap<String, Student> {

        public void addStudent(Student newStudent) {
                if (this.get(newStudent.getId()) != null) {
                        System.out.println("This student already existed");
                }
                this.put(newStudent.getId(), newStudent);
                System.out.println("Success");

        }

        public void updateStudent(Student newStudent) {
                Student student = this.get(newStudent.getId());
                if (student == null) {
                        System.out.println("Student does not exist");
                        return;
                }

                if (newStudent.getFirstName() != null) {
                        student.setFirstName(newStudent.getFirstName());
                }
                if (newStudent.getLastName() != null) {
                        student.setLastName(newStudent.getLastName());
                }
                if (newStudent.getDateOfBirth() != null) {
                        student.setDateOfBirth(newStudent.getDateOfBirth());
                }
                if (newStudent.getPhone() != null) {
                        student.setPhone(newStudent.getPhone());
                }
                if (newStudent.getEmail() != null) {
                        student.setEmail(newStudent.getEmail());
                }

                System.out.println("Success");
        }

        public void deleteStudent(String studentId) {
                Object previousStudent = this.remove(studentId);
                if (previousStudent == null) {
                        System.out.println("Student doesn't exist");
                        return;
                }

                System.out.println("Success");
        }

}
