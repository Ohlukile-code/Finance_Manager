package io.github.lesegokhumalo.FinanceManger;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;

public class UserController {

    private Map<String, User> users = new HashMap<>();

    public void registerUser(Context ctx) {
        String username = ctx.formParam("username");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        if (validatePassword(password)) {
            User user = new User(username,email,password);
            users.put(username, user);
            ctx.status(201).result("User registered successfully");
        }else {
            ctx.status(400).result("Invalid password.");
        }
    }

    public void loginUser(Context ctx){
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            ctx.status(200).result("Login successful.");
        } else{
            ctx.status(401).result("Invalid credentials.");
        }
    }

    public boolean validatePassword(String password){
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,10}$";
        return password.matches(regex);
    }
}
