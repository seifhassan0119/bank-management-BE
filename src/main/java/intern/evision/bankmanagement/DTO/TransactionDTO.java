package intern.evision.bankmanagement.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class TransactionDTO {

    private Integer id;

    @NotBlank(message = "Account ID is required")
    private String accountId;

    private String type;

    @NotBlank(message = "Amount is required")
    @Pattern(
            regexp = "^(?!0+(\\.0+)?$)\\d+(\\.\\d+)?$",
            message = "Amount must be greater than 0"
    )
    private String amount;

    private String balanceAfter;

    private String description;

    private String timestamp;

    public TransactionDTO() {
    }

    public TransactionDTO(
            Integer id,
            String accountId,
            String type,
            String amount,
            String balanceAfter,
            String description,
            String timestamp) {

        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.description = description;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(String balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}