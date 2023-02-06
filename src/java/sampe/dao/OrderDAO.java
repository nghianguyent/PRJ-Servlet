/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampe.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;
import sample.dto.Account;
import sample.dto.Order;
import sample.dto.OrderDetail;
import sample.utils.mylib;

/**
 *
 * @author Nghia Nguyen
 */
public class OrderDAO implements Serializable {

    public static ArrayList<Order> getOrders() throws Exception {
        ArrayList<Order> result = new ArrayList<>();
        Connection cn = null;
        try {
            cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + "from dbo.Orders";
                Statement stm = cn.createStatement();
                ResultSet table = stm.executeQuery(sql);

                while (table.next()) {
                    int orderID = table.getInt("OrderID");
                    String orderDate = table.getString("OrdDate");
                    String shipDate = table.getString("shipDate");
                    int status = table.getInt("status");
                    int accID = table.getInt("AccID");
                    result.add(new Order(orderID, orderDate, shipDate, status, accID));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return result;
    }

    public static ArrayList<Order> getOrders(String email) throws Exception {
        ArrayList<Order> result = new ArrayList<>();
        Account acc = AccountDAO.getAccountByEmail(email);
        Connection cn = null;
        if (acc != null) {
            try {
                cn = mylib.makeConnection();
                if (cn != null) {
                    String sql = "select *\n"
                            + "from dbo.Orders where AccID = ?";
                    PreparedStatement stm = cn.prepareStatement(sql);
                    stm.setInt(1, acc.getAcacid());
                    ResultSet table = stm.executeQuery();

                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipDate");
                        int status = table.getInt("status");
                        int accID = table.getInt("AccID");
                        result.add(new Order(orderID, orderDate, shipDate, status, accID));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cn.close();
            }
        }
        return result;
    }

    public static ArrayList<Order> getOrdersByDate(String from, String to, int id) throws Exception {
        ArrayList<Order> result = new ArrayList<>();
        Connection cn = null;
        try {
            cn = mylib.makeConnection();
            if (cn != null) {
                int type = 0;
                String sql = "select *\n"
                        + "from dbo.Orders where AccID = ? and OrdDate ";
                if ((from == null || from.isEmpty()) && (to == null || to.isEmpty())) {
                    sql += "> '1996-01-01'";
                } else {
                    if (from == null || from.isEmpty()) {
                        sql += "< ?";
                        type = 1;
                    } else if (to == null || to.isEmpty()) {
                        sql += "> ?";
                        type = 2;
                    } else {
                        sql += "between ? and ?";
                        type = 3;
                    }
                }
                PreparedStatement stm = cn.prepareStatement(sql);
                switch (type) {
                    case 1:
                        stm.setString(2, to);
                        break;
                    case 2:
                        stm.setString(2, from);
                        break;
                    case 3:
                        stm.setString(2, from);
                        stm.setString(3, to);
                }
                stm.setInt(1, id);
                ResultSet table = stm.executeQuery();

                while (table.next()) {
                    int orderID = table.getInt("OrderID");
                    String orderDate = table.getString("OrdDate");
                    String shipDate = table.getString("shipDate");
                    int status = table.getInt("status");
                    int accID = table.getInt("AccID");
                    result.add(new Order(orderID, orderDate, shipDate, status, accID));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return result;
    }

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) {
        ArrayList<OrderDetail> result = new ArrayList<>();
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "select DetailId, OrderID, Plants.PID, PName, price, imgPath, quantity \n"
                        + "from OrderDetails, Plants\n"
                        + "where OrderID = ? and OrderDetails.PID = Plants.PID";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, orderID);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int detailID = rs.getInt("DetailID");
                        int plantID = rs.getInt("PID");
                        String PlantName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        int quantity = rs.getInt("quantity");
                        result.add(new OrderDetail(detailID, orderID, plantID, PlantName, price, imgPath, quantity));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean UpdateStatus(int orderID, int status) {
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "update Orders set status = ? where OrderID = ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(2, orderID);
                stm.setInt(1, status);
                return stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean CreateOrder(Order order) {
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "insert into Orders (OrdDate, shipdate, status, AccID) values (?,?,?,?) ";
                PreparedStatement stm = cn.prepareStatement(sql);
//                stm.setInt(1, order.getOrderID());
                stm.setString(1, order.getOrderDate());
                stm.setString(2, order.getShipDate());
                stm.setInt(3, order.getStatus());
                stm.setInt(4, order.getAccID());
                return stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean inserOrder(String email, HashMap<String, Integer> cart) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = mylib.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                String sql = "select accID from Accounts where email = ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, email);
                ResultSet rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }
                System.out.println("accountid: " + accid);
                Date d = new Date(System.currentTimeMillis());
                System.out.println("order date: " + d);
                sql = "insert Orders(OrdDate, status, AccID) values (?,?,?)";
                stm = cn.prepareStatement(sql);
                stm.setDate(1, d);
                stm.setInt(2, 1);
                stm.setInt(3, accid);
                stm.executeUpdate();
                sql = "select top 1 orderID from Orders order by orderId desc ";
                stm = cn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("orderID");
                }
                System.out.println("orderid: " + orderid);
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert OrderDetails values(?, ?, ?)";
                    stm = cn.prepareStatement(sql);
                    stm.setInt(1, orderid);
                    stm.setInt(2, Integer.parseInt(pid.trim()));
                    stm.setInt(3, cart.get(pid));
                    stm.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            } else {
                System.out.println("Khong chen duoc");
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    public static boolean CreateOrderDetail(OrderDetail orderDetail) {
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {

                String sql = "SET IDENTITY_INSERT OrderDetails OFF ;"
                        + "insert into OrderDetails (OrderID, PID, quantity) values (?,?,?)";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, orderDetail.getOrderID());
                stm.setInt(2, orderDetail.getPlantID());
                stm.setInt(3, orderDetail.getQuantity());
                return stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
}
