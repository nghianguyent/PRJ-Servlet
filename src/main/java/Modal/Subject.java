/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

/**
 *
 * @author trong
 */
public class Subject {

        private String id;
        private String name;
        private int credit;

        public Subject() {
        }

        public Subject(String id, String name, int credit) {
                this.id = id;
                this.name = name;
                this.credit = credit;
        }

        public String getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getCredit() {
                return credit;
        }

        public void setCredit(int credit) {
                this.credit = credit;
        }

}
