/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassList;

import Modal.Student;
import Modal.Subject;
import Modal.Transcript;
import Utilities.Regex;

import Utilities.Validation;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author trong
 */
public class TranscriptList extends ArrayList<Transcript> {

        public void addGrade(Transcript newTranscipt) {
                for (int i = 0; i < this.size(); i++) {
                        Transcript transcript = this.get(i);
                        Student student = transcript.getStudentObj();
                        Subject subject = transcript.getSubjectObj();
                        if (student.equals(newTranscipt.getStudentObj()) && subject.equals(newTranscipt.getSubjectObj())) {
                                String message = "This student is graded, do you want to overwrite (y/n): ";
                                String isOverwrite = Validation.inputString(message, "y|Y|n|n", false).toLowerCase();
                                if (!isOverwrite.equals("y")) {
                                        System.out.println("Fail");
                                        return;
                                }
                                this.set(i, newTranscipt);
                                System.out.println("Success");
                                return;
                        }
                }
                this.add(newTranscipt);
                System.out.println("Success");
        }

        public void reportStudentGrades(String studentId) {
                boolean isStudentExisted = false;
                int count = 0;
                String studentName = new String();
                for (Transcript transcript : this) {
                        if (transcript.getStudentObj().getId().equals(studentId)) {
                                isStudentExisted = true;
                                studentName = transcript.getStudentObj().getLastName() + " " + transcript.getStudentObj().getFirstName();
                        }
                }
                if (!isStudentExisted) {
                        System.out.println("This student doesn't graded yet");
                        return;
                }
                System.out.println("Student mark report:");
                System.out.println("-  Student ID: " + studentId);
                System.out.println("-  Studen name: " + studentName);
                System.out.println("|++ No ++|+++++++  Subject name ++++++++| ++ Averagemark ++| ++ Status ++|");
                for (int i = 0; i < this.size(); i++) {
                        Transcript transcript = this.get(i);
                        Student student = transcript.getStudentObj();
                        Subject subject = transcript.getSubjectObj();
                        if (student.getId().equals(studentId)) {
                                String isPass = transcript.average() > 5.0 ? "Pass" : "Not pass";
                                System.out.format("   %-10d%-34s%-14.2f %s\n", ++count, subject.getName(), transcript.average(), isPass);
                        }
                }
        }

        public void reportSubjectGrades(String subjectId) {
                boolean isSubjectExisted = false;
                String subjectName = new String();
                for (Transcript transcript : this) {
                        if (transcript.getSubjectObj().getId().equals(subjectId)) {
                                isSubjectExisted = true;
                                subjectName = transcript.getSubjectObj().getName();

                        }
                }
                if (!isSubjectExisted) {
                        System.out.println("Subject have no student grade");
                        return;
                }
                System.out.println("Subject mark report:");
                System.out.println("-  Student ID: " + subjectId);
                System.out.println("-  Studen name: " + subjectName);
                System.out.println("|++ Student ID ++|+++++ Student name ++++++|++ Averagemark ++|++ Status ++|");
                this.forEach((transcript) -> {
                        Student student = transcript.getStudentObj();
                        Subject subject = transcript.getSubjectObj();
                        if (subject.getId().equals(subjectId)) {
                                String isPass = transcript.average() > 5.0 ? "Pass" : "Not pass";
                                System.out.format("    %-16s%-30s%-14.2f %s\n", student.getId(), student.getLastName() + " " + student.getFirstName(), transcript.average(), isPass);
                        }
                });
        }
}
