/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampe.dao;

import sample.utils.mylib;
import sample.dto.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Nghia Nguyen
 */
public class AccountDAO {

    // ham nay de lay tata ca cac account
    public static ArrayList<Account> getAccounts() throws Exception {
        ArrayList<Account> result = new ArrayList<>();
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + "from dbo.Accounts";
                Statement stm = cn.createStatement();
                ResultSet table = stm.executeQuery(sql);

                while (table.next()) {
                    int accid = table.getInt("accID");
                    String email = table.getString("email");
                    String password = table.getString("password");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("Status");
                    int role = table.getInt("role");
                    String token = table.getString("token");
                    result.add(new Account(accid, email, password, fullname, phone, status, role, token));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 1.  make connection
        // 2. viet querry
        // 3. xu ly dap an
        // 4. dong connection

        return result;
    }

    public static ArrayList<Account> getAccount(int role) throws Exception {
        ArrayList<Account> result = new ArrayList<>();
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role, token\n"
                        + "from dbo.Accounts where role = ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, role);
                ResultSet table = stm.executeQuery();

                while (table.next()) {
                    int accid = table.getInt("accID");
                    String email = table.getString("email");
                    String password = table.getString("password");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("Status");
                    String token = table.getString("token");
                    result.add(new Account(accid, email, password, fullname, phone, status, role, token));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<Account> getAccountsByEmail(String email) throws Exception {
        ArrayList<Account> result = new ArrayList<>();
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role, token\n"
                        + "from dbo.Accounts where email like ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, "%" + email + "%");
                ResultSet table = stm.executeQuery();

                while (table.next()) {
                    int accid = table.getInt("accID");
                    email = table.getString("email");
                    String password = table.getString("password");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("Status");
                    int role = table.getInt("role");
                    String token = table.getString("token");
                    result.add(new Account(accid, email, password, fullname, phone, status, role, token));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Account getAccountByEmail(String email) throws Exception {
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role, token\n"
                        + "from dbo.Accounts where email like ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, email);
                ResultSet table = stm.executeQuery();

                while (table.next()) {
                    int accid = table.getInt("accID");
                    email = table.getString("email");
                    String password = table.getString("password");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("Status");
                    int role = table.getInt("role");
                    String token = table.getString("token");
                    return new Account(accid, email, password, fullname, phone, status, role, token);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ham nay de lay 1 account dua vaof email, passsword
    public static Account getAccount(String email, String password) throws Exception {
        ArrayList<Account> result = new ArrayList<>();
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role, token\n"
                        + "from dbo.Accounts where email = ? and password = ? and status = 1";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                ResultSet table = stm.executeQuery();

                while (table.next()) {
                    int accid = table.getInt("accID");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("Status");
                    int role = table.getInt("role");
                    String token = table.getString("token");
                    result.add(new Account(accid, email, password, fullname, phone, status, role, token));
                }
                if (result.isEmpty()) {
                    return null;
                }
                return result.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Account getAccount(String token) throws Exception {
        ArrayList<Account> result = new ArrayList<>();
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role\n"
                        + "from dbo.Accounts where token = ? and status = 1";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, token);
                ResultSet table = stm.executeQuery();

                while (table.next()) {
                    int accid = table.getInt("accID");
                    String email = table.getString("email");
                    String password = table.getString("password");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("Status");
                    int role = table.getInt("role");
                    result.add(new Account(accid, email, password, fullname, phone, status, role, token));
                }
                if (result.size() == 0) {
                    return null;
                }
                return result.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateToken(String token, String email) {
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Accounts set token = ? where email = ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, token);
                stm.setString(2, email);
                int result = stm.executeUpdate();
                return result > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateAccountStatus(String email, int status) {
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Accounts set status = ? where email = ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setString(2, email);
                int result = stm.executeUpdate();
                return result > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean UpdateAccountProfile(int accId, String name, String phone) {
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Accounts set fullname = ?, phone = ? where accId = ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, phone);
                stm.setInt(3, accId);
                int result = stm.executeUpdate();
                return result > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newStatus, int newRole) {

        try {
            Account existedAcc = getAccount(newEmail, newPassword);
            if (existedAcc != null) {
                return false;
            }
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "insert into dbo.Accounts (email, password, fullname, phone, status, role) values (?,?,?,?,?,?)";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, newEmail);
                stm.setString(2, newPassword);
                stm.setString(3, newFullname);
                stm.setString(4, newPhone);
                stm.setInt(5, newStatus);
                stm.setInt(6, newRole);
                int result = stm.executeUpdate();
                return result > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
