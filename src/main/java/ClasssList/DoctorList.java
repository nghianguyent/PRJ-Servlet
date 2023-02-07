/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasssList;

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
public class DoctorList extends ArrayList<Doctor> implements Modifier {

    @Override
    public boolean addItem(Object obj) {
        Doctor doctor = (Doctor) obj;
        this.add(doctor);
        return true;
    }

    @Override
    public boolean updateItem(Object obj) {
        Doctor newDoctor = (Doctor) obj;
        Doctor doctor = this.searchItem(newDoctor.getId());
        if (doctor == null) {
            return false;
        }
        if (newDoctor.getName() != null) {
            doctor.setName(newDoctor.getName());
        }
        if (newDoctor.getAddress() != null) {
            doctor.setAddress(newDoctor.getAddress());
        }
        if (newDoctor.isSex() != null) {
            doctor.setSex(newDoctor.isSex());
        }
        if (newDoctor.getDepartmentID() != null) {
            doctor.setDepartmentID(newDoctor.getDepartmentID());
        }
        doctor.setLastUpdateDate(new Date());

        System.out.println("Success updating");
        printObj(doctor);
        return true;
    }

    @Override
    public boolean removeItem(String id) {
        Doctor doctor = this.searchItem(id);
        return this.remove(doctor);
    }

    @Override
    public Doctor searchItem(String id) {
        for (Doctor doctor : this) {
            if (doctor.getId().toUpperCase().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public void printall() {
        if (this.isEmpty()) {
            System.out.println("The doctor list is empty");
        }
        System.out.println("========== Doctor list =============");
        System.out.println("| ++ Doctor ID ++ | +++++++ Doctor name +++++++ | +++ Gender +++ | +++++++++++ Addresss +++++++++++ | +++++ Department ID +++++ | ++++ Created date ++++ | ++++ Last updated date ++++ |");
        this.forEach((doctor) -> {
            System.out.format("     %-15s%-34s%-13s%-40s%-28s%-28s%s\n",
                    doctor.getId(), doctor.getName(), doctor.getSex(), doctor.getAddress(),
                    doctor.getDepartmentID(), Validation.convertDateString(doctor.getCreateDate()), Validation.convertDateString(doctor.getLastUpdateDate()));
        });
    }

    @Override
    public void printObj(Object obj) {
        Doctor doctor = (Doctor) obj;
        System.out.println("---------- Doctor information ----------");
        System.out.println("-  Doctor id: " + doctor.getId());
        System.out.println("-  Name: " + doctor.getName());
        System.out.println("-  Gender: " + doctor.getSex());
        System.out.println("-  Address: " + doctor.getAddress());
        System.out.println("-  Department ID: " + doctor.getDepartmentID());
        System.out.println("-  Created date: " + Validation.convertDateString(doctor.getCreateDate()));
        System.out.println("-  Last update date: " + Validation.convertDateString(doctor.getLastUpdateDate()));
        System.out.println("");
    }

    public DoctorList searchByName(String name) {
        DoctorList list = new DoctorList();
        for (Doctor doctor : this) {
            if (doctor.getName().contains(name)) {
                list.addItem(doctor);
            }
        }
        if (list.isEmpty()) {
            System.out.println("Cannot find any doctor has " + name + " name");
        }
        list.printall();
        return list;
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
            Doctor doctor;

            while ((doctor = (Doctor) (obj.readObject())) != null) {
                this.add(doctor);
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
            for (Doctor doctor : this) {
                obj.writeObject(doctor);
            }
            obj.flush();
            obj.close();
            fo.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//        public static void main(String[] args) {
//                DoctorList doctorList = new DoctorList();
//                Doctor doctor = new Doctor("test123123", "test", true, "testsetestset", "se");
//                Doctor doctor2 = new Doctor("seaae", "test", true, "testsetestset", "se");
//                Doctor doctor3 = new Doctor("testasdfasdf123123", "test", true, "testsetestset", "se");
//
//                doctorList.add(doctor2);
//                doctorList.add(doctor3);
//                doctorList.addItem(doctor);
//                doctorList.saveDataFile();
//                doctorList.loadDataFile();
//        }
}
