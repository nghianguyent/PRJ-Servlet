/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.text.SimpleDateFormat;

/**
 *
 * @author trong
 */
public class Regex {
        public static SimpleDateFormat DATE_PATTERN = new SimpleDateFormat("dd/MM/yyyy");
        public static String EMAIL_PATTERN = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+";
        public static String PHONE_PATTERN = "\\d{10,12}";
        public static String DOCTOR_DATA_PATH = "doctor.dat";
        public static String DEPARTMENT_DATA_PATH = "department.dat";
}
