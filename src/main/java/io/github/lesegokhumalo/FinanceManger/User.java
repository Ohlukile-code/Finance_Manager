package io.github.lesegokhumalo.FinanceManger;

public class User {
    private String username;
    private String email;
    private String password;
    private Profile profile;  // Linked Profile object

    public User(String username, String email, String password, Profile profile) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Profile getProfile() {
        return profile;
    }
}
