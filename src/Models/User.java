package Models;

public class User {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String name;
    private String sureName;
    private int userRole;

    public User (int userId, String username, String email, String password, String name, String sureName, int userRole) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.sureName = sureName;
        this.userRole = userRole;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }



    public int getUserRole() {
        return userRole;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "ID: " + userId + ", Nazwa użytkownika: " + username + ", Email: " + email;
    }
}
