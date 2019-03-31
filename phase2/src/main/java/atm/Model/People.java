package atm.Model;

import java.io.Serializable;

public class People implements Serializable {
    private static final long serialVersionUID = 50L;

    public String username;
    public String password;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
