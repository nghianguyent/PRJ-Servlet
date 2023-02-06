/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author trong
 */
public class BussinessRule {

        public static boolean checkMarkLimit(double mark) {
                return !(mark < 0.0 || mark > 10.0);
        }
        public static boolean checkMarkLimit(int mark) {
                return !(mark < 0);
        }
}
