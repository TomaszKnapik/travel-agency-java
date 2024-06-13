package Models;

public class UserSingleton {
    private static UserSingleton instance;
    public static UserSingleton getInstance() {
        return instance;
    }
    // TODO użyć do wylogowania
    public static void logout() {
        instance = null;
    }

    private User user;

    public UserSingleton(User user) {
        instance = this;
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
