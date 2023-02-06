package Application;

import ClassList.StudentList;
import ClassList.SubjectList;
import ClassList.TranscriptList;
import Modal.Student;
import Modal.Subject;
import Modal.Transcript;
import Utilities.Menu;
import Utilities.Regex;
import Utilities.Validation;
import java.text.ParseException;
import java.util.Date;

public class Application {

        private static Menu menu = new Menu();
        private static StudentList studentList = new StudentList();
        private static SubjectList subjectList = new SubjectList();
        private static TranscriptList transcriptList = new TranscriptList();

        private static void initMenu() {
                menu.add("1. Add a new student");
                menu.add("2. Update Student");
                menu.add("3. Add new subject");
                menu.add("4. Update subject");
                menu.add("5. Enter grade");
                menu.add("6. Student grade report");
                menu.add("7. Subject grade report");
                menu.add("Other - Exit");
        }

        // =================== input object =========================
        public static Student inputStudent(boolean allowBlank, boolean existedValid) {
                Boolean gender, flag;
                String id, firstName, lastName, phone, email;
                Date dateOfBirth = new Date();

                do {
                        id = Validation.inputString("Enter student id: ", "", false).toUpperCase();
                        Student existedStudent = studentList.get(id);
                        flag = false;
                        if (!existedValid && existedStudent != null) {
                                System.out.println("This id already existed, Please enter again!");
                                flag = true;
                        }
                        if (existedValid && existedStudent == null) {
                                System.out.println("This id doesn't existed, Please enter again!");
                                flag = true;
                        }
                } while (flag);

                firstName = Validation.inputString("Enter first name: ", "", allowBlank);
                lastName = Validation.inputString("Enter last name: ", "", allowBlank);
                gender = Validation.inputBoolean("Is the student are male (y/n): ", allowBlank);
                phone = Validation.inputString("Enter phone number: ", Regex.PHONE_PATTERN, allowBlank);
                dateOfBirth = Validation.inputDate("Enter date of birth: ", allowBlank);
                email = Validation.inputString("Enter email: ", Regex.EMAIL_PATTERN, allowBlank);
                return new Student(id, firstName, lastName, gender, dateOfBirth, phone, email);
        }

        public static Subject inputSubject(boolean allowBlank, boolean existedValid) {
                String id, name;
                int credit;
                boolean flag;

                do {
                        id = Validation.inputString("Enter subject id: ", "", allowBlank).toUpperCase();
                        Subject existedSubject = subjectList.get(id);
                        flag = false;
                        if (!existedValid && existedSubject != null) {
                                System.out.println("This id already existed, Please enter again!");
                                flag = true;
                        }
                        if (existedValid && existedSubject == null) {
                                System.out.println("This id doesn't existed, Please enter again!");
                                flag = true;
                        }
                } while (flag);

                name = Validation.inputString("Enter name: ", "", allowBlank);
                credit = Validation.inputInterger("Enter credit: ", allowBlank);
                return new Subject(id, name, credit);

        }

        public static Transcript inputTranscript(boolean allowBlank) {
                double labMark, testMark, finalMark;
                Student student;
                Subject subject;

                student = inputStudentId();
                subject = inputSubjectId();
                if (student == null || subject == null) {
                        return null;
                }
                labMark = Validation.inputDouble("Enter lab mark: ", false);
                testMark = Validation.inputDouble("Enter test mark: ", false);
                finalMark = Validation.inputDouble("Enter final mark: ", false);

                return new Transcript(student, subject, labMark, testMark, finalMark);
        }

        // ============ Input object id ==================
        public static Student inputStudentId() {
                String id;
                do {
                        id = Validation.inputString("Enter student id: ", "", false).toUpperCase();
                        Student existedStudent = studentList.get(id);
                        if (existedStudent == null) {
                                System.out.println("This id doesn't existed, please enter again!");
                                continue;
                        }
                        return existedStudent;
                } while (true);
        }

        public static Subject inputSubjectId() {
                String id;
                do {
                        id = Validation.inputString("Enter subject id: ", "", false).toUpperCase();
                        Subject existedSubject = subjectList.get(id);
                        if (existedSubject == null) {
                                System.out.println("This id doesn't existed, please enter again!");
                                continue;
                        }
                        return existedSubject;
                } while (true);
        }

        // ==================== student process ==========================
        public static void addStudentProcess() {
                Boolean flag = true;
                while (flag) {
                        Student student = inputStudent(false, false);
                        studentList.addStudent(student);
                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                }
        }

        public static void deleteStudentProcess() {
                Boolean flag = true;
                while (flag) {
                        Student student = inputStudentId();
                        flag = false;
                        for (Transcript transcript : transcriptList) {
                                if (transcript.getStudentObj().equals(student)) {
                                        System.out.println("This student is already in a subject, delete fail!");
                                        return;
                                }
                        }
                        studentList.deleteStudent(student.getId());
                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                }
        }

        public static void updateStudentProcess() {
                Boolean flag = true;
                while (flag) {
                        Student student = inputStudent(true, true);
                        if (student != null) {
                                studentList.updateStudent(student);
                        }
                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                }
        }

        public static void updateStudentMenu() {
                if (studentList.isEmpty()) {
                        System.out.println("There is no student in the database");
                        return;
                }
                Menu updateMenu = new Menu();
                int choice;
                // adding menu
                System.out.println("==== Update Student ====");
                updateMenu.add("1. Update student Information");
                updateMenu.add("2. Delete student");
                updateMenu.add("Other - Back to main menu");
                while (true) {
                        choice = updateMenu.getUserChoice();
                        switch (choice) {
                                case 1:
                                        updateStudentProcess();
                                        break;
                                case 2:
                                        deleteStudentProcess();
                                        break;
                                default:
                                        return;
                        }
                }
        }

        // ==================== subject process ==========================
        public static void addSubjectProcess() {
                Boolean flag = true;
                while (flag) {
                        Subject subject = inputSubject(false, false);
                        if (subject != null) {
                                subjectList.addSubject(subject);
                        }
                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                }
        }

        public static void updateSubjectProcess() {
                Boolean flag = true;
                while (flag) {
                        Subject subject = inputSubject(true, true);
                        if (subject != null) {
                                subjectList.updateSubject(subject);
                        }
                        flag = Validation.inputBoolean("Do you want to continue (y/n)?:", false);
                }
        }

        public static void deleteSubjectProcess() {
                Boolean flag = true;
                while (flag) {
                        Subject subject = inputSubjectId();
                        flag = false;
                        for (Transcript transcript : transcriptList) {
                                if (transcript.getSubjectObj().equals(subject)) {
                                        System.out.println("This subject already had student, delete fail!");
                                        return;
                                }
                        }
                        subjectList.deleteSubject(subject.getId());
                        flag = Validation.inputBoolean("Do you want to continue (y/n)?:", false);
                }
        }

        public static void updateSubjectMenu() {
                if (subjectList.isEmpty()) {
                        System.out.println("There is no subject in the database");
                        return;
                }

                Menu updateMenu = new Menu();
                updateMenu.add("1. Update subject");
                updateMenu.add("2. delete Menu");
                updateMenu.add("Other - back to main menu");
                while (true) {
                        System.out.println("===== Update subject ======");
                        int choice = updateMenu.getUserChoice();
                        switch (choice) {
                                case 1:
                                        updateSubjectProcess();
                                        break;
                                case 2:
                                        deleteSubjectProcess();
                                        break;
                                default:
                                        System.out.println("");
                                        return;
                        }
                }
        }

        //================== Grade process =====================
        public static void addGradeProcess() {
                Boolean flag = true;
                while (flag) {
                        Transcript transcript = inputTranscript(false);
                        if (transcript != null) {
                                transcriptList.addGrade(transcript);
                        }
                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                }
        }

        public static void reportStudentGradeProcess() {
                Boolean flag = true;
                while (flag) {
                        Student student = inputStudentId();
                        if (student != null) {
                                transcriptList.reportStudentGrades(student.getId());
                        }
                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                }
        }

        public static void reportSubjectProcesss() {
                Boolean flag = true;
                while (flag) {
                        Subject subject = inputSubjectId();
                        if (subject != null) {
                                transcriptList.reportSubjectGrades(subject.getId());
                        }
                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                }
        }

        public static void process() {
                System.out.println("========= Student manager ==========");
                System.out.println("");

                while (true) {
                        System.out.println("===== Main menu =====");
                        int choice = menu.getUserChoice();
                        switch (choice) {
                                case 1:
                                        addStudentProcess();
                                        break;
                                case 2:
                                        updateStudentMenu();
                                        break;
                                case 3:
                                        addSubjectProcess();
                                        break;
                                case 4:
                                        updateSubjectMenu();
                                        break;
                                case 5:
                                        addGradeProcess();
                                        break;
                                case 6:
                                        reportStudentGradeProcess();
                                        break;
                                case 7:
                                        reportSubjectProcesss();
                                        break;
                                default:
                                        return;
                        }
                }
        }

        public static void main(String[] args) {
                initMenu();
                process();
        }
}
