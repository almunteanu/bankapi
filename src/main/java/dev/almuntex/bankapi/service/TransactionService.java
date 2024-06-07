package dev.almuntex.bankapi.service;

import dev.almuntex.bankapi.model.Transaction;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransactionService {

    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public List<Transaction> findAll() {
        return transactions;
    }

    public Transaction create(Integer amount, String reference) {
        Transaction transaction = new Transaction(amount, reference);
        transactions.add(transaction);
        return transaction;
    }
}
