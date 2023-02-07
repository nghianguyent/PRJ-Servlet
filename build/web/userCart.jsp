<%-- 
    Document   : userCart
    Created on : Oct 14, 2022, 9:05:28 AM
    Author     : tklin
--%>

<%@page import="java.util.Map"%>
<%@page import="binhnd.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <%
            //1. Cust goes to cart placment
            if (session != null) {
                //2. Cus takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cus takes items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. System traverses items to show
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Remove</th>
                    <th>Checkout</th>
                </tr>
            </thead>
            <form action="DispatchController">

                <tbody>
                    <%
                        int count = 0;
                        for (String key : items.keySet()) {
                            int value = items.get(key);
                    %>
                    <tr>
                        <td><%= ++count%></td>
                        <td><%= key%></td>
                        <td><%= value%></td>
                        <td>
                            <input type="checkbox" name="chkRemovedItem" value="<%= key%>" />
                        </td>
                        <td>
                            <input type="checkbox" name="chkCheckoutItem" value="<%= key%>" />
                        </td>

                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td colspan="3">
                            <a href="shopping.jsp">Add More Items to Your Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Selected Items" name="btAction" />
                        </td>
                        <td>
                            <input type="submit" value="Checkout Selected Items" name="btAction" />
                        </td>
                    </tr>

                </tbody>
            </form>
        </table>

        <%
                        return;
                    }
                    
                } //end cart has existed
            }
        %>
        <h2>No Cart is existed</h2>
    </body>
</html>
