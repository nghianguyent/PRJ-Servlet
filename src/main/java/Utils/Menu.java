/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Modal.HospitalMod;
import java.util.ArrayList;
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
                for (int i = 0; i < this.size(); i++) {
                        String item = this.get(i);
                        System.out.println((i+1) + ". " + item);
                }
                choice = Validation.inputInterger("Enter your choice: ", false);
                return choice;
        }

}

