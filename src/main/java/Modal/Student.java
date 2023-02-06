/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

import java.util.Date;

/**
 *
 * @author trong
 */
public class Student {

        private String id;
        private String firstName;
        private String lastName;
        private Boolean gender;
        private Date dateOfBirth;
        private String phone;
        private String email;

        public Student(String id, String firstName, String lastName, Boolean gender, Date dateOfBirth, String phone, String email) {
                this.id = id;
                this.firstName = firstName;
                this.lastName = lastName;
                this.gender = gender;
                this.dateOfBirth = dateOfBirth;
                this.phone = phone;
                this.email = email;
        }

        public Student() {

        }

        public String getId() {
                return id;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public Boolean isGender() {
                return gender;
        }

        public void setGender(Boolean gender) {
                this.gender = gender;
        }

        public Date getDateOfBirth() {
                return dateOfBirth;
        }

        public void setDateOfBirth(Date dateOfBirth) {
                this.dateOfBirth = dateOfBirth;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        @Override
        public String toString() {
                return "Student{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + '}';
        }
}
