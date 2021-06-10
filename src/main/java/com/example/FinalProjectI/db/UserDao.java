package com.example.FinalProjectI.db;

import com.example.FinalProjectI.db.entity.User;
import com.example.FinalProjectI.db.exception.CustomDBException;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    boolean insertUser(User user, Connection connection) throws CustomDBException;
    boolean deleteUser(String login, Connection connection) throws CustomDBException;
    boolean validateUser(String login,String password,Connection connection) throws CustomDBException;
    User findUser(String login, Connection connection) throws CustomDBException;
    boolean updateUser(String login,String newPassword,Connection connection) throws CustomDBException;
    List<User> findAllUser(Connection connection) throws CustomDBException;
}
