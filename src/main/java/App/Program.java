/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import ClasssList.HospitalManagement;
import Modal.Doctor;
import Utils.Menu;

/**
 *
 * @author trong
 */
public class Program {

        public static Menu menuInit() {
                Menu menu = new Menu();
                menu.add("Show information");
                menu.add("Add new");
                menu.add("Update information");
                menu.add("delete");
                menu.add("Search information");
                menu.add("Save to File");
                menu.add("Other - quit");
                return menu;
        }
        
        public static void main(String[] args) {
                HospitalManagement hospitalManagement = new HospitalManagement();
                Menu menu = menuInit();
                System.out.println("=========== Hospital management ==============");
                System.out.println("");
                do {
                        System.out.println("=========== Main menu ============");
                        int choice = menu.getUserChoice();
                        switch (choice) {
                                case 1:
                                        hospitalManagement.showInfo();
                                        break;
                                case 2:
                                        hospitalManagement.addNew();
                                        break;
                                case 3:
                                        hospitalManagement.updateInfo();
                                        break;
                                case 4:
                                        hospitalManagement.delete();
                                        break;
                                case 5:
                                        hospitalManagement.search();
                                        break;
                                case 6: 
                                        hospitalManagement.saveToFile();
                                        break;
                                default:
                                        return;
                        }
                } while (true);
        }
}
