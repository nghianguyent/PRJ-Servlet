/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.io.Serializable;

/**
 *
 * @author Nghia Nguyen
 */
public class Account implements Serializable{

    private int acacid;
    private String email;
    private String password;
    private String fulname;
    private String phone;
    private int status;
    private int role;
    private String token;

    public Account(int acacid, String email, String password, String fulname, String phone, int status, int role, String token) {
        this.acacid = acacid;
        this.email = email;
        this.password = password;
        this.fulname = fulname;
        this.phone = phone;
        this.status = status;
        this.role = role;
        this.token = token;
    }

    public Account() {

    }

    public int getAcacid() {
        return acacid;
    }

    public void setAcacid(int acacid) {
        this.acacid = acacid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFulname(String fulname) {
        this.fulname = fulname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fulname;
    }

    public String getPhone() {
        return phone;
    }

    public int getStatus() {
        return status;
    }

    public int getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Account{" + "acacid=" + acacid + ", email=" + email + ", password=" + password + ", fulname=" + fulname + ", phone=" + phone + ", status=" + status + ", role=" + role + '}';
    }

}
