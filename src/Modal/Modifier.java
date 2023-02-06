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
public interface Modifier {
        public void addItem(Object obj);
        public void updateItem(Object obj);
        public void removeItem(String id);
        public Object searchItem(String id);
        public void printall();
        public void printObj(Object obj);
}

