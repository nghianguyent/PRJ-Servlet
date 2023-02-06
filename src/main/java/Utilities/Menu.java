/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author trong
 */
public class Menu extends ArrayList<String> {

        public Menu() {
                super();
        }

        public int getUserChoice() {
                int choice;
                this.forEach((aThi) -> {
                        System.out.println(aThi);
                });
                choice = Validation.inputInterger("Enter your choice: ", false);
                
                return choice;
        }
}
