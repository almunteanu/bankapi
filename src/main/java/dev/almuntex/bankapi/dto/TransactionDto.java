package dev.almuntex.bankapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;

public class TransactionDto {

    @NotBlank
    @JsonProperty("receiving_user_id")
    private String receivingUserId;

    @DecimalMin("0.01")
    private BigDecimal amount;

    @NotBlank
    private String reference;

    public String getReceivingUserId() {
        return receivingUserId;
    }

    public void setReceivingUserId(String receivingUserId) {
        this.receivingUserId = receivingUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public static class ValidationErrorDto {

        private String message;
        @JsonProperty("invalid_fields")
        private List<String> invalidFields;

        public ValidationErrorDto(String message, List<String> invalidFields) {
            this.message = message;
            this.invalidFields = invalidFields;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<String> getInvalidFields() {
            return invalidFields;
        }

        public void setInvalidFields(List<String> invalidFields) {
            this.invalidFields = invalidFields;
        }
    }
}
