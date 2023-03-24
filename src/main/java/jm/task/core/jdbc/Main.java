package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService man = new UserServiceImpl();
        man.createUsersTable();
        man.saveUser("Name1", "LastName1", (byte) 1);
        man.saveUser("Name2", "LastName2", (byte) 2);
        man.saveUser("Name3", "LastName3", (byte) 3);
        man.saveUser("Name4", "LastName4", (byte) 100);
        man.removeUserById(2);
        for (User user: man.getAllUsers()) {
            System.out.println(user.toString());
        }
        man.cleanUsersTable();
        man.dropUsersTable();
    }
}
