package intern.evision.bankmanagement.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AccountDTO {

    private Integer id;

    @NotBlank(message = "Customer ID is required")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "Customer ID must contain only numbers"
    )
    private String customerId;

    @NotBlank(message = "Account number is required")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "Account number must contain only numbers"
    )
    private String accountNumber;

    @NotBlank(message = "Account type is required")
    @Pattern(
            regexp = "^(SAVINGS|CHECKING)$",
            message = "Account type must be SAVINGS or CHECKING"
    )
    private String accountType;

    @NotBlank(message = "Balance is required")
    @Pattern(
            regexp = "^(0|[1-9][0-9]*)(\\.[0-9]{1,2})?$",
            message = "Balance must be a valid number with up to 2 decimal places"
    )
    private String balance;

    @NotBlank(message = "Currency is required")
    @Pattern(
            regexp = "^[A-Z]{3}$",
            message = "Currency must be a valid 3-letter code"
    )
    private String currency;

    @NotBlank(message = "Status is required")
    @Pattern(
            regexp = "^(ACTIVE|INACTIVE|BLOCKED|CLOSED)$",
            message = "Status must be ACTIVE, INACTIVE, BLOCKED, or CLOSED"
    )
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerid) {
        this.customerId = customerid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountnumber) {
        this.accountNumber = accountnumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AccountDTO() {
    }

    public AccountDTO(
            Integer id,
            String customerId,
            String accountNumber,
            String accountType,
            String balance,
            String currency,
            String status) {

        this.id = id;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.currency = currency;
        this.status = status;
    }
}