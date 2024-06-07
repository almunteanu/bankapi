package dev.almuntex.bankapi.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.almuntex.bankapi.service.TransactionService;

public class Application {

    public static TransactionService transactionService = new TransactionService();
    public static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
}
