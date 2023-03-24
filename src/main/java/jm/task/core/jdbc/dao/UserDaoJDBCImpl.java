package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }
    public void createUsersTable() {
        String createTable = "CREATE TABLE USERS (id BIGINT primary key, Name VARCHAR(20), LastName VARCHAR(45), age TINYINT)";
        Connection newCon = Util.getConnection();
        try (Statement statement = newCon.createStatement()){
            DatabaseMetaData meta = newCon.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, "USERS", new String[] {"TABLE"});
            if (!resultSet.next()) {
            statement.execute(createTable);
            } else {
                System.out.println("Таблица уже существует");
            }
        }catch (Exception e) {
            System.out.println("Таблица не добавилась");
        }
    }

    public void dropUsersTable() {
        Connection newCon = Util.getConnection();
        try (Statement statement = newCon.createStatement()){
            DatabaseMetaData meta = newCon.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, "USERS", new String[] {"TABLE"});
            if (resultSet.next()) {
                statement.execute("DROP TABLE USERS");
            } else {
                System.out.println("Таблица не существует");
            }
        } catch (SQLException e) {
            System.out.println("Eще жива табличка");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection newCon = Util.getConnection();
        String insertUser = "INSERT INTO USERS(id, name, lastName, age) VALUES (?, ?, ?, ?)";
        User man = new User(name,lastName,age);
        try (PreparedStatement statement = newCon.prepareStatement(insertUser)){
            statement.setLong(1, man.getId());
            statement.setString(2, man.getName());
            statement.setString(3, man.getLastName());
            statement.setByte(4, man.getAge());
            statement.executeUpdate();
            System.out.println("User  " + man.getName() + "  добавлен в базу");
        }catch (Exception e) {
            System.out.println("Юзер не добавился");
        }
    }

    public void removeUserById(long id) {
        Connection newCon = Util.getConnection();
        String del = "Delete from USERS where id=" + id;
        try (Statement statement = newCon.createStatement()){
            statement.execute(del);
        } catch (SQLException e) {
            System.out.println("Юзер не удален");
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        Connection newCon = Util.getConnection();
        try (Statement statement = newCon.createStatement()){
            ResultSet results = statement.executeQuery("SELECT * FROM USERS");
            while (results.next()) {
                User user = new User();
                user.setId(results.getLong(1));
                user.setName(results.getString(2));
                user.setLastName(results.getString(3));
                user.setAge(results.getByte(4));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Списка не будет");
        }
        return list;
    }

    public void cleanUsersTable() {
        Connection newCon = Util.getConnection();
        try (Statement statement = newCon.createStatement()){
            statement.execute("Delete from USERS");
        } catch (SQLException e) {
            System.out.println("Данные еше живы");
        }
    }
}
