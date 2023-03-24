package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }
    @Override
    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS USERS (id BIGINT primary key, Name VARCHAR(20), LastName VARCHAR(45), age TINYINT)";
        try (Statement statement = Util.getConnection().createStatement()){
            statement.execute(createTable);
        }catch (Exception e) {
            System.out.println("Таблица не добавилась");
        }
    }
    @Override
    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()){
                statement.execute("DROP TABLE IF EXISTS USERS");
        } catch (SQLException e) {
            System.out.println("Eще жива табличка");
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        String insertUser = "INSERT INTO USERS(id, name, lastName, age) VALUES (?, ?, ?, ?)";
        User man = new User(name,lastName,age);
        try (PreparedStatement statement = Util.getConnection().prepareStatement(insertUser)){
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
    @Override
    public void removeUserById(long id) {
        String del = "Delete from USERS where id=" + id;
        try (Statement statement = Util.getConnection().createStatement()){
            statement.execute(del);
        } catch (SQLException e) {
            System.out.println("Юзер не удален");
        }
    }
    @Override
    public List<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()){
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
    @Override
    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()){
            statement.execute("Delete from USERS");
        } catch (SQLException e) {
            System.out.println("Данные еше живы");
        }
    }
}
