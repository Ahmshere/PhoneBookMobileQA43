package tasks;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationSystem {
    private Map<String, String> users = new HashMap<>();
    public boolean registerUser(String username, String password) {
        if(users.containsKey(username)) {
         return false;
          }
        System.out.println("User not found");
         users.put(username, password);
         return true;
    }

    public boolean loginUser(String username, String password) {
        if (users.get(username).equals(password)) {
            return true;
        }
        return false;
    }

    public void changePassword(String username, String newPassword) {
        if (users.containsKey(username)) {
            users.put(username, newPassword);
        }else {
            System.out.println("User not found" + username);
        }
    }
}
