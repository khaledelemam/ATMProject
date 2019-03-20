package atm;

import java.io.Serializable;

public class Request implements Serializable {

    private String username;
    private AccountType accountType;
    private static final long serialVersionUID = 100L;

    public Request(String username, AccountType accountType) {
        this.username = username;
        this.accountType = accountType;
    }

    public String getUsername() {
        return this.username;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }
}
