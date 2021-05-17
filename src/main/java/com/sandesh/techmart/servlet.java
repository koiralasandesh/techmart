package com.sandesh.techmart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.sandesh.techmart.jdbc.orderQuantityHigh;
import com.sandesh.techmart.jdbc.outOfStockException;

import static com.sandesh.techmart.jdbc.databaseInterface.updateQuantity;

@WebServlet("/buy")
public class servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productname= String.valueOf(req.getParameter("productname"));
        int quantity=Integer.parseInt (String.valueOf(req.getParameter("quantity")));
//        System.out.println(productname);
//        System.out.println(quantity);
        try {
            updateQuantity(productname,quantity);
            resp.getWriter().println("order placed");
        } catch (outOfStockException e) {
            resp.getWriter().println("item out of order");
        } catch (orderQuantityHigh e) {
            resp.getWriter().println("order quantity too high");
        }

    }
}