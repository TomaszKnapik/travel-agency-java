package Models;

public class User {
    private int id;
    private String email;
    private String username;

    private int userRole;

    public User (int id, String email, String username, int userRole) {
        this.id = id;
        this.email = email;
        this.username = username;
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
}
