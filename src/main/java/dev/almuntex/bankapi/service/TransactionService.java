package dev.almuntex.bankapi.service;

import dev.almuntex.bankapi.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TransactionService {

    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();

    private final String bankSlogan;

    public TransactionService(@Value("${bank.slogan}") String bankSlogan) {
        this.bankSlogan = bankSlogan;
    }

    public List<Transaction> findAll() {
        return transactions;
    }

    public Transaction create(Integer amount, String reference) {
        Transaction transaction = new Transaction(amount, reference, bankSlogan);
        transactions.add(transaction);
        return transaction;
    }
}
