/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasssList;

import Modal.Department;
import Modal.Doctor;
import Utils.Menu;
import Utils.Regex;
import Utils.Validation;

/**
 *
 * @author trong
 */
public class HospitalManagement {

        DoctorList doctorList = new DoctorList();
        DepartmentList departmentList = new DepartmentList();

        public HospitalManagement() {
                doctorList.loadDataFile(Regex.DOCTOR_DATA_PATH);
                departmentList.loadDataFile(Regex.DEPARTMENT_DATA_PATH);
        }

        // input id 
        private String inputDoctorId(boolean existedValid, boolean allowBlank) {
                String id;
                do {
                        id = Validation.inputString("Enter doctor id: ", "", allowBlank).toUpperCase();
                        Doctor existedDoctor = doctorList.searchItem(id);
                        if (!existedValid && existedDoctor != null) {
                                System.out.println("This id already existed, Please enter again!");
                                continue;
                        }
                        if (existedValid && existedDoctor == null) {
                                System.out.println("This id doesn't existed, Please enter again!");
                                continue;
                        }
                        return id;
                } while (true);
        }

        private String inputDepartmentId(boolean allowBlank, boolean existedValid) {
                String id;
                do {
                        id = Validation.inputString("Enter department id: ", "", true);
                        if (allowBlank && id == null) {
                                return null;
                        }
                        if (id == null) {
                                System.out.println("Required input");
                                continue;
                        }
                        id = id.toUpperCase();
                        Department existedDepartment = departmentList.searchItem(id);
                        if (!existedValid && existedDepartment != null) {
                                System.out.println("This id already existed, Please enter again!");
                                continue;
                        }
                        if (existedValid && existedDepartment == null) {
                                System.out.println("This id doesn't existed, Please enter again!");
                                continue;
                        }
                        return id;
                } while (true);
        }

        // input object 
        private Doctor inputDoctor(boolean allowBlank, boolean existedValid) {
                Doctor doctor;
                String id, name, address, departmentId;
                Boolean gender;

                id = inputDoctorId(existedValid, false);
                name = Validation.inputString("Enter doctor name: ", "", allowBlank);
                address = Validation.inputString("Enter address: ", "", allowBlank);
                gender = Validation.inputBoolean("Is this person male? (y/n): ", allowBlank);
                departmentId = inputDepartmentId(allowBlank, true);
                //create object
             
                doctor = new Doctor(id, name, gender, address, departmentId);
                return doctor;

        }

        private Department inputDepartment(boolean allowBlank, boolean existedValid) {
                Department department;
                String id, name;

                id = inputDepartmentId(allowBlank, existedValid);
                name = Validation.inputString("Enter department name: ", "", allowBlank);
                //create object
                department = new Department(id, name);
                return department;
        }

        public void showInfo() {
                Menu menu = new Menu();
                menu.add("Show doctor list");
                menu.add("Show department list");
                menu.add("Other - quit");

                System.out.println("-------- Show information --------");
                int choice = menu.getUserChoice();
                switch (choice) {
                        case 1:
                                if (doctorList.isEmpty()) {
                                        System.out.println("There is no doctor in the list!");
                                        break;
                                }
                                doctorList.printall();
                                break;
                        case 2:
                                if (departmentList.isEmpty()) {
                                        System.out.println("There is no department in the list!");
                                        break;
                                }
                                departmentList.printall();
                                break;
                        default:
                                System.out.println("");
                                return;
                }
                System.out.println("");
        }

        public void addNew() {
                Menu menu = new Menu();
                menu.add("Add new doctor");
                menu.add("Add new department");
                menu.add("Other - quit");

                System.out.println("-------- Add new object --------");
                boolean flag;
                int choice = menu.getUserChoice();
                switch (choice) {
                        case 1:
                                do {
                                        Doctor doctor = inputDoctor(false, false);
                                        doctorList.addItem(doctor);
                                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                                } while (flag);
                                break;
                        case 2:
                                do {
                                        Department department = inputDepartment(false, false);
                                        departmentList.addItem(department);
                                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                                } while (flag);
                                break;
                        default:
                                System.out.println("");
                                return;
                }
                System.out.println("");
        }

        public void updateInfo() {
                Menu menu = new Menu();
                menu.add("Update a doctor");
                menu.add("Update a department");
                menu.add("Other - quit");

                System.out.println("-------- Update an object --------");
                boolean flag;
                int choice = menu.getUserChoice();
                switch (choice) {
                        case 1:
                                do {
                                        if (doctorList.isEmpty()) {
                                                System.out.println("The doctor list is empty");
                                                return;
                                        }
                                        Doctor doctor = inputDoctor(true, true);
                                        doctorList.updateItem(doctor);
                                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                                } while (flag);
                                break;
                        case 2:
                                do {
                                        if (departmentList.isEmpty()) {
                                                System.out.println("The department list is empty");
                                                return;
                                        }
                                        Department department = inputDepartment(true, true);
                                        departmentList.updateItem(department);
                                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                                } while (flag);
                                break;
                        default:
                                System.out.println("");
                                return;
                }
                System.out.println("");
        }

        public void delete() {
                Menu menu = new Menu();
                menu.add("Delete a doctor");
                menu.add("Delete a department");
                menu.add("Other - quit");

                System.out.println("-------- Delete an object --------");
                boolean flag;
                int choice = menu.getUserChoice();
                switch (choice) {
                        case 1:
                                do {
                                        if (departmentList.isEmpty()) {
                                                System.out.println("There is no department in the list!");
                                                return;
                                        }
                                        String doctorId = inputDoctorId(true, false);
                                        flag = Validation.inputBoolean("Are you sure that delete the doctor (y/n)?: ", false);
                                        if (flag) {
                                                doctorList.removeItem(doctorId);
                                        }
                                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                                } while (flag);
                                break;
                        case 2:
                                do {
                                        if (departmentList.isEmpty()) {
                                                System.out.println("There is no department in the list!");
                                                return;
                                        }
                                        String departmentId = inputDepartmentId(false, true);
                                        flag = Validation.inputBoolean("Are you sure that delete the department (y/n)?: ", false);
                                        if (flag) {
                                                departmentList.removeItem(departmentId, doctorList);
                                        }
                                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                                } while (flag);
                                break;
                        default:
                                System.out.println("");
                                return;
                }
                System.out.println("");
        }

        public void search() {
                Menu menu = new Menu();
                menu.add("Search a doctor");
                menu.add("Search a department");
                menu.add("Other - quit");

                System.out.println("-------- Search an object --------");
                boolean flag;
                int choice = menu.getUserChoice();
                switch (choice) {
                        case 1:
                                do {
                                        if (doctorList.isEmpty()) {
                                                System.out.println("The doctor list is empty");
                                                return;
                                        }
                                        String doctorName = Validation.inputString("Etner the name you want to search: ", "", false);
                                        doctorList.searchByName(doctorName);
                                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                                } while (flag);
                                break;
                        case 2:
                                do {
                                        if (departmentList.isEmpty()) {
                                                System.out.println("The department list is empty");
                                                return;
                                        }
                                        String departmentId = inputDepartmentId(false, true);
                                        Department department = departmentList.searchItem(departmentId);
                                        if (department != null) {
                                                System.out.println("Found!");
                                                departmentList.printObj(department);
                                        } else {
                                                System.out.println("Cannot find any result has " + departmentId + " Id");
                                        }
                                        flag = Validation.inputBoolean("Do you want to continue (y/n)?: ", false);
                                } while (flag);
                                break;
                        default:
                                System.out.println("");
                                return;
                }
                System.out.println("");
        }

        public void saveToFile() {
                Menu menu = new Menu();
                menu.add("Save doctor list to docto.datr");
                menu.add("Search department list to department.dat");
                menu.add("Other - quit");

                System.out.println("-------- Save to file --------");
                boolean flag;
                int choice = menu.getUserChoice();
                switch (choice) {
                        case 1:
                                if (doctorList.isEmpty()) {
                                        System.out.println("The doctor list is empty");
                                        return;
                                }
                                doctorList.saveDataFile(Regex.DOCTOR_DATA_PATH);
                                System.out.println("Successful save");
                                break;
                        case 2:
                                if (departmentList.isEmpty()) {
                                        System.out.println("The department list is empty");
                                        return;
                                }
                                departmentList.saveDataFile(Regex.DEPARTMENT_DATA_PATH);
                                System.out.println("Successful save");
                                break;
                        default:
                                System.out.println("");
                                return;
                }
                System.out.println("");
        }

}
