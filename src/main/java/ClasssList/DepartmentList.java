/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasssList;

import Modal.Department;
import Modal.Doctor;
import Modal.Modifier;
import Utils.Validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 * @author trong
 */
public class DepartmentList extends ArrayList<Department> implements Modifier {

    @Override
    public boolean addItem(Object obj) {
        Department department = (Department) obj;
        this.add(department);
        return true;
    }

    @Override
    public boolean updateItem(Object obj) {
        Department newDepartment = (Department) obj;
        Department department = this.searchItem(newDepartment.getId());
        if (department == null) return false;

        if (newDepartment.getName() != null) {
            department.setName(newDepartment.getName());
        }
        department.setLastUpdateDate(new Date());

//        System.out.println("Success updating");
//        printObj(department);
        return true;
    }

    @Override
    public boolean removeItem(String id) {
        return false;
    }

    public boolean removeItem(String id, DoctorList doctorList) {
        Department department = this.searchItem(id);
        for (Doctor doctor : doctorList) {
            if (doctor.getDepartmentID().equals(id)) {
                System.out.println("This department has doctors, delete fail!");
                return false;
            }
        }
        this.remove(department);
        System.out.println("Successful deleting");
        return true;
    }

    @Override
    public Department searchItem(String id) {
        for (Department department : this) {
            if (department.getId().toUpperCase().equals(id)) {
                return department;
            }
        }
        return null;
    }

    @Override
    public void printall() {
        if (this.isEmpty()) {
            System.out.println("The department list is empty");
            return;
        }
        System.out.println("| ++ Department ID ++ | +++++++ Department name +++++++ | ++++ Created date ++++ | ++++ Last updated date ++++ |");
        this.forEach((department) -> {
            System.out.format("     %-19s%-39s%-28s%s\n",
                    department.getId(), department.getName(), Validation.convertDateString(department.getCreateDate()), Validation.convertDateString(department.getLastUpdateDate()));
        });
    }

    @Override
    public void printObj(Object obj) {
        Department department = (Department) obj;
        System.out.println("---------- Department information ----------");
        System.out.println("Department id: " + department.getId());
        System.out.println("-  Name: " + department.getName());
        System.out.println("-  Created date: " + Validation.convertDateString(department.getCreateDate()));
        System.out.println("-  Last update date: " + Validation.convertDateString(department.getLastUpdateDate()));
        System.out.println("");
    }

    public void loadDataFile(String fileName) {
        if (this.size() > 0) {
            this.clear();
        }
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                return;
            }
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream obj = new ObjectInputStream(fi);

            Department department;
            while ((department = (Department) (obj.readObject())) != null) {
                this.add(department);
            }
            obj.close();
            fi.close();
        } catch (Exception e) {
            if (e.getMessage() != null) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void saveDataFile(String fileName) {
        if (this.isEmpty()) {
            System.out.println("Empty list");
            return;
        }
        try {
            FileOutputStream fo = new FileOutputStream(fileName);
            ObjectOutputStream obj = new ObjectOutputStream(fo);
            for (Department department : this) {
                obj.writeObject(department);
            }
            obj.flush();
            obj.close();
            fo.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//        public static void main(String[] args) {
//                DepartmentList list = new DepartmentList();
//                Department d = new Department("Stest", "straerer");
//                list.add(d);
//                list.saveDataFile();
//                list.loadDataFile();
//        }
}
