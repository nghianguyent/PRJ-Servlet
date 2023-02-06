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
public class Department extends HospitalMod implements Serializable {

        private String name;

        public Department() {
        }

        public Department(String id, String name) {
                super(id);
                this.name = name;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        @Override
        public String toString() {
                return "id=" + id + "name=" + name + "createDate=" + createDate + "lastUpdateDate=" + lastUpdateDate;
        }

        public String toFile() {
                return id + ", " + name + ", " + createDate + ", " + lastUpdateDate;
        }
}
