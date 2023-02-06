/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import sampe.dao.AccountDAO;
import sample.dto.Account;
import java.util.ArrayList;

/**
 *
 * @author Nghia Nguyen
 */
public class test {
    public static void main(String[] args) {
        try {
            ArrayList<Account> List = AccountDAO.getAccount(0);
            for (Account account : List) {
                System.out.println(account.toString());
            }   
            System.out.println(AccountDAO.updateAccountStatus("tung@gmail.com", 1));
            System.out.println(AccountDAO.insertAccount("nghia@gmail.com", "123", "nguyen nghia", "0912312", 1, 0));
        } catch (Exception e) {
        }
    }
}
