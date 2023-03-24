package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Name1", "LastName1", (byte) 1);
        userDao.saveUser("Name2", "LastName2", (byte) 2);
        userDao.saveUser("Name3", "LastName3", (byte) 3);
        userDao.saveUser("Name4", "LastName4", (byte) 100);
        userDao.removeUserById(2);
        for (User user: userDao.getAllUsers()) {
            System.out.println(user.toString());
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
