/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

//import Utils.Validation;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author trong
 */
public class HospitalMod implements Serializable {

        protected String id;
        protected Date createDate;
        protected Date lastUpdateDate;

        public HospitalMod() {
                this.createDate = new Date();
                lastUpdateDate = null;
        }

        public HospitalMod(String id) {
                this.id = id;
                this.createDate = new Date();
                lastUpdateDate = null;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public Date getLastUpdateDate() {
                return lastUpdateDate;
        }

        public void setLastUpdateDate(Date lastUpdateDate) {
                this.lastUpdateDate = lastUpdateDate;
        }

        public Date getCreateDate() {
                return createDate;
        }

        @Override
        public String toString() {
                return "HospitalMod{" + "id=" + id + ", createDate=" + createDate + ", lastUpdateDate=" + lastUpdateDate + '}';
        }
}
