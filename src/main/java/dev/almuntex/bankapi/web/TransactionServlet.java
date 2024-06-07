package dev.almuntex.bankapi.web;

import dev.almuntex.bankapi.context.Application;
import dev.almuntex.bankapi.model.Transaction;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class TransactionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().equalsIgnoreCase("/transactions")) {
            List<Transaction> transactions = Application.transactionService.findAll();

            resp.setContentType("application/json; charset=UTF-8");
            resp.getWriter().print(Application.objectMapper.writeValueAsString(transactions));
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().equalsIgnoreCase("/transactions")) {
            int amount = Integer.parseInt(req.getParameter("amount"));
            String reference = req.getParameter("reference");

            Transaction transaction = Application.transactionService.create(amount, reference);

            resp.setContentType("application/json; charset=UTF-8");
            resp.getWriter().print(Application.objectMapper.writeValueAsString(transaction));
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
