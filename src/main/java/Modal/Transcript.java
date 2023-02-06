/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

/**
 *
 * @author trong
 */
public class Transcript {

        private Student studentObj;
        private Subject subjectObj;
        private Double labMark;
        private Double testMark;
        private Double finalMark;

        public Transcript() {
        }

        public Transcript(Student studentObj, Subject subjectObj, Double labMark, Double testMark, Double finalMark) {
                this.studentObj = studentObj;
                this.subjectObj = subjectObj;
                this.labMark = labMark;
                this.testMark = testMark;
                this.finalMark = finalMark;
        }

        public Student getStudentObj() {
                return studentObj;
        }

        public void setStudentObj(Student studentObj) {
                this.studentObj = studentObj;
        }

        public Subject getSubjectObj() {
                return subjectObj;
        }

        public void setSubjectObj(Subject subjectObj) {
                this.subjectObj = subjectObj;
        }

        public Double getLabMark() {
                return labMark;
        }

        public void setLabMark(Double labMark) {
                this.labMark = labMark;
        }

        public Double getTestMark() {
                return testMark;
        }

        public void setTestMark(Double testMark) {
                this.testMark = testMark;
        }

        public Double getFinalMark() {
                return finalMark;
        }

        public void setFinalMark(Double finalMark) {
                this.finalMark = finalMark;
        }

        public Double average() {
                return labMark * 0.3 + testMark * 0.3 + finalMark * 0.4;
        }

        @Override
        public String toString() {
                return "Transcript{" + "studentObj=" + studentObj + ", subjectObj=" + subjectObj + ", labMark=" + labMark + ", testMark=" + testMark + ", finalMark=" + finalMark + '}';
        }
}
