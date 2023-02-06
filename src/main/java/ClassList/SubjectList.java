/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassList;

import Modal.Student;
import Modal.Subject;
import java.util.HashMap;

/**
 *
 * @author trong
 */
public class SubjectList extends HashMap<String, Subject> {

        public void addSubject(Subject newSubject) {
                if (this.get(newSubject.getId()) != null) {
                        System.out.println("Subject already existed");
                }

                this.put(newSubject.getId(), newSubject);
                System.out.println("Success");
        }

        public void updateSubject(Subject newSubject) {
                Subject subject = this.get(newSubject.getId());
                if (subject == null) {
                        System.out.println("Subject doesn't exist");
                        return;
                }
              
                if (newSubject.getName() != null) {
                     subject.setName(newSubject.getName());
                }      
                if (newSubject.getCredit() != 0) {
                     subject.setCredit(newSubject.getCredit());
                }   
                System.out.println("Success");
        }

        public void deleteSubject(String id) {
                Object previousStudent = this.remove(id);
                if (previousStudent == null) {
                        System.out.println("Subject doesn't exist");
                        return;
                }

                System.out.println("Success");
        }

}
