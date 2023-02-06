/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

import java.io.Serializable;

/**
 *
 * @author trong
 */
public class Doctor extends HospitalMod implements Serializable {

        private String name;
        private Boolean sex;
        private String address;
        private String departmentID;

        public Doctor() {

        }

        public Doctor(String id, String name, Boolean sex, String address, String departmentID) {
                super(id);
                this.name = name;
                this.sex = sex;
                this.address = address;
                this.departmentID = departmentID;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Boolean isSex() {
                return sex;
        }

        public void setSex(boolean sex) {
                this.sex = sex;
        }

        public String getSex() {
                return sex ? "male" : "female";
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public String getDepartmentID() {
                return departmentID;
        }

        public void setDepartmentID(String departmentID) {
                this.departmentID = departmentID;
        }

        @Override
        public String toString() {
                return "id= " + id + "name=" + name + ", sex=" + sex + ", address=" + address + ", departmentID=" + departmentID + "createDate=" + createDate + "lastUpdateDate=" + lastUpdateDate;
        }

        public String toFile() {
                return id + ", " + name + ", " + sex + ", " + address + ", " + createDate + ", " + lastUpdateDate;
        }
}
