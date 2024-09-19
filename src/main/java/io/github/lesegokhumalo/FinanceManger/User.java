package io.github.lesegokhumalo.FinanceManger;

public class User {

    private String username;
    private String email;
    private String password;
    private Budget budget;

    public User(String username, String email, String password, Budget budget) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.budget = budget;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.budget = new Budget("0",0,0,0,0,0);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
