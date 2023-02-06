/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;
import java.util.Calendar;

/**
 *
 * @author trong
 */
public class Validation {

        static Scanner sc = new Scanner(System.in);

        public static String inputString(String message, String pattern, boolean allowBlank) {
                String input;
                do {
                        try {
                                System.out.print(message);
                                input = sc.nextLine().trim();
                                input = input.replaceAll("\\s+", " ");
                                if (input.isEmpty()) {
                                        if (allowBlank) {
                                                return null;
                                        }
                                        throw new Exception("Required string");
                                }
                                if (!input.matches(pattern) && !pattern.equals("")) {
                                        throw new Exception("Invalid input");
                                }
                                return input;
                        } catch (Exception e) {
                                System.out.println(e.getMessage());
                        }
                } while (true);
        }

        public static int inputInterger(String message, boolean allowBlank) {
                int number;
                do {
                        try {
                                System.out.print(message);
                                String input = sc.nextLine();
                                if (input.isEmpty()) {
                                        if (allowBlank) {
                                                return 0;
                                        }
                                        throw new Exception("Required interger!");
                                }

                                number = parseInt(input);
                                if (!BussinessRule.checkMarkLimit(number)) {
                                        throw new Exception("Invalid input!");
                                }
                                return number;
                        } catch (Exception e) {
                                if (e instanceof NumberFormatException) {
                                        System.out.println("Invalid input");
                                        continue;
                                }
                                System.out.println(e.getMessage());
                        }
                } while (true);
        }

        public static Boolean inputBoolean(String message, boolean allowBlank) {
                Boolean res;
                do {
                        try {
                                String input = inputString(message, "y|Y|n|N", allowBlank);
                                if (input == null && allowBlank) {
                                        return null;
                                }
                                res = input.toLowerCase().equals("y");
                                return res;
                        } catch (Exception e) {
                                System.out.println("Invalid input!");
                        }
                } while (true);
        }

        public static double inputDouble(String message, boolean allowBlank) {
                double number = 0;
                do {
                        try {
                                System.out.print(message);
                                String input = sc.nextLine();
                                if (input.isEmpty()) {
                                        if (allowBlank) {
                                                return 0;
                                        }
                                        throw new Exception("Required Double!");
                                }
                                number = Double.parseDouble(input);
                                if (!BussinessRule.checkMarkLimit(number)) {
                                        throw new Exception("Invalid Input!");
                                }
                                return number;
                        } catch (Exception e) {
                                if (e instanceof NumberFormatException) {
                                        System.out.println("Invalid input");
                                        continue;
                                }
                                System.out.println(e.getMessage());
                        }
                } while (true);
        }

        public static Date inputDate(String message, boolean allowBlank) {
                Date date = new Date();

                do {
                        try {
                                String input;
                                input = inputString(message, "", allowBlank);
                                if (input == null && allowBlank) {
                                        return null;
                                }
                                if (!checkValidDate(input)) {
                                        throw new Exception("Date doesn't exist");
                                }
                                date = Regex.DATE_PATTERN.parse(input);
                                return date;
                        } catch (Exception e) {
                                if (e instanceof ParseException) {
                                        System.out.println("Invalid input");
                                        continue;
                                }
                                System.out.println(e.getMessage());
                        }
                } while (true);
        }

        private static boolean checkValidDate(String input) {
                LocalDateTime time = LocalDateTime.now();
                String[] dateStrings = input.split("/");
                int year = Integer.parseInt(dateStrings[2]);
                int month = Integer.parseInt(dateStrings[1]);
                int day = Integer.parseInt(dateStrings[0]);

                if (year > time.getYear()) {
                        return false;
                }
                return DateValidation.checkDayValid(year, month, day);
        }

        public static String convertDateString(Date date) {
                String res;
                try {
                        res = Regex.DATE_PATTERN.format(date);
                } catch (Exception e) {
                        System.out.println("Convert error!");
                        return null;
                }
                return res;
        }

}

class DateValidation {

        static boolean checkYearValid(int year) {
                //get current year, month, day
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                return !(year > currentYear || year < 1900);
        }

        static boolean checkMonthValid(int month) {
                return !(month > 12 || month < 1);
        }

        static boolean IsNamNhuan(int year) {
                if (year % 400 == 0) {
                        return true;
                } else {
                        return year % 4 == 0 && year % 100 != 0;
                }
        }

        static boolean checkDayValid(int year, int month, int day) {
                if (month > 12 || month < 1) {
                        return false;
                }
                switch (month) {
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12:
                                // day 31
                                if (day > 31 || day <= 0) {
                                        return false;
                                }
                                break;
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                                //day 30
                                if (day > 30 || day <= 0) {
                                        return false;
                                }
                                break;
                        case 2:
                                if (IsNamNhuan(year)) {
                                        if (day > 29 || day <= 0) {
                                                return false;
                                        }
                                } else {
                                        if (day > 28 | day <= 0) {
                                                return false;
                                        }
                                }
                                break;
                        default:
                                break;
                }
                return true;
        }
}
