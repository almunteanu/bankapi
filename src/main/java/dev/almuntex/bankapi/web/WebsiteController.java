package dev.almuntex.bankapi.web;

import dev.almuntex.bankapi.service.TransactionService;
import dev.almuntex.bankapi.web.form.CreateTransactionForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Controller
public class WebsiteController {

    private final TransactionService transactionService;

    public WebsiteController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public String homepage() {
        return "index.html";
    }

    @GetMapping("/account/{userId}")
    public String account(Model model, @PathVariable(name = "userId") String userId) {
        model.addAttribute("userId", userId);
        model.addAttribute("transactions", transactionService.findByUserId(userId));
        model.addAttribute("createTransactionForm", new CreateTransactionForm());

        return "account.html";
    }

    @PostMapping("/account/{userId}")
    public String createTransaction(@ModelAttribute @Valid CreateTransactionForm form,
                                    BindingResult bindingResult,
                                    Model model,
                                    @PathVariable(name = "userId") String userId) {
        model.addAttribute("userId", userId);
        model.addAttribute("transactions", transactionService.findByUserId(userId));

        if (bindingResult.hasErrors()) {
            return "account.html";
        }

        transactionService.create(form.getReceivingUserId(), form.getAmount(), form.getReference(),
                OffsetDateTime.now(ZoneOffset.of("+03:00")));

        return "redirect:/account/" + userId;
    }
}
